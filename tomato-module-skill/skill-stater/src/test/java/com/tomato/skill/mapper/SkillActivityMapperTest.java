package com.tomato.skill.mapper;

import com.tomato.skill.database.SkillActivityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * SkillActivityMapper
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@SpringBootTest
public class SkillActivityMapperTest {
    @Resource
    private SkillActivityMapper skillActivityMapper;
    @Test
    public void test() {
        System.out.println(skillActivityMapper.getByActivityId(1L));
    }
}
