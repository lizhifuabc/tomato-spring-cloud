package com.tomato.auth;

import com.tomato.sys.api.SysUserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 授权服务中心
 *
 * @author lizhifu
 * @date 2022/5/31
 */
@SpringBootApplication
@EnableFeignClients(basePackageClasses = {SysUserFeignClient.class})
@EnableDiscoveryClient
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
