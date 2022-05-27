package com.tomato.id.strategy;

/**
 * Id 生成策略
 *
 * @author lizhifu
 * @date 2022/5/27
 */
@FunctionalInterface
public interface IdGeneratorStrategy {
    /**
     * 生成 Id
     * @return Id
     */
    long nextId();
}