package com.tomato.skill.mapper;

import com.tomato.skill.database.SkillActivityUserMapper;
import com.tomato.skill.database.dataobject.SkillActivityUserDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * SkillActivityUserMapper
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@SpringBootTest
public class SkillActivityUserMapperTest {
    @Resource
    private SkillActivityUserMapper skillActivityUserMapper;
    @Test
    public void create() {
        SkillActivityUserDO skillActivityUserDO = new SkillActivityUserDO();
        skillActivityUserDO.setActivityUserId(System.currentTimeMillis());
        skillActivityUserDO.setActivityRelationId(System.currentTimeMillis());
        skillActivityUserDO.setUserId(System.currentTimeMillis());
        skillActivityUserDO.setActivityCount(1);
        skillActivityUserMapper.create(skillActivityUserDO);

        System.out.println(skillActivityUserMapper.updateActivityCount(1653723454981L, 2));

        System.out.println(skillActivityUserMapper.getByActivityUserId(1653723454981L));
    }
}
