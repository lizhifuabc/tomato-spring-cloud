package com.tomato.thread.executor;

import com.tomato.thread.proxy.RejectedProxyUtil;
import com.tomato.utils.date.SystemClock;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.task.TaskDecorator;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义线程池包装
 *
 * @author lizhifu
 * @date 2022/7/26
 */
@Slf4j
public class DynamicThreadPoolExecutor extends ThreadPoolExecutor implements DisposableBean {
    /**
     * 线程标识配置
     */
    @Getter
    private final String threadPoolId;
    /**
     * 执行超时时间配置
     */
    @Getter
    @Setter
    private Long executeTimeOut;
    @Getter
    @Setter
    private TaskDecorator taskDecorator;
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
    /**
     * 等待任务完成
     */
    public boolean waitForTasksToCompleteOnShutdown;
    /**
     * 应用关闭时，如果线程池还有未执行完的任务，关闭线程池的等待时间
     */
    public long awaitTerminationMillis;
    public DynamicThreadPoolExecutor(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     long executeTimeOut,
                                     boolean waitForTasksToCompleteOnShutdown,
                                     long awaitTerminationMillis,
                                     @NonNull BlockingQueue<Runnable> workQueue,
                                     @NonNull String threadPoolId,
                                     @NonNull ThreadFactory threadFactory,
                                     @NonNull RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.threadPoolId = threadPoolId;
        this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;
        this.awaitTerminationMillis = awaitTerminationMillis;
        this.executeTimeOut = executeTimeOut;
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
        // 案例1 ：executeTimeOut ：变更前 1，变更后 0
        // 案例2 ：executeTimeOut ：变更前 0，变更后 1
        if (executeTimeOut == null || executeTimeOut <= 0) {
            return;
        }
        // TODO 超时时间配置如果动态更改，可能会出现内存泄露
        // 案例1 ：executeTimeOut ：变更前 1
        // 案例2 ：executeTimeOut ：变更前 0，此时startTime空
        this.startTime.set(SystemClock.now());
    }

    /**
     * 执行任务后记录执行结束时间
     * @param r the runnable that has completed  已经完成执行的任务
     * @param t the exception that caused termination, or null if
     * execution completed normally
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        // 案例1 ：executeTimeOut ：变更后 0，此时直接返回了，不会执行 this.startTime.remove();
        // 案例2 ：executeTimeOut ：变更后 1， long startTime = this.startTime.get(); NPE
        // TODO 超时时间配置如果动态更改，可能会出现内存泄露
        if (executeTimeOut == null || executeTimeOut <= 0) {
            return;
        }
        try {
            long startTime = this.startTime.get();
            long endTime = SystemClock.now();
            long executeTime;
            boolean executeTimeAlarm = (executeTime = (endTime - startTime)) > executeTimeOut;
        } finally {
            // 清空执行开始时间
            this.startTime.remove();
        }
    }
    @Override
    public void execute(@NonNull Runnable command) {
        if (taskDecorator != null) {
            command = taskDecorator.decorate(command);
        }
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
    public void destroy() throws Exception {
        if (this.waitForTasksToCompleteOnShutdown) {
            this.shutdown();
        } else {
            for (Runnable remainingTask : this.shutdownNow()) {
                cancelRemainingTask(remainingTask);
            }
        }
        awaitTerminationIfNecessary(this);
    }
    /**
     * Cancel the given remaining task which never commended execution,
     * as returned from {@link ExecutorService#shutdownNow()}.
     *
     * @param task the task to cancel (typically a {@link RunnableFuture})
     * @see #shutdown()
     * @see RunnableFuture#cancel(boolean)
     * @since 5.0.5
     */
    protected void cancelRemainingTask(Runnable task) {
        if (task instanceof Future) {
            ((Future<?>) task).cancel(true);
        }
    }
    /**
     * Wait for the executor to terminate, according to the value of the.
     */
    private void awaitTerminationIfNecessary(ExecutorService executor) {
        if (this.awaitTerminationMillis > 0) {
            try {
                if (!executor.awaitTermination(this.awaitTerminationMillis, TimeUnit.MILLISECONDS)) {
                    if (log.isWarnEnabled()) {
                        log.warn("Timed out while waiting for executor" +
                                (this.threadPoolId != null ? " '" + this.threadPoolId + "'" : "") + " to terminate.");
                    }
                }
            } catch (InterruptedException ex) {
                if (log.isWarnEnabled()) {
                    log.warn("Interrupted while waiting for executor" +
                            (this.threadPoolId != null ? " '" + this.threadPoolId + "'" : "") + " to terminate.");
                }
                Thread.currentThread().interrupt();
            }
        }
    }
}
