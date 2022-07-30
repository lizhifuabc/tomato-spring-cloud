package com.tomato.web.controller;

import com.tomato.web.utils.BeanUtilsWrapper;
/**
 * 基础 controller
 *
 * @author lizhifu
 * @date 2021/12/9
 */
public abstract class BaseController {
    /**
     * copy对象方法
     * @param t 需要copy的对象
     * @param s 转换后的对象
     * @param <T>
     * @param <S>
     * @return
     */
    protected <T, S> S copyToDo(T t, S s) {
       return BeanUtilsWrapper.copyToDo(t, s);
    }
}
