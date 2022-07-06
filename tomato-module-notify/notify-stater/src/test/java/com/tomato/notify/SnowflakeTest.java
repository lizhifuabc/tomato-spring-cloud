package com.tomato.notify;

import com.tomato.utils.lang.Snowflake;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * SnowflakeTest
 *
 * @author lizhifu
 * @date 2022/7/6
 */
@SpringBootTest
public class SnowflakeTest {
    @Resource
    private Snowflake snowflake;
    @Test
    public void test(){
        for (int i = 0; i < 100; i++) {
            System.out.println(snowflake.nextId());
        }
    }
}
