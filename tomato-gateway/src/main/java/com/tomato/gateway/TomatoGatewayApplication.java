package com.tomato.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author lizhifu
 * @date 2022/5/12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TomatoGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(TomatoGatewayApplication.class, args);
        System.out.println("网关启动成功");
    }
}
