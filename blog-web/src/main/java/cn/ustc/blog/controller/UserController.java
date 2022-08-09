package cn.ustc.blog.controller;


import cn.ustc.blog.entity.SysUser;
import cn.ustc.blog.entity.User;
import cn.ustc.blog.service.Impl.UserDetailsServiceImpl;
import cn.ustc.blog.service.SysUserService;
import cn.ustc.blog.service.UserService;
import cn.ustc.handler.BusinessException;
import cn.ustc.response.Result;
import cn.ustc.response.ResultCode;
import cn.ustc.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaolei565
 * @since 2022-03-29
 */
@RestController
@RequestMapping("/")
@Api(value="用户管理")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SysUserService sysUserService;

//    @Autowired
//    UserDetailsServiceImpl userDetailsService;

    @GetMapping(value ="/getuser/{id}")
    @ApiOperation("根据id查询用户信息")
    public Result getUserById(@PathVariable("id") long id){
//        int i = 1/0;
        User user = userService.getById(id);
        if(user!=null){
            return Result.ok().data("user",user);
        }else{
            throw new BusinessException(ResultCode.USER_NOT_FOUND_EXCEPTION.getCode(),ResultCode.USER_NOT_FOUND_EXCEPTION.getMessage());
        }
    }

    /**
     * get格式，而不是restful格式
     * @param
     * @return
     * @RequestParam("token") String jwt
     */
    @GetMapping(value ="/getInfo")
    @ApiOperation("根据id查询用户信息")
    public Result getUserInfo(@RequestHeader("Authorization") String jwt){
        // TODO: 2022/4/13  其实这里写错了，不能从请求参数获得，而应该从请求头获取 // 已修改
        // 不需要参数，直接根据jwt工具类进行获取
        Claims claim = jwtUtil.getClaimByToken(jwt);
        String username = claim.getSubject();
        // 获取user
        SysUser user = sysUserService.getUserByName(username);
        // 获取角色，这里先再查询一下数据库吧，
        // // TODO: 2022/4/25  可以留着进行优化，和上一个user查询直接放到一个查询里面
        String userAuthority = sysUserService.getUserAuthority(user.getId());
        // 前端要求，roles必须要以数组形式返回
        String[] roles = userAuthority.trim().split(",");

        return Result.ok().data("user",user).data("roles",roles);
    }

}

