package cn.ustc.blog.service.Impl;

import cn.hutool.core.util.StrUtil;
import cn.ustc.blog.entity.AccountUser;
import cn.ustc.blog.entity.SysUser;
import cn.ustc.blog.mapper.UserMapper;
import cn.ustc.blog.service.SysUserService;
import cn.ustc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.blog.service.Impl
 * @Author: leixue
 * @CreateTime: 2022-04-05 15:46
 * @Description: 继承UserService（安全框架中的接口）
 */
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 重写该方法：通过用户名加载该用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 判断用户名是否为空
        if(StrUtil.isEmpty(username)){
            throw new RuntimeException("用户名不可为空");
        }
        // 根据用户名查找用户信息
        SysUser sysUser =  sysUserService.getUserByName(username);
        // 判断用户是否为空
        if(sysUser==null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new AccountUser(
                sysUser.getId(),
                sysUser.getUsername(),
                sysUser.getPassword(),
                // 权限
                getAuthorities(sysUser.getId())
                );
    }

    /**
     * 获取用户权限列表，包括角色(ROLE_admin)和菜单权限(sys:user:list)
     * // TODO: 2022/4/25  这里要改造，返回的不能有菜单权限，直接返回角色即可 
     * @param userId
     * @return
     */
    public List<GrantedAuthority> getAuthorities(Long userId){
        // 结果类似ROLE_admin,sys:user:list,.......
        String userAuthority = sysUserService.getUserAuthority(userId);
        // 通过该工具类将string转成list，其中分隔符以逗号隔开
//        System.out.println(AuthorityUtils.commaSeparatedStringToAuthorityList(userAuthority));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(userAuthority);
    }
}
