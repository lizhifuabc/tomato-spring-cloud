package com.tomato.utils.exception;

import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

/**
 * 异常工具类
 *
 * @author lizhifu
 * @date 2022/5/30
 */
public class ExceptionUtils {
    public static Throwable extractRealException(Throwable throwable) {
        //这里判断异常类型是否为CompletionException、ExecutionException，如果是则进行提取，否则直接返回。
        if (throwable instanceof CompletionException || throwable instanceof ExecutionException) {
            if (throwable.getCause() != null) {
                return throwable.getCause();
            }
        }
        return throwable;
    }
}
