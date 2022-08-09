package cn.ustc.handler;

import cn.ustc.response.Result;
import cn.ustc.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.handler
 * @Author: leixue
 * @CreateTime: 2022-04-11 11:36
 * @Description: 登出操作，
 */
@Component
public class JwtLogOutSucessHandler implements LogoutSuccessHandler {

    @Autowired
    JwtUtil jwtUtil;
    /**
     * 和登录成功基本一致
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 如果不为空，则手动退出
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        //只需要以json格式返回一个提示即可
        Result result = Result.ok().message("登出成功").data("jwt","");

//        response.setHeader(jwtUtil.getHeader(),"");

        response.setContentType("text/json;charset=utf-8");
        //放入response中返回给前台
        //使用springboot里的jackson将对象转成json的方法
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
