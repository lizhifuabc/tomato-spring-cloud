package com.tomato.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author lizhifu
 * @date 2022/5/27
 */
@SpringBootApplication
//@EnableDiscoveryClient
@Slf4j
public class TomatoAccountApplication {
    public static void main(String[] args) {
        log.info("Begin to start Spring Boot Application");
        long startTime = System.currentTimeMillis();
        SpringApplication.run(TomatoAccountApplication.class, args);
        long endTime = System.currentTimeMillis();
        log.info("End starting Spring Boot Application, Time used: "+ (endTime - startTime) );
    }
}
