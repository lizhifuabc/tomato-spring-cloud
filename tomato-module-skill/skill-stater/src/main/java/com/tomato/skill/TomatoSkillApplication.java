package com.tomato.id;

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
public class TomatoIdApplication {
    public static void main(String[] args) {
        SpringApplication.run(TomatoIdApplication.class, args);
        System.out.println("TomatoIdApplication 服务启动成功");
    }
}
