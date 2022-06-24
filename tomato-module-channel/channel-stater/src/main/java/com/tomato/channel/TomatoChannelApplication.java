package com.tomato.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author lizhifu
 * @date 2022/5/27
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class TomatoChannelApplication {
    public static void main(String[] args) {
        log.info("Begin to start Spring Boot Application");
        long startTime = System.currentTimeMillis();
        SpringApplication.run(TomatoChannelApplication.class, args);
        long endTime = System.currentTimeMillis();
        log.info("End starting Spring Boot Application, Time used: "+ (endTime - startTime) );
    }
}
