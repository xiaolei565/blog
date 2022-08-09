package cn.ustc.blog.service.Impl;

import cn.ustc.blog.entity.SysMenu;
import cn.ustc.blog.entity.SysRole;
import cn.ustc.blog.entity.SysUser;
import cn.ustc.blog.mapper.SysUserMapper;
import cn.ustc.blog.mapper.UserMapper;
import cn.ustc.blog.service.SysMenuService;
import cn.ustc.blog.service.SysRoleService;
import cn.ustc.blog.service.SysUserService;
import cn.ustc.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaolei565
 * @since 2022-04-11
 */
@Service
public class SysUserServiceImp extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public SysUser getUserByName(String username) {
        return getOne(new QueryWrapper<SysUser>().eq("username",username));
    }

    /**
     * 由于这里需要经常性查库，需要对其进行优化，加一层缓存
     * @param userId
     * @return
     */
    @Override
    public String getUserAuthority(Long userId) {
        // ROLE_admin,sys:user:list,.......
        String authority = null;
        SysUser sysUser = sysUserMapper.selectById(userId);
        // 首先判断缓存中有没有信息
        if (redisUtil.hasKey("GrantedAuthority:" + sysUser.getUsername())) {
            authority = (String) redisUtil.get("GrantedAuthority:" + sysUser.getUsername());
        } else {
            // 获取角色,暂时其他表还没写
            List<SysRole> roles = sysRoleService.list(new QueryWrapper<SysRole>()
                    .inSql("id", "select role_id from sys_user_role where user_id = " + userId));
            if(roles.size()>0){
//                String roleNames = roles.stream().map(r -> r.getCode()).collect(Collectors.joining(","));
                // role前缀是用来后面角色投票使用的
                String roleNames = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
//                authority = roleNames.concat(",");
                authority = roleNames;
            }
//            List<Long> menuIds = sysUserMapper.getNavMenuIds(userId);
//            if(menuIds.size()>0){
//                List<SysMenu> menus = sysMenuService.listByIds(menuIds);
//                String permNames = menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));
//                authority = authority.concat(permNames);
//            }
        }
        // 存入缓存
        redisUtil.set("GrantedAuthority:" + sysUser.getUsername(), authority, 60 * 60);

        // 获取菜单操作权限
        return authority;
    }

    /**
     * 清除缓存
     * @param username
     */
    @Override
    public void clearUserAuthorityInfo(String username) {
        redisUtil.del("GrantedAuthority:" + username);
    }

    /**
     * 通过角色id清除缓存
     * @param roleId
     */
    @Override
    public void clearUserAuthorityInfoByRoleId(Long roleId) {
        List<SysUser> sysUsers = this.list(new QueryWrapper<SysUser>()
                .inSql("id", "select user_id from sys_user_role where role_id = " + roleId));

        sysUsers.forEach(u -> {
            this.clearUserAuthorityInfo(u.getUsername());
        });

    }

    /**
     * 通过菜单id清除缓存
     * @param menuId
     */
    @Override
    public void clearUserAuthorityInfoByMenuId(Long menuId) {
        List<SysUser> sysUsers = sysUserMapper.listByMenuId(menuId);

        sysUsers.forEach(u -> {
            this.clearUserAuthorityInfo(u.getUsername());
        });

    }
}
