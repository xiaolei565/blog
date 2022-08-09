package cn.ustc.blog.service;

import cn.ustc.blog.entity.SysUser;
import cn.ustc.blog.entity.User;
import cn.ustc.response.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaolei565
 * @since 2022-03-29
 */
public interface UserService extends IService<User> {

//    Result active(int id);

    //根据username查询用户
    User getOne(String username,String userpwd);


}
