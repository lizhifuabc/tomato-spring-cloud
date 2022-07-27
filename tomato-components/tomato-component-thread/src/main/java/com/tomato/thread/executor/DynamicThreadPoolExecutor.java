package com.tomato.thread.executor;

import com.tomato.thread.proxy.RejectedProxyUtil;
import com.tomato.thread.wrapper.RunnableWrapper;
import com.tomato.utils.date.SystemClock;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义线程池包装
 *
 * @author lizhifu
 * @date 2022/7/26
 */
@Slf4j
public class DynamicThreadPoolExecutor extends AbstractDynamicThreadPoolExecutor {
    /**
     * 预先启动所有核心线程
     */
    @Setter
    private boolean preStartAllCoreThreads = false;
    /**
     * 运行超时
     */
    @Getter
    @Setter
    private long runTimeout;

    /**
     * 任务队列等待超时
     */
    @Getter
    @Setter
    private long queueTimeout;

    /**
     * 运行超时任务数量
     */
    @Getter
    @Setter
    private final AtomicInteger runTimeoutCount = new AtomicInteger();

    /**
     * 队列等待超时任务数量
     */
    @Getter
    @Setter
    private final AtomicInteger queueTimeoutCount = new AtomicInteger();
    /**
     * 拒绝策略执行之后的处理策略
     */
    @Getter
    @Setter
    private RejectedExecutionHandler redundancyHandler;
    /**
     * 记录拒绝策略执行次数
     */
    @Getter
    private final AtomicLong rejectCount = new AtomicLong();
    /**
     * 记录执行开始时间
     */
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    public DynamicThreadPoolExecutor(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory,
                                     RejectedExecutionHandler handler,
                                     String threadPoolId) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory,threadPoolId);
        this.threadPoolId = threadPoolId;
        // 动态代理记录拒绝策略执行次数
        RejectedExecutionHandler rejectedProxy = RejectedProxyUtil.createProxy(handler, threadPoolId, rejectCount);
        setRejectedExecutionHandler(rejectedProxy);
        // 记录拒绝策略
        redundancyHandler = handler;
    }

    /**
     * 执行任务前记录执行开始时间
     * @param t the thread that will run task {@code r} 执行任务的线程
     * @param r the task that will be executed 将要被执行的任务
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        RunnableWrapper runnable = (RunnableWrapper) r;
        long currTime = SystemClock.now();
        runnable.setExeStartTime(currTime);
        long waitTime = currTime - runnable.getSubmitTime();
        if (waitTime > queueTimeout) {
            queueTimeoutCount.incrementAndGet();
            log.warn("{} queue timeout, wait time: {}", threadPoolId, waitTime);
        }
        super.beforeExecute(t, r);
    }

    /**
     * 执行任务后记录执行结束时间
     * @param r the runnable that has completed  已经完成执行的任务
     * @param t the exception that caused termination, or null if
     * execution completed normally
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        RunnableWrapper runnable = (RunnableWrapper) r;
        long runTime = SystemClock.now() - runnable.getExeStartTime();
        if (runTime > runTimeout) {
            runTimeoutCount.incrementAndGet();
            log.warn("{} run timeout, run time: {}", threadPoolId, runTime);
        }
        super.afterExecute(r, t);
    }
    @Override
    public void execute(Runnable command) {
        command = new RunnableWrapper(command);
        super.execute(command);
    }
    /**
     * 获取拒绝策略执行次数
     * @return
     */
    public Long getRejectCountNum() {
        return rejectCount.get();
    }

    @Override
    protected void initialize() {
        if (preStartAllCoreThreads) {
            prestartAllCoreThreads();
        }
    }
}
