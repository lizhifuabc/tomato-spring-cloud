package com.tomato.spi;

import java.lang.annotation.*;

/**
 * Join
 * 将此注释添加到类中表示加入了扩展机制。
 *
 * @author lizhifu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Join {
}
