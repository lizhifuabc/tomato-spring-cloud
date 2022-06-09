package com.tomato.ratelimiter.algorithm.handler;

import com.tomato.ratelimiter.algorithm.RateLimiterAlgorithm;
import com.tomato.ratelimiter.algorithm.type.AlgorithmTypeEnum;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流算法
 *
 * @author lizhifu
 * @date 2022/6/9
 */
public class RateLimiterAlgorithmHandler {
    private static final Map<String, RateLimiterAlgorithm<List<Long>>> ALGORITHM_MAP = new ConcurrentHashMap<>(16);

    /**
     * 构建限流算法
     * @param algorithm 算法类型
     * @return
     */
    public static RateLimiterAlgorithm<List<Long>> getRateLimiterAlgorithm(String algorithm) {
        RateLimiterAlgorithm<List<Long>> rateLimiterAlgorithm = ALGORITHM_MAP.get(algorithm);
        if (Objects.nonNull(rateLimiterAlgorithm)) {
           return rateLimiterAlgorithm;
        }
        ALGORITHM_MAP.putIfAbsent(algorithm, AlgorithmTypeEnum.buildRateLimiterAlgorithm(algorithm));
        return ALGORITHM_MAP.get(algorithm);
    }
}
