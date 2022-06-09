package com.tomato.ratelimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;

/**
 * 自定义自动配置类
 *
 * @author lizhifu
 * @date 2022/6/9
 */
@Configuration
public class RedisRateLimiterAutoConfig{
    private static final Logger log = LoggerFactory.getLogger(RedisRateLimiter.class);
    @Bean
    RedisRateLimiter redisRateLimiter(RedisTemplate<String,String> redisTemplate){
        return new RedisRateLimiter(redisTemplate);
    }
    @PostConstruct
    public void init(){
        log.info("RedisRateLimiterAutoConfig init");
    }
}
