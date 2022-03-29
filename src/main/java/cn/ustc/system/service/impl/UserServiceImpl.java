package cn.ustc.system.service.impl;

import cn.ustc.system.entity.User;
import cn.ustc.system.mapper.UserMapper;
import cn.ustc.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.system.service.impl
 * @Author: leixue
 * @CreateTime: 2022-03-27 14:52
 * @Description: user service具体实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(int id) {

        return userMapper.getUserById(id);
    }
}
