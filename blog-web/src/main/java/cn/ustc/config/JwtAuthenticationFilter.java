package cn.ustc.config;

import cn.hutool.core.util.StrUtil;
import cn.ustc.blog.entity.SysUser;
import cn.ustc.blog.service.Impl.UserDetailsServiceImpl;
import cn.ustc.blog.service.SysUserService;
import cn.ustc.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.handler
 * @Author: leixue
 * @CreateTime: 2022-04-09 14:57
 * @Description: jwt认证过滤器
 */
//@Configuration
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    SysUserService sysUserService;

//    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwt = request.getHeader(jwtUtil.getHeader());
        // 如果jwt为空，直接到下一个过滤器
        if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request, response);
            return;
        }
        // 解析
        Claims claim = jwtUtil.getClaimByToken(jwt);
        if (claim == null) {
            throw new JwtException("token 异常");
        }
        if (jwtUtil.isTokenExpired(claim)) {
            throw new JwtException("token 已过期");
        }
        String username = claim.getSubject();
        //获取user
        SysUser user = sysUserService.getUserByName(username);
//        System.out.println(userDetailsService.getAuthorities(user.getId()));

        // 注意这个token不是原来后端生成的token，而是重新生成的
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, userDetailsService.getAuthorities(user.getId()));
        // 根据上下文设置认证主体
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 下一个过滤器
        chain.doFilter(request,response);
    }
}
