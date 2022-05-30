package com.tomato.skill.component;

import com.tomato.skill.database.dataobject.SkillActivityRelationDO;
import com.tomato.skill.dto.SkillReq;
import com.tomato.skill.pojo.clientObject.SkillCO;
import com.tomato.skill.service.SkillActivityUserService;
import org.springframework.stereotype.Component;

/**
 * 秒杀
 *
 * @author lizhifu
 * @date 2022/5/30
 */
@Component
public class SkillComponent {
    private final SkillCheckComponent skillCheckComponent;
    private final RedisSkillComponent redisSkillComponent;
    private final SkillActivityUserService skillActivityUserService;
    public SkillComponent(SkillCheckComponent skillCheckComponent, RedisSkillComponent redisSkillComponent, SkillActivityUserService skillActivityUserService) {
        this.skillCheckComponent = skillCheckComponent;
        this.redisSkillComponent = redisSkillComponent;
        this.skillActivityUserService = skillActivityUserService;
    }

    public void skill(SkillReq skillReq) {
        // 基本数据校验
        SkillCO skillCO = skillCheckComponent.checkSkill(skillReq);
        SkillActivityRelationDO skillActivityRelationDO = skillCO.getSkillActivityRelationDO();
        // redis 扣减库存
        redisSkillComponent.deduct(skillActivityRelationDO.getActivityRelationId(), skillActivityRelationDO.getSkillCount());
        // 扣减库存后，更新个人抢购次数
        skillActivityUserService.skillActivity(skillActivityRelationDO.getActivityRelationId(),skillReq.getUserId(), skillActivityRelationDO.getSkillLimit());
    }
}
