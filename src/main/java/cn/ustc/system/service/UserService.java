package cn.ustc.system.service;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.system.service
 * @Author: leixue
 * @CreateTime: 2022-03-26 21:12
 * @Description: userservice
 */

import cn.ustc.system.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /***/
    User getUserById(int id);
}
