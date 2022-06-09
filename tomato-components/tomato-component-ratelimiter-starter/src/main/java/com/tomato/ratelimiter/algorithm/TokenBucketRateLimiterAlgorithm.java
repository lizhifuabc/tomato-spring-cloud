package com.tomato.ratelimiter.algorithm;

/**
 * 令牌桶速率限制器算法，保证自己的系统不被打垮
 * 1）所有的请求在处理之前都需要拿到一个可用的令牌才会被处理；
 * 2）根据限流大小，设置按照一定的速率往桶里添加令牌；
 * 3）桶设置最大的放置令牌限制，当桶满时、新添加的令牌就被丢弃或者拒绝；
 * 4）请求达到后首先要获取令牌桶中的令牌，拿着令牌才可以进行其他的业务逻辑，处理完业务逻辑之后，将令牌直接删除；
 * 5）令牌桶有最低限额，当桶中的令牌达到最低限额的时候，请求处理完之后将不会删除令牌，以此保证足够的限流
 *
 * 优点：有效利用网络带宽，允许一定程度的流量突发。
 * 缺点：临界突发流量。
 *
 * @author lizhifu
 * @date 2022/6/9
 */
public class TokenBucketRateLimiterAlgorithm extends AbstractRateLimiterAlgorithm {
    public TokenBucketRateLimiterAlgorithm() {
        super("token_bucket_rate_limiter");
    }
}
