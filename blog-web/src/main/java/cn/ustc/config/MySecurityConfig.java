package cn.ustc.config;

import cn.ustc.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.config
 * @Author: leixue
 * @CreateTime: 2022-04-03 15:32
 * @Description: Spring Security配置
 * 要重写三个configure方法
 */
@Configuration
@EnableWebSecurity
// 哪些方法需要用到权限
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] URL_WHITELIST = {

            "/blog/login",
            "/blog/user/logout",
            "/login",
            "/getverifyimg",
            "/favicon.ico",
            "/getInfo"
    };

    /**
     * 引入成功和失败处理器
     */
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private JwtLogOutSucessHandler jwtLogOutSucessHandler;

    /**注入自定义验证码过滤器
     * */
    @Autowired
    private VerifyImgFilter verifyImgFilter;

    /**
     * 错误入口
     */
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    /**
     * 注入userservice
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * 通过auth可以在内存里构建虚拟的用户和密码
     * @param
     * @throws Exception
     * 重写userdetail
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 在这里将框架里角色的默认前缀去掉
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1. CSRF - Cross-Site Request Forgery - 跨站请求伪造
        // 2. CORS - Cross Origin Resourse-Sharing - 跨站资源共享
        // 关闭跨域
        http.cors().and().csrf().disable();
        // 登录配置
        http.formLogin()
                //配置成功处理器
                .successHandler(myAuthenticationSuccessHandler)
                //配置失败处理器
                .failureHandler(myAuthenticationFailureHandler);

        //禁用session
        http.sessionManagement()
                // 策略为不生产session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //配置拦截规则
        http.authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated();
        //配置异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);

        //配置退出
        http.logout()
                .logoutSuccessHandler(jwtLogOutSucessHandler);

        // 配置自定义的过滤器，以及放置位置
		http
                .addFilter(jwtAuthenticationFilter())
                //验证码过滤器要放在用户名密码验证前
                .addFilterBefore(verifyImgFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
