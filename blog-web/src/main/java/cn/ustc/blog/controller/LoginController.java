package cn.ustc.blog.controller;

import cn.ustc.blog.entity.User;
import cn.ustc.blog.service.UserService;
import cn.ustc.response.Result;
import cn.ustc.response.ResultCode;
import cn.ustc.response.StatusEnum;
import cn.ustc.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.blog.controller
 * @Author: leixue
 * @CreateTime: 2022-04-03 10:13
 * @Description: 登录
 */
@RestController
//@RequestMapping("")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 注册，这里是点击注册按钮后触发
     *
     * 暂时是这样，但是后面需要返回更详细信息
     * 需要调用发送邮件连接
     * 需要用户点击链接激活用户后，才可以使用
     * 生成登录token存入redis
     */
    @PostMapping("/register")
    public Result register(User user){
        user.setStatus(0);
        user.setActivationCode(CommonUtils.generateUUID());
        //后期edit用户时进行设置，最开始可以为空，从oss中获取，默认是空，要进行一个展示
//        user.setHeaderUrl();

        boolean b = userService.save(user);
        if(b){
            return Result.ok().code(ResultCode.REGISTER_SUCCESS.getCode()).message(ResultCode.REGISTER_SUCCESS.getMessage());
        }else{
            return Result.ok().code(ResultCode.REGISTER_FAIL.getCode()).message(ResultCode.REGISTER_FAIL.getMessage());
        }
    }

    @PostMapping("/active/{id}/{activeCode}")
    public Result active(@PathVariable("id") int id,
                        @PathVariable("activeCode") long activeCode){
        //查询id
        User user = userService.getById(id);
        //如果存在
        if(user.getStatus().equals(StatusEnum.ACTIVED.getCode())){
            return Result.error().message("用户已存在");
        }else if(user.getActivationCode().equals(activeCode)){
            //开始激活用户
            user.setStatus(1);//设置为1
            boolean b = userService.updateById(user);
            if(b){

            }else{

            }
        }
        return Result.error().code(ResultCode.USER_REGISTER_FAIL.getCode()).message(ResultCode.USER_REGISTER_FAIL.getMessage());
    }


    /**
     * 登录
     * @RequestBody UserDto userDto
     * 一开始就想错了，这里不需要使用这个login，这里的工作全部交给security框架完成，包括认证等等
     */
//    @PostMapping("/blog/login")
//    public Result login(@RequestParam(value = "username") String username,
//                        @RequestParam(value = "password") String password){
//
//        User user =  userService.getOne(username, password);
//        if(user!=null){
//            return Result.ok();
//        }else{
//            return Result.error();
//        }
//    }

//    /**
//     * 获取验证码
//     */
//    @GetMapping("/getRandomCode")
//    //@RequestParam String randomCode
//    public Result getRandomCode(){
//        return Result.ok().message("生成成功").data("data",CommonUtils.getRandomCode("123"));
//    }

}
