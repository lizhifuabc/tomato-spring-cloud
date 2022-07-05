package com.tomato.order;

/**
 * Snowflake
 *
 * @author lizhifu
 * @date 2022/7/5
 */
public class SnowflakeTest {
    private final static long workerIdBits = 10L;
    private final static long datacenterIdBits = 5L;
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    public static void main(String[] args) {
        System.out.println(maxWorkerId);
    }
}
