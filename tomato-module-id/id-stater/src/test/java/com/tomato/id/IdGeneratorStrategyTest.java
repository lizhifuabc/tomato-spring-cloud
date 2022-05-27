package com.tomato.id;

import com.tomato.id.strategy.IdGeneratorStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * IdGeneratorStrategy
 *
 * @author lizhifu
 * @date 2022/5/27
 */
@SpringBootTest
public class IdGeneratorStrategyTest {
    @Resource
    private IdGeneratorStrategy idGeneratorStrategy;

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            System.out.println(idGeneratorStrategy.nextId());
        }
    }
}