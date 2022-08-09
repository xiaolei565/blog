package cn.ustc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//        (scanBasePackages ="cn.ustc.system.mapper" )
//mybatis plus扫描的mapper文件夹目录,必须要配置
@MapperScan("cn.ustc.blog.mapper")
@EnableOpenApi
//暂时禁用springsecurity
//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
@EnableConfigurationProperties
@ComponentScan(basePackages = "cn.ustc.*")

public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }
}
