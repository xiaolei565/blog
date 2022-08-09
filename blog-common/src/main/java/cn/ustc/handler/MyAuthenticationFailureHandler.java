package cn.ustc.handler;

import cn.ustc.response.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.handler
 * @Author: leixue
 * @CreateTime: 2022-04-03 16:20
 * @Description: 登录失败处理器
 */
@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //如果这里异常是账号密码异常，则json中返回账号密码不正确
//        if(exception instanceof BusinessException){
//            return response;
//        }
        //这里返回异常信息，让前端进行展示,这里的错误吗也要进行统一管理
        Result result = Result.ok().code(20001).message(exception.getMessage());
        //处理编码防止乱码
        response.setContentType("text/json;charset=utf-8");
        //否则返回登录失败
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
