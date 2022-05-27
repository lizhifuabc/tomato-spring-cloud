package com.tomato.id.strategy.impl;

import com.tomato.id.strategy.IdGeneratorStrategy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * redis id生成策略
 * 时间：20220527
 * 业务编码：100
 * 递增位数：000000
 * @author lizhifu
 * @date 2022/5/27
 */
@Component
public class RedisIdGeneratorStrategy implements IdGeneratorStrategy {
    private final static String REDIS_KEY = "ID:";
    private final static String TYPE = "100";
    private final static int DIGIT = 6;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public long nextId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        Long increment = redisTemplate.opsForValue().increment(REDIS_KEY+TYPE);
        return Long.parseLong(date + TYPE + String.format("%0" + DIGIT + "d", increment));
    }
}
