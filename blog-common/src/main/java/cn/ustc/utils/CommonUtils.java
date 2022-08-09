package cn.ustc.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;

import java.awt.*;
import java.util.UUID;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.utils
 * @Author: leixue
 * @CreateTime: 2022-04-03 11:25
 * @Description: 常规工具类
 */
public class CommonUtils {

    /**
     * 生成随机数
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 生成验证码图片
     */
    public static ShearCaptcha getRandomCode(String randomCode) {
        //生成验证码图片大小
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(90, 34, 4, 3);
        //设置图片背景
        shearCaptcha.setBackground(Color.WHITE);
//        randomCode = shearCaptcha.getCode();
//        shearCaptcha.verify("")
//        return shearCaptcha.getImageBase64Data();
        return shearCaptcha;
    }


}
