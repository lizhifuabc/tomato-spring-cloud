package com.tomato.skill;

import com.tomato.skill.service.SkillService;
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
public class SkillServiceTest {
    @Resource
    private SkillService skillService;
    @Test
    public void test() {
        System.out.println(skillService.checkSkillActivity(11L));
    }
}
