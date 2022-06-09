package com.tomato.ratelimiter.demo;

import com.tomato.ratelimiter.RedisRateLimiter;
import com.tomato.ratelimiter.pojo.RateLimiterReq;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * RedisRateLimiter
 *
 * @author lizhifu
 * @date 2022/6/9
 */
@SpringBootTest
public class RedisRateLimiterTest {
    public static final RateLimiterReq rateLimiterBuilder = new RateLimiterReq()
            .build()
            // 桶允许流出的数量 =（当前时间 - 上次更新时间即key2存储的值）* 速率
            .replenishRate(2)
            // 桶的容量
            .burstCapacity(2)
            // .algorithmName("ConcurrentRateLimiterAlgorithm")
            .algorithmName("LeakyBucketRateLimiterAlgorithm");

    @Resource
    RedisRateLimiter redisRateLimiter;
    @Test
    public void contextLoads() throws InterruptedException {
        Task task = new Task();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);
        Thread thread4 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(10000);
    }
    class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(redisRateLimiter.exe("127.0.0.1", rateLimiterBuilder));
        }
    }
}
