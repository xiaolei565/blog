package cn.ustc.blog.service;

import cn.ustc.blog.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaolei565
 * @since 2022-04-11
 */
public interface SysUserService extends IService<SysUser> {

    SysUser getUserByName(String username);

    String getUserAuthority(Long userId);

    /**
     * 这里预留几个方法，缓存需要删除重新生成等
     */
    void clearUserAuthorityInfo(String username);

    void clearUserAuthorityInfoByRoleId(Long roleId);

    void clearUserAuthorityInfoByMenuId(Long menuId);
}
