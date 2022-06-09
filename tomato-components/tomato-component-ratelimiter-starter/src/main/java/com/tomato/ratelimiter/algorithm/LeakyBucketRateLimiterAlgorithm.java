package com.tomato.ratelimiter.algorithm;

/**
 * 漏桶速率限制器算法，保证别人的系统不被打垮
 * 漏桶限制的是常量流出速率（即流出速率是一个固定常量值，比如都是1的速率流出，而不能一次是1，下次又是2），从而平滑突发流入速率
 * ⽔（请求）先进⼊到漏桶⾥，漏桶以⼀定的速度出⽔，当⽔流⼊速度过⼤会直接溢出（拒绝服务）
 *
 * SETEX：为指定的 key 设置值及其过期时间。如果 key 已经存在， SETEX 命令将会替换旧的值
 *
 * 桶允许流出的数量 =（当前时间 - 上次更新时间即key2存储的值）* 速率
 * 桶存储时间即XX时间内允许请求的数量（key 过期时间） = 容量 / 速率
 * 例如：1s内允许请求的数量为2，那么需要设置容量为2，速率为2，那么桶存储时间为1s
 * {} 内的值为 传入的 key 值
 * key1 = leaky_bucket_rate_limiter.{127.0.0.1}.tokens，用于记录当前已经请求的总数
 * key2 = leaky_bucket_rate_limiter.{127.0.0.1}.timestamp，用于记录当前时间戳
 *
 * @author lizhifu
 * @date 2022/6/9
 */
public class LeakyBucketRateLimiterAlgorithm extends AbstractRateLimiterAlgorithm {
    public LeakyBucketRateLimiterAlgorithm() {
        super("leaky_bucket_rate_limiter");
    }
}
