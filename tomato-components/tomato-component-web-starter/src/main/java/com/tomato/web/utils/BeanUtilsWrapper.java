package com.tomato.web.utils;

import org.springframework.beans.BeanUtils;

/**
 * BeanUtils
 *
 * @author lizhifu
 * @date 2022/7/30
 */
public class BeanUtilsWrapper {
    /**
     * copy对象方法
     * @param source 需要copy的对象
     * @param target 转换后的对象
     * @param <T>
     * @param <S>
     * @return
     */
    public static  <T, S> S copyToDo(T source, S target) {
        if (source == null) {
            target = null;
        }else {
            BeanUtils.copyProperties(source, target);
        }
        return target;
    }
}
