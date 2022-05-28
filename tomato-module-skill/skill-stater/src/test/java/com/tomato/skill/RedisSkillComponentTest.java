package com.tomato.skill;

import com.tomato.skill.component.RedisSkillSkuRelationComponent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * RedisSkillSkuRelationComponent
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@SpringBootTest
public class RedisSkillSkuRelationComponentTest {
    @Resource
    private RedisSkillSkuRelationComponent redisSkillSkuRelationComponent;
    @Test
    public void test() {
        System.out.println(redisSkillSkuRelationComponent.deduct(1L, 1));
    }
}
