package com.tomato.redis.reactive;

import com.tomato.redis.reactive.serializer.CustomRedisSerializationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

/**
 * 模板对象，使用自定义的序列化方式
 *
 * @author lizhifu
 * @date 2022/8/11
 */
@Configuration
public class RedisConfig {
    @Bean
    public CustomRedisSerializationContext customRedisSerializationContext() {
        return new CustomRedisSerializationContext();
    }
    /**
     * 创建模板对象，使用自定义的序列化方式
     * @param factory
     * @param context 序列化方式
     * @return
     */
    @Bean
    public ReactiveRedisTemplate reactiveRedisTemplate(ReactiveRedisConnectionFactory factory, CustomRedisSerializationContext context){
        return new ReactiveRedisTemplate(factory, context);
    }
}
