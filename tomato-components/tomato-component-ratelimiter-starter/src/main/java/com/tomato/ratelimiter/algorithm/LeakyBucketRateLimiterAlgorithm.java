package com.tomato.ratelimiter.algorithm;

/**
 * TODO
 *
 * @author lizhifu
 * @date 2022/6/9
 */
public class LeakyBucketRateLimiterAlgorithm extends AbstractRateLimiterAlgorithm {
    public LeakyBucketRateLimiterAlgorithm() {
        super("leaky_bucket_rate_limiter");
    }
}
