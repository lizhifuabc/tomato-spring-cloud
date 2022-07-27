package com.tomato.thread.executor;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * 通过 spring 生命周期管理线程池
 * Spring 容器关闭前尽可能多的处理队列中的任务
 * {@link ThreadPoolTaskExecutor}
 * @author lizhifu
 * @date 2022/7/27
 */
@Slf4j
public abstract class AbstractDynamicThreadPoolExecutor extends ThreadPoolExecutor implements InitializingBean, DisposableBean {
    /**
     * 线程标识配置
     */
    @Getter
    @Setter
    protected String threadPoolId;

    /**
     * true,SHUTDOWN：关闭状态，不再接受新任务提交，但是会将已保存在任务队列中的任务处理完。
     * false,STOP：停止状态，不再接受新任务提交，并且会中断当前正在执行的任务、放弃任务队列中已有的任务。
     */
    @Setter
    protected boolean waitForTasksToCompleteOnShutdown = false;

    /**
     * 等待剩余任务完成执行的时间
     */
    @Setter
    protected int awaitTerminationSeconds = 0;

    public AbstractDynamicThreadPoolExecutor(int corePoolSize,
                               int maximumPoolSize,
                               long keepAliveTime,
                               TimeUnit unit,
                               BlockingQueue<Runnable> workQueue,
                               ThreadFactory threadFactory,
                               String threadPoolId) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        this.threadPoolId = threadPoolId;
    }

    @Override
    public void destroy() {
        internalShutdown();
    }

    @Override
    public void afterPropertiesSet() {
        initialize();
    }
    /**
     * spring 初始化完成后调用
     */
    protected abstract void initialize();
    /**
     * Perform a shutdown on the underlying ExecutorService.
     * @see java.util.concurrent.ExecutorService#shutdown()
     * @see java.util.concurrent.ExecutorService#shutdownNow()
     */
    public void internalShutdown() {
        if (log.isInfoEnabled()) {
            log.info("Shutting down ExecutorService, poolName: {}", threadPoolId);
        }
        if (this.waitForTasksToCompleteOnShutdown) {
            // SHUTDOWN：关闭状态，不再接受新任务提交，但是会将已保存在任务队列中的任务处理完。
            this.shutdown();
        } else {
            // STOP：停止状态，不再接受新任务提交，并且会中断当前正在执行的任务、放弃任务队列中已有的任务。
            for (Runnable remainingTask : this.shutdownNow()) {
                // 取消任务队列中的剩余任务
                cancelRemainingTask(remainingTask);
            }
        }
        // 指定一个最大等待时间，保证线程池最终一定可以被停止下来
        awaitTerminationIfNecessary();
    }

    /**
     * 取消任务队列中的剩余任务
     * Cancel the given remaining task which never commended execution,
     * as returned from {@link ExecutorService#shutdownNow()}.
     * @param task the task to cancel (typically a {@link RunnableFuture})
     * @since 5.0.5
     * @see #shutdown()
     * @see RunnableFuture#cancel(boolean)
     */
    protected void cancelRemainingTask(Runnable task) {
        if (task instanceof Future) {
            ((Future<?>) task).cancel(true);
        }
    }

    /**
     * Wait for the executor to terminate, according to the value of the
     * {@link #setAwaitTerminationSeconds "awaitTerminationSeconds"} property.
     */
    private void awaitTerminationIfNecessary() {
        if (this.awaitTerminationSeconds <= 0) {
            return;
        }
        try {
            // TERMINATED：销毁状态，当执行完线程池的 terminated() 方法之后就会变为此状态。
            if (!awaitTermination(this.awaitTerminationSeconds, TimeUnit.SECONDS) && log.isWarnEnabled()) {
                log.warn("Timed out while waiting for executor {} to terminate", threadPoolId);
            }
        } catch (InterruptedException ex) {
            if (log.isWarnEnabled()) {
                log.warn("Interrupted while waiting for executor {} to terminate", threadPoolId);
            }
            Thread.currentThread().interrupt();
        }
    }
}
