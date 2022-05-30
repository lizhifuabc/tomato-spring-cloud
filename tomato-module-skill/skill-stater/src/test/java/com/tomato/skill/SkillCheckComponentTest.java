package com.tomato.skill;

import com.tomato.skill.component.SkillCheckComponent;
import com.tomato.skill.dto.SkillReq;
import com.tomato.skill.pojo.clientObject.SkillCO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * SkillCheckComponent
 *
 * @author lizhifu
 * @date 2022/5/30
 */
@SpringBootTest
public class SkillCheckComponentTest {
    @Resource
    private SkillCheckComponent skillCheckComponent;

    @Test
    public void test() throws InterruptedException {
        SkillReq skillReq = new SkillReq();
        skillReq.setActivityId(11L);
        skillReq.setActivityRelationId(11L);
        SkillCO skillCO = skillCheckComponent.checkSkill(skillReq);
        System.out.println("skillCO:"+skillCO);
    }
}
