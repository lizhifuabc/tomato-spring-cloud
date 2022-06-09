package com.tomato.ratelimiter;

/**
 * 限流配置
 *
 * @author lizhifu
 * @date 2022/6/9
 */
public class RateLimiterConfig {
    /**
     * 每秒填充平均速率,每秒有多少令牌。
     */
    private int replenishRate;
    /**
     * 总容量，桶中可以容纳多少令牌
     */
    private int burstCapacity = 1;
    /**
     * 单个请求需要多少令牌
     */
    private int requestedTokens = 1;

    public int getReplenishRate() {
        return replenishRate;
    }

    public RateLimiterConfig setReplenishRate(int replenishRate) {
        this.replenishRate = replenishRate;
        return this;
    }

    public int getBurstCapacity() {
        return burstCapacity;
    }

    public RateLimiterConfig setBurstCapacity(int burstCapacity) {
        this.burstCapacity = burstCapacity;
        return this;
    }

    public int getRequestedTokens() {
        return requestedTokens;
    }

    public RateLimiterConfig setRequestedTokens(int requestedTokens) {
        this.requestedTokens = requestedTokens;
        return this;
    }

    @Override
    public String toString() {
        return "RateLimiterConfig{" +
                "replenishRate=" + replenishRate +
                ", burstCapacity=" + burstCapacity +
                ", requestedTokens=" + requestedTokens +
                '}';
    }
}
