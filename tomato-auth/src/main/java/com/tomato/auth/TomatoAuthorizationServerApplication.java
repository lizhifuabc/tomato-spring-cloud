package com.tomato.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 授权服务中心
 *
 * @author lizhifu
 * @date 2022/5/31
 */
@SpringBootApplication
@Slf4j
public class TomatoAuthorizationServerApplication {
    public static void main(String[] args) {
        log.info("Begin to start Spring Boot Application");
        long startTime = System.currentTimeMillis();
        SpringApplication.run(TomatoAuthorizationServerApplication.class, args);
        long endTime = System.currentTimeMillis();
        log.info("End starting Spring Boot Application, Time used: "+ (endTime - startTime) );
    }
}
