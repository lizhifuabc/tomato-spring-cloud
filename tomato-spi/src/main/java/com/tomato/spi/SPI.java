package com.tomato.spi;

import java.lang.annotation.*;

/**
 * dubbo spi:标记接口，声明该接口是一个扩展点，可以被多个插件扩展。
 * 扩展接口，都需要在接口上加@SPI注解
 *
 * {@link ExtensionLoader}
 * {@link ExtensionFactory}
 *
 * @author lizhifu
 * @date 2022/8/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SPI {
    /**
     * value
     * @return
     */
    String value() default "";
}
