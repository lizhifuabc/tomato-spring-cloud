package com.tomato.ratelimiter.algorithm.type;

import com.tomato.ratelimiter.algorithm.*;

import java.util.List;
import java.util.Objects;

/**
 * 算法类型
 *
 * @author lizhifu
 * @date 2022/3/4
 */
public enum AlgorithmTypeEnum {
    /**
     * 算法类型
     */
    CONCURRENT_RATE_LIMITER(1,"ConcurrentRateLimiterAlgorithm"),
    LEAKY_BUCKET_RATE_LIMITER(2,"LeakyBucketRateLimiterAlgorithm"),
    SLIDING_WINDOW_RATE_LIMITER(3,"SlidingWindowRateLimiterAlgorithm"),
    TOKEN_BUCKET_LIMITER(4,"TokenBucketRateLimiterAlgorithm");
    private final Integer code;
    private final String name;
    AlgorithmTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static RateLimiterAlgorithm<List<Long>> buildRateLimiterAlgorithm(String name) {
        RateLimiterAlgorithm<List<Long>> rateLimiterAlgorithm = null;
        if (Objects.equals(name, CONCURRENT_RATE_LIMITER.getName())) {
            rateLimiterAlgorithm = new ConcurrentRateLimiterAlgorithm();
        } else if (Objects.equals(name, LEAKY_BUCKET_RATE_LIMITER.getName())) {
            rateLimiterAlgorithm = new LeakyBucketRateLimiterAlgorithm();
        } else if (Objects.equals(name, SLIDING_WINDOW_RATE_LIMITER.getName())) {
            rateLimiterAlgorithm = new SlidingWindowRateLimiterAlgorithm();
        } else if (Objects.equals(name, TOKEN_BUCKET_LIMITER.getName())) {
            rateLimiterAlgorithm = new TokenBucketRateLimiterAlgorithm();
        }
        if (rateLimiterAlgorithm != null) {
            return rateLimiterAlgorithm;
        }
        throw new RuntimeException("不支持的算法类型： " + name);
    }
}
