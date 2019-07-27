package com.weijiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeijiangApplication {
    //内置了Tomcat服务
    public static void main(String[] args) {
        SpringApplication.run(WeijiangApplication.class, args);
    }

}
