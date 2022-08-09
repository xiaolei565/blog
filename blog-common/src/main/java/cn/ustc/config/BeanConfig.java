package cn.ustc.config;

//import cn.ustc.handler.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject: blog
 * @BelongsPackage: cn.ustc.config
 * @Author: leixue
 * @CreateTime: 2022-04-11 22:36
 * @Description:
 */
public class BeanConfig {

    /**
     * 托管自定义的jwtfilter
     * 因为重写了构造器，所以要进行重新托管，而不能直接注入实例
     */
//    @Bean
//    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
//        return jwtAuthenticationFilter;
//    }
}
