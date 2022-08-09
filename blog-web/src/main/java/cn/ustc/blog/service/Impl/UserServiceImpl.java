package cn.ustc.blog.service.Impl;

import cn.ustc.blog.entity.SysUser;
import cn.ustc.blog.entity.User;
import cn.ustc.blog.mapper.UserMapper;
import cn.ustc.blog.service.UserService;
import cn.ustc.response.Result;
import cn.ustc.response.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaolei565
 * @since 2022-03-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getOne(String username,String userpwd) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 这个列要改一下
        QueryWrapper<User> eq = wrapper.eq("username",username).eq("userpwd",userpwd);
        User user = userMapper.selectOne(eq);
        return user;
    }
}
