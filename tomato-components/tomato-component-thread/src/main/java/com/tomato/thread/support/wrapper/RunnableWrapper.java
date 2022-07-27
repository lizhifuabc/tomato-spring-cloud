package com.tomato.thread.support.wrapper;

import com.tomato.utils.date.SystemClock;

/**
 * Runnable 包装
 *
 * @author lizhifu
 * @date 2022/7/27
 */
public class RunnableWrapper implements Runnable{
    /**
     * runnable
     */
    private final Runnable runnable;
    /**
     * 任务提交时间
     */
    private final Long submitTime;
    /**
     * 任务开始执行时间
     */
    private Long exeStartTime;

    public RunnableWrapper(Runnable runnable) {
        this.runnable = runnable;
        submitTime = SystemClock.now();
    }

    @Override
    public void run() {
        runnable.run();
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public Long getSubmitTime() {
        return submitTime;
    }

    public Long getExeStartTime() {
        return exeStartTime;
    }

    public void setExeStartTime(Long exeStartTime) {
        this.exeStartTime = exeStartTime;
    }
}
