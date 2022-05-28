package com.tomato.skill.component;

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
public class SkillSkuRelationComponent {
    public void skill(){
        log.info("扣减库存");
    }
}
