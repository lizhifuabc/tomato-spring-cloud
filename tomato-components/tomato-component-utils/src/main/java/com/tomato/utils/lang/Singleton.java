package com.tomato.utils.lang;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例类<br>
 *
 * @author lizhifu
 * @date 2022/7/28
 */
public enum Singleton {
    /**
     * 单例对象
     */
    INST;
    /**
     * 单例对象池
     */
    private static final Map<String, Object> SINGLES = new ConcurrentHashMap<>();

    /**
     * 单例对象
     * @param clazz 单例对象类型
     * @param o 单例对象
     */
    public void single(final Class<?> clazz, final Object o) {
        SINGLES.put(clazz.getName(), o);
    }

    /**
     * 获取单例对象
     * @param clazz
     * @return
     * @param <T>
     */
    public <T> T get(final Class<T> clazz) {
        return (T) SINGLES.get(clazz.getName());
    }
}
