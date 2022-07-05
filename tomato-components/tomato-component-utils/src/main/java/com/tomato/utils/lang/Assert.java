package com.tomato.utils.lang;

import java.util.function.Supplier;

/**
 * 断言<br>
 * 断言某些对象或值是否符合规定，否则抛出异常。经常用于做变量检查
 * @author lizhifu
 * @date 2022/7/5
 */
public class Assert {
    /**
     * 断言是否为假，如果为 {@code true} 抛出指定类型异常<br>
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     *  Assert.isFalse(i &gt; 0, ()-&gt;{
     *      // to query relation message
     *      return new IllegalArgumentException("relation message to return");
     *  });
     * </pre>
     *
     * @param <X>           异常类型
     * @param expression    布尔值
     * @param errorSupplier 指定断言不通过时抛出的异常
     * @throws X if expression is {@code false}
     * @since 5.4.5
     */
    public static <X extends Throwable> void isFalse(boolean expression, Supplier<X> errorSupplier) throws X {
        if (expression) {
            throw errorSupplier.get();
        }
    }
}
