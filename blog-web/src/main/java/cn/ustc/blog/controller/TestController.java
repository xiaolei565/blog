package cn.ustc.blog.controller;

import cn.ustc.blog.service.SysUserService;
import cn.ustc.blog.service.UserService;
import cn.ustc.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.blog.controller
 * @Author: leixue
 * @CreateTime: 2022-04-11 09:39
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //这个表示只有admin的角色才能对其进行管理
    @PreAuthorize("hasRole('admin')")
//    @PreAuthorize("hasAuthority()")
    @GetMapping("/test")
    public Result test(){
        return Result.ok().data("roles",sysUserService.list());
    }


    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/test/pass")
    public Result pass(){
        String  password = bCryptPasswordEncoder.encode("111111");
        boolean matches = bCryptPasswordEncoder.matches("111111",password);
        System.out.println(matches);
        return Result.ok().data("password",password);
    }

}
