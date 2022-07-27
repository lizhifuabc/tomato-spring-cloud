package com.tomato.thread.proxy;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 拒绝策略执行之后的处理策略
 * @author lizhifu
 */
@AllArgsConstructor
@Slf4j
public class RejectedProxyInvocationHandler implements InvocationHandler {

    private final Object target;

    private final String threadPoolId;
    /**
     * 记录拒绝策略执行次数
     */
    private final AtomicInteger rejectCount;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        rejectCount.incrementAndGet();
        log.warn("ThreadPool[{}] rejectCount[{}]", threadPoolId, rejectCount.get());
        try {
            return method.invoke(target, args);
        } catch (InvocationTargetException ex) {
            throw ex.getCause();
        }
    }
}
