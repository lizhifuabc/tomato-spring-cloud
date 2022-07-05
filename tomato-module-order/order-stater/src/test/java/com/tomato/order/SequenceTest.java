package com.tomato.order;

import com.tomato.utils.lang.Sequence;
import com.tomato.utils.lang.Snowflake;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.commons.util.InetUtils;

import javax.annotation.Resource;

/**
 * Sequence
 *
 * @author lizhifu
 * @date 2022/7/5
 */
@SpringBootTest
public class SequenceTest {
    @Resource
    private InetUtils inetUtils;
    @Test
    public void test(){
        Snowflake snowflake = Snowflake.create(1L);
        Sequence sequence = new Sequence(inetUtils.findFirstNonLoopbackAddress());
        for (int i = 0; i < 100; i++) {
            System.out.println(sequence.nextId());
            System.out.println(snowflake.nextId());
        }
    }
}
