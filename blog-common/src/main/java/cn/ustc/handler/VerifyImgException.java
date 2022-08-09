package cn.ustc.handler;


import org.springframework.security.core.AuthenticationException;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.handler
 * @Author: leixue
 * @CreateTime: 2022-04-08 21:54
 * @Description: 验证码错误异常
 */
public class VerifyImgException extends AuthenticationException {

    public VerifyImgException(String msg) {
        super(msg);
    }
}
