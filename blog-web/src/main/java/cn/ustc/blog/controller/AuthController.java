package cn.ustc.blog.controller;

import cn.hutool.captcha.ShearCaptcha;
import cn.ustc.response.Const;
import cn.ustc.response.Result;
import cn.ustc.utils.CommonUtils;
import cn.ustc.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.blog.controller
 * @Author: leixue
 * @CreateTime: 2022-04-06 19:31
 * @Description:
 */
@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    RedisUtil redisUtil;

    /**
     * 获取验证码，并将其存入redis
     * @return
     * @throws IOException
     */
    @GetMapping("/getverifyimg")
    public Result captcha() throws IOException {
        // 生成key，随机数
        String key = CommonUtils.generateUUID();
        // 生成验证码
        ShearCaptcha shearCaptcha = CommonUtils.getRandomCode(key);
        // 获取验证码中的code
        String code = shearCaptcha.getCode();
        // 将图片转成base64编码格式
        String verifyimg = shearCaptcha.getImageBase64Data();
        // 这里将key和code存入redis
        redisUtil.hset(Const.VERIFYIMG,key,code,120);
        // 返回key，以及 base64的验证码图片
        return Result.ok().code(20000).message("验证码生成成功").data("token",key).data("verifyImg",verifyimg);
    }

}
