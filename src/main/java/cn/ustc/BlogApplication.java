package cn.ustc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//添加SpringBoot启动类注解
@ComponentScan(basePackages = {"cn.ustc.system.*"})
@SpringBootApplication
//mybatis plus扫描的mapper文件夹目录
//@MapperScan("cn.ustc.system.mapper")

public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }
}
