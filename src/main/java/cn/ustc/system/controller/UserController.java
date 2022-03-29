package cn.ustc.system.controller;

import cn.ustc.response.Result;
import cn.ustc.system.entity.User;
import cn.ustc.system.mapper.UserMapper;
import cn.ustc.system.service.UserService;
import cn.ustc.system.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.system.controller
 * @Author: leixue
 * @CreateTime: 2022-03-26 21:19
 * @Description: usercontroller
 */

@RestController
@RequestMapping(value ="/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserMapper userMapper;

//    @GetMapping("/test")


    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") int id){
        log.info("hehe");
        User user = userMapper.getUserById(id);
        if(user!=null){
          return Result.ok().data("user",user);
        }else{
//            System.out.println("hello");
            return Result.error();
        }
//        return user.getEmail();
    }

}
