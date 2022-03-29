package cn.ustc.blog.service.Impl;

import cn.ustc.blog.entity.User;
import cn.ustc.blog.mapper.UserMapper;
import cn.ustc.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaolei565
 * @since 2022-03-29
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

}
