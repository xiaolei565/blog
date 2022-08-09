package cn.ustc.handler;

import cn.ustc.response.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.handler
 * @Author: leixue
 * @CreateTime: 2022-04-11 23:11
 * @Description:
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        Result result = Result.error().code(20005).message("请先登录");

        // 处理编码防止乱码
        response.setContentType("text/json;charset=utf-8");
        // 权限不足返回status
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 否则返回登录失败
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
