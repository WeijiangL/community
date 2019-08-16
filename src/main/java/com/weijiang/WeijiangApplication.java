package com.weijiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.weijiang.mapper")
public class WeijiangApplication {
    //内置了Tomcat服务
    public static void main(String[] args) {
        SpringApplication.run(WeijiangApplication.class, args);
    }

}
