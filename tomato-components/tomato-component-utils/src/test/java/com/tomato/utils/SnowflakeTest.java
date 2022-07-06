package com.tomato.utils;

import com.tomato.utils.lang.Snowflake;

/**
 * Snowflake
 *
 * @author lizhifu
 * @date 2022/7/6
 */
public class SnowflakeTest {
    public static void main(String[] args) {
        Snowflake snowflake = Snowflake.create(1, 1);
        System.out.println(snowflake.nextId());
    }
}
