package com.tomato.skill;

import com.tomato.skill.component.SkillComponent;
import com.tomato.skill.dto.SkillReq;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * SkillComponent
 *
 * @author lizhifu
 * @date 2022/5/30
 */
@SpringBootTest
public class SkillComponentTest {
    @Resource
    private SkillComponent skillComponent;
    @Test
    public void test() {
        SkillReq skillReq = new SkillReq();
        skillReq.setActivityId(11L);
        skillReq.setActivityRelationId(11L);
        skillReq.setUserId(10000L);
        skillComponent.skill(skillReq);
    }
}
