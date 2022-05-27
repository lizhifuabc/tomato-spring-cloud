package com.tomato.web.annotation;

import java.lang.annotation.*;

/**
 * 接口版本控制
 *
 * @author lizhifu
 * @date 2021/12/9
 */
@Target({ElementType.METHOD,ElementType.TYPE})//用于方法和类上
@Retention(RetentionPolicy.RUNTIME) //运行时有效
@Documented
public @interface ApiVersion {
    /**
     * 版本：默认 1
     * @return
     */
    double value() default 1;
}
