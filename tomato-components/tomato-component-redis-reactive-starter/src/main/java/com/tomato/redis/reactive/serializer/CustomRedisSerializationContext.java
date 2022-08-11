package com.tomato.redis.reactive.serializer;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 序列化配置
 *
 * @author lizhifu
 * @date 2022/8/11
 */
public class CustomRedisSerializationContext implements RedisSerializationContext {
    private StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    private GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

    /**
     * 设置key的序列化方式
      * @return
     */
    @Override
    public SerializationPair getKeySerializationPair() {
        return SerializationPair.fromSerializer(stringRedisSerializer);
    }

    /**
     * 设置value的序列化方式
     * @return
     */
    @Override
    public SerializationPair getValueSerializationPair() {
        return SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer);
    }

    /**
     * 设置spring字符串的序列化方式
     * @return
     */
    @Override
    public SerializationPair<String> getStringSerializationPair() {
        return SerializationPair.fromSerializer(stringRedisSerializer);
    }

    /**
     * 设置hashkey的序列化方式
     * @return
     */
    @Override
    public SerializationPair getHashValueSerializationPair() {
        return SerializationPair.fromSerializer(stringRedisSerializer);
    }

    /**
     * 设置hashvalue的序列化方式
     * @return
     */
    @Override
    public SerializationPair getHashKeySerializationPair() {
        return SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer);
    }
}
