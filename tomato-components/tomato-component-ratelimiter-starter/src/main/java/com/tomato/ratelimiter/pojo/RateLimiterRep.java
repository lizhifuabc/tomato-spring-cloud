package com.tomato.ratelimiter.pojo;

/**
 * 限流返回
 *
 * @author lizhifu
 * @date 2022/6/9
 */
public class RateLimiterRep {
    /**
     * 是否限流
     */
    private boolean pass;
    /**
     * 余令牌数量
     */
    private long leftTokenCount;

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public long getLeftTokenCount() {
        return leftTokenCount;
    }

    public void setLeftTokenCount(long leftTokenCount) {
        this.leftTokenCount = leftTokenCount;
    }

    @Override
    public String toString() {
        return "RateLimiterRep{" +
                "pass=" + pass +
                ", leftTokenCount=" + leftTokenCount +
                '}';
    }
}
