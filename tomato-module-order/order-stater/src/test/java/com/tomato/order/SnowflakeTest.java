package com.tomato.order;

import com.tomato.utils.lang.Snowflake;

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
        Snowflake snowflake = Snowflake.create(1013L);
        for (int i = 0; i < 1; i++) {
            System.out.println(snowflake.nextId());
        }
    }
}
