package com.tomato.example.thread.config;

import com.tomato.thread.executor.DynamicThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * 线程池配置
 * 默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
 * 当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
 *  当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
 * @author lizhifu
 * @date 2022/7/6
 */
public class ThreadPoolTaskConfig {
    public static final String TASK_EXECUTOR_POOL = "taskExecutor";
    /** 核心线程数（默认线程数） */
    private static final int corePoolSize = 20;
    /** 最大线程数 */
    private static final int maxPoolSize = 100;
    /** 允许线程空闲时间（单位：默认为秒） */
    private static final long keepAliveTime = 10;
    /** 缓冲队列大小 */
    private static final int queueCapacity = 200;
    /** 线程池名前缀 */
    private static final String threadNamePrefix = "Async-Service-";

    /**
     * 创建线程池
     * @return
     */
    public static DynamicThreadPoolExecutor taskExecutor(){
        DynamicThreadPoolExecutor executor = new DynamicThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                1L,
                true,
                5L,
                new LinkedBlockingQueue<>(queueCapacity),
                ThreadPoolTaskConfig.TASK_EXECUTOR_POOL,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }

    public static void main(String[] args) {
        DynamicThreadPoolExecutor executor = taskExecutor();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
    }
}
