package com.tomato.skill.mapper;

import com.tomato.skill.database.SkillActivityRelationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * SkillActivityRelationMapper
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@SpringBootTest
public class SkillActivityRelationMapperTest {
    @Resource
    private SkillActivityRelationMapper skillActivityRelationMapper;
    @Test
    public void test(){
        System.out.println(skillActivityRelationMapper.getByActivityRelationId(1L));
        skillActivityRelationMapper.updateSkillSurplusCount(1L);
    }
}
