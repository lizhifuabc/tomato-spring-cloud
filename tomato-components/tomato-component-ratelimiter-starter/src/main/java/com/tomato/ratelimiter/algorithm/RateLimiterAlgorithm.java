package com.tomato.ratelimiter.algorithm;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.List;

/**
 * 算法
 *
 * @author lizhifu
 * @date 2022/3/4
 */
public interface RateLimiterAlgorithm<T> {
    /**
     * 获取脚本
     * @return
     */
    RedisScript<T> getScript();

    /**
     * lua 脚本 key list
     * @param key
     * @return
     */
    List<String> getKeys(String key);

    /**
     * 脚本回调方法执行
     * @param script
     * @param keys
     * @param scriptArgs
     * @param redisTemplate
     */
    default void callback(final RedisScript<?> script, final List<String> keys, final List<String> scriptArgs, RedisTemplate<String,String> redisTemplate) {
    }
}
