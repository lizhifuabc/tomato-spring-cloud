package com.tomato.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 授权服务器启动类
 *
 * @author lizhifu
 * @date 2022/5/17
 */
@SpringBootApplication
public class OAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
        System.out.println("授权服务器启动成功");
    }
}
