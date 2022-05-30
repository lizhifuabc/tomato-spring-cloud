package com.tomato.skill;

import com.tomato.skill.service.SkillCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * SkillService
 *
 * @author lizhifu
 * @date 2022/5/30
 */
@SpringBootTest
public class SkillCheckServiceTest {
    @Resource
    private SkillCheckService skillCheckService;
    @Test
    public void test() {
        System.out.println(skillCheckService.checkSkillActivity(11L));
        System.out.println(skillCheckService.checkSkillActivityRelation(11L));
        System.out.println(skillCheckService.checkSkillActivityUser(11L, 11L, 100));
    }
}
