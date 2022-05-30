package com.tomato.skill.component;

import com.tomato.skill.database.dataobject.SkillActivityRelationDO;
import com.tomato.skill.dto.SkillReq;
import com.tomato.skill.pojo.clientObject.RedisDeduct;
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
        RedisDeduct redisDeduct = redisSkillComponent.deduct(skillActivityRelationDO.getActivityRelationId(), skillActivityRelationDO.getSkillCount());
        try {
            // 扣减库存后，更新个人抢购次数
            // TODO 个人抢购失败需要恢复库存:本次抢购LOCK，本次增加的库存
            // 不推荐恢复库存，如果需要把失败的库存号恢复，则需要添加一个处理失败的缓存队列，记录失败的key，之后从缓存队列中往出弹进行消费。
            skillActivityUserService.skillActivity(skillActivityRelationDO.getActivityRelationId(),skillReq.getUserId(), skillActivityRelationDO.getSkillLimit());
        } catch (Exception e) {
            // 删除一次秒杀，单个库存的锁
            redisSkillComponent.deleteLock(redisDeduct);
        }
        // 删除一次秒杀，单个库存的锁
        redisSkillComponent.deleteLock(redisDeduct);
    }
}
