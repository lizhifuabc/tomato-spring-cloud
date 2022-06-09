package com.tomato.ratelimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;

/**
 * 自定义自动配置类
 *
 * @author lizhifu
 * @date 2022/6/9
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(RedisTemplate.class)
public class RedisRateLimiterAutoConfig{
    private static final Logger log = LoggerFactory.getLogger(RedisRateLimiter.class);
    @Bean
    RedisRateLimiter redisRateLimiter(RedisTemplate<String,String> redisTemplate){
        return new RedisRateLimiter(redisTemplate);
    }
    @Bean
    @SuppressWarnings("unchecked")
    public RedisScript slidingWindowRateLimiterAlgorithm() {
        DefaultRedisScript redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(
                new ResourceScriptSource(new ClassPathResource("META-INF/scripts/sliding_window_rate_limiter.lua")));
        redisScript.setResultType(List.class);
        return redisScript;
    }
}
