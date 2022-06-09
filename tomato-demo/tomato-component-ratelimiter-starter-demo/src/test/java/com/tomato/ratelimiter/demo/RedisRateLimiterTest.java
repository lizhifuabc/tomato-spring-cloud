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
            .replenishRate(100)
            .algorithmName("ConcurrentRateLimiterAlgorithm")
            .burstCapacity(3);
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
