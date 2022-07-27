package com.tomato.thread.support.convert;

import com.tomato.thread.dto.ThreadPoolInfoRep;
import com.tomato.thread.executor.DynamicThreadPoolExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程信息转换
 *
 * @author lizhifu
 * @date 2022/7/27
 */
public class ThreadPoolInfoConvert {
    private ThreadPoolInfoConvert() {}

    /**
     * 转换线程池信息
     * @param executor 线程池
     * @return
     */
    public static ThreadPoolInfoRep convert(DynamicThreadPoolExecutor executor) {
        ThreadPoolInfoRep threadPoolInfoRep = convertCommon(executor);
        threadPoolInfoRep.setThreadPoolId(executor.getThreadPoolId());
        threadPoolInfoRep.setRejectHandlerName(executor.getRedundancyHandler().getClass().getSimpleName());
        threadPoolInfoRep.setRejectCount(executor.getRejectCount());
        threadPoolInfoRep.setRunTimeoutCount(executor.getRunTimeoutCount());
        threadPoolInfoRep.setQueueTimeoutCount(executor.getQueueTimeoutCount());
        threadPoolInfoRep.setDynamic(true);
        return threadPoolInfoRep;
    }
    /**
     * 转换线程池信息
     *
     * @param executor 线程池
     * @return 线程池信息
     */
    public static ThreadPoolInfoRep convertCommon(ThreadPoolExecutor executor) {
        return ThreadPoolInfoRep.builder()
                .corePoolSize(executor.getCorePoolSize())
                .maximumPoolSize(executor.getMaximumPoolSize())
                .poolSize(executor.getPoolSize())
                .activeCount(executor.getActiveCount())
                .taskCount(executor.getTaskCount())
                .queueType(executor.getQueue().getClass().getSimpleName())
                .queueCapacity(executor.getQueue().size() + executor.getQueue().remainingCapacity())
                .queueSize(executor.getQueue().size())
                .queueRemainingCapacity(executor.getQueue().remainingCapacity())
                .completedTaskCount(executor.getCompletedTaskCount())
                .largestPoolSize(executor.getLargestPoolSize())
                .waitTaskCount(executor.getQueue().size())
                .build();
    }
}
