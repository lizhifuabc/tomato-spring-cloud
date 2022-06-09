package com.tomato.ratelimiter;

import com.tomato.data.dto.rep.SingleResponse;
import com.tomato.ratelimiter.algorithm.RateLimiterAlgorithm;
import com.tomato.ratelimiter.algorithm.handler.RateLimiterAlgorithmHandler;
import com.tomato.ratelimiter.pojo.RateLimiterRep;
import com.tomato.ratelimiter.pojo.RateLimiterReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * 算法执行
 *
 * @author lizhifu
 * @date 2022/6/9
 */
public class RedisRateLimiter {
    private static final Logger log = LoggerFactory.getLogger(RedisRateLimiter.class);
    private final RedisTemplate<String,String> redisTemplate;

    public RedisRateLimiter(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public SingleResponse<RateLimiterRep> exe(final String key, RateLimiterReq rateLimiterReq) {
        log.info("限流执行，key：{}，执行算法：{}，执行参数：{}",key,rateLimiterReq.getAlgorithmName(),rateLimiterReq);
        double replenishRate = rateLimiterReq.getReplenishRate();
        double burstCapacity = rateLimiterReq.getBurstCapacity();
        double requestCount = rateLimiterReq.getRequestCount();
        RateLimiterAlgorithm<List<Long>> rateLimiterAlgorithm = RateLimiterAlgorithmHandler.getRateLimiterAlgorithm(rateLimiterReq.getAlgorithmName());
        RedisScript<List<Long>> script = rateLimiterAlgorithm.getScript();
        // key 组合之后如下
        // 1 concurrent_rate_limiter.{test}.tokens
        // 2 concurrent_rate_limiter.{test}.timestamp
        List<String> keys = rateLimiterAlgorithm.getKeys(key);
        // 参数如下
        // 1 replenishRate 补充速率
        // 2 burstCapacity 总容量
        // 3 timestamp 时间戳
        // 4 requestCount 请求数，默认 1
        List<String> scriptArgs = Arrays.asList(doubleToString(replenishRate),  doubleToString(burstCapacity), doubleToString(Instant.now().getEpochSecond()), doubleToString(requestCount));
        log.info("限流执行，keys：{}，scriptArgs：{}",keys,scriptArgs);
        // 第一个参数是是否被限流，第二个参数是剩余令牌数
        List<Long> rateLimitResponse = redisTemplate.execute(script,keys,scriptArgs.toArray());
        RateLimiterRep response = new RateLimiterRep();
        response.setPass(rateLimitResponse.get(0) == 1);
        response.setLeftTokenCount(rateLimitResponse.get(1));
        log.info("限流执行，key：{}，执行算法：{}，执行结果：{}",key,rateLimiterReq.getAlgorithmName(),response);
        rateLimiterAlgorithm.callback(script,keys,scriptArgs,redisTemplate);
        return SingleResponse.of(response);
    }
    private String doubleToString(final double param) {
        return String.valueOf(param);
    }
}
