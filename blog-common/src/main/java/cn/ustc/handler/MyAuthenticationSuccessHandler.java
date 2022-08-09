package cn.ustc.handler;

import cn.ustc.response.Result;
import cn.ustc.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.handler
 * @Author: leixue
 * @CreateTime: 2022-04-03 15:39
 * @Description: 登录处理类
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtUtil jwtUtil;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

//        authentication.getPrincipal()//返回userdetail
        // 生成jwt
        String jwt = jwtUtil.generateToken(authentication.getName());
        // 放入返回头里的authorization字段里
//        response.setHeader(jwtUtil.getHeader(),jwt);
        // 以统一返回体进行返回
        Result result = Result.ok().code(20000).message("登录成功").data("jwt",jwt);

        //处理编码防止乱码
        response.setContentType("text/json;charset=utf-8");
        // 将result放入response中返回
        //使用jackson将对象转成json的方法
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
