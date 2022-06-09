package com.tomato.ratelimiter.pojo;

import com.tomato.data.dto.req.Req;

/**
 * RateLimiterReq
 *
 * @author lizhifu
 * @date 2022/6/9
 */
public class RateLimiterReq extends Req {
    /**
     * 算法
     */
    private String algorithmName;

    /**
     * 补充速率,每秒允许的请求数量
     */
    private double replenishRate;

    /**
     * 总容量
     */
    private double burstCapacity;

    /**
     * 请求数量
     */
    private double requestCount = 1.0;

    public RateLimiterReq build() {
        return new RateLimiterReq();
    }
    public RateLimiterReq requestCount(double requestCount) {
        this.requestCount = requestCount;
        return this;
    }
    public RateLimiterReq replenishRate(double replenishRate) {
        this.replenishRate = replenishRate;
        return this;
    }
    public RateLimiterReq burstCapacity(double burstCapacity) {
        this.burstCapacity = burstCapacity;
        return this;
    }
    public RateLimiterReq algorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
        return this;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public double getReplenishRate() {
        return replenishRate;
    }

    public double getBurstCapacity() {
        return burstCapacity;
    }

    public double getRequestCount() {
        return requestCount;
    }

    @Override
    public String toString() {
        return "RateLimiterReq{" +
                "algorithmName='" + algorithmName + '\'' +
                ", replenishRate=" + replenishRate +
                ", burstCapacity=" + burstCapacity +
                ", requestCount=" + requestCount +
                '}';
    }
}
