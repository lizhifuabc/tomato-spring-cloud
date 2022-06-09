package com.tomato.ratelimiter.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 并发速率限制器算法 - 适用于瞬间并发限流
 * 每秒限流
 * 网关每秒限流
 * IP每秒限流
 *
 * zset 是 Redis 常用数据类型之一，它适用于排行榜类型的业务场景，ZADD key score member [score member ...]
 * 比如 QQ 音乐排行榜、用户贡献榜等。在音乐排行榜单中，我们可以将歌曲的点击次数作为 score 值，把歌曲的名字作为 value 值，通过对 score 排序就可以得出歌曲“热度榜单”。
 *
 * 此用例中，score 是当前时间戳（秒），value 是 UUID 生成的唯一标识，用于标识每一个请求。每次执行完成之后删除元素。
 *
 * @author lizhifu
 * @date 2022/6/9
 */
public class ConcurrentRateLimiterAlgorithm extends AbstractRateLimiterAlgorithm {
    private static final Logger log = LoggerFactory.getLogger(ConcurrentRateLimiterAlgorithm.class);
    public ConcurrentRateLimiterAlgorithm() {
        super("concurrent_rate_limiter");
    }
    /**
     * key 需要特殊定制
     * @param key
     * @return
     */
    @Override
    public List<String> getKeys(final String key) {
        String tokenKey = getScriptName() + ".{" + key + "}.tokens";
        // 随机数
        String requestKey = UUID.randomUUID().toString().replace("-","");
        return Arrays.asList(tokenKey, requestKey);
    }
    @Override
    public void callback(final RedisScript<?> script, final List<String> keys, final List<String> scriptArgs, RedisTemplate<String,String> redisTemplate) {
        redisTemplate.opsForZSet().remove(keys.get(0), keys.get(1));
        log.info("remove token from zset, key={}, requestKey={}", keys.get(0), keys.get(1));
    }
}
