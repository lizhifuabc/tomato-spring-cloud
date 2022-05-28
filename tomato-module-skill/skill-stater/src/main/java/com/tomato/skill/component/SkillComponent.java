package com.tomato.skill.component;

import com.tomato.skill.dto.SkillReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 获取秒杀资格
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Component
@Slf4j
public class SkillComponent {
    public void skill(SkillReq skillReq) {
        log.info("获取秒杀资格skillReq:{}", skillReq);
        // 1 查询用户是否有资格
        // 2 查询用户是否已经秒杀过
    }
}
