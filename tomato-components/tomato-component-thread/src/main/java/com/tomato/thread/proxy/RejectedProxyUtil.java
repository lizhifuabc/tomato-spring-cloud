package com.tomato.thread.proxy;

import java.lang.reflect.Proxy;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 拒绝策略执行之后的处理策略
 * @author lizhifu
 */
public class RejectedProxyUtil {

    /**
     * 代理拒绝策略，记录拒绝策略执行次数
     *
     * @param rejectedExecutionHandler
     * @param threadPoolId
     * @param rejectedNum
     * @return
     */
    public static RejectedExecutionHandler createProxy(RejectedExecutionHandler rejectedExecutionHandler, String threadPoolId, AtomicLong rejectedNum) {
        RejectedExecutionHandler rejectedProxy = (RejectedExecutionHandler) Proxy
                .newProxyInstance(
                        rejectedExecutionHandler.getClass().getClassLoader(),
                        new Class[]{RejectedExecutionHandler.class},
                        new RejectedProxyInvocationHandler(rejectedExecutionHandler, threadPoolId, rejectedNum));
        return rejectedProxy;
    }
}
