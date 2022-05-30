package com.tomato.skill.component;

import com.tomato.redis.util.RedisUtils;
import com.tomato.skill.constants.RedisKeyConstants;
import com.tomato.skill.exception.SkillException;
import com.tomato.skill.exception.SkillResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * redis 库存相关操作
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Component
@Slf4j
public class RedisSkillComponent {
    private final RedisUtils redisUtils;

    public RedisSkillComponent(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * redis 扣减库存
     * @param activityRelationId 秒杀活动商品记录ID
     * @param skillCount 秒杀总量
     * @return
     */
    public void deduct(Long activityRelationId,Integer skillCount){
        // 库存 Key
        String skillKey = RedisKeyConstants.SKILL_KEY_PREFIX + activityRelationId;
        // 已经秒杀的库存数量
        long usedSkillCount = redisUtils.incr(skillKey, 1);
        // 已经秒杀的库存数量 > 秒杀数量
        if (usedSkillCount > skillCount) {
            log.info("活动{}秒杀库存不足：{}", activityRelationId, skillKey);
            // 库存不足，返回秒杀失败
            redisUtils.decr(skillKey, 1);
            throw new SkillException(SkillResponseCode.SKILL_COUNT_NOT_ENOUGH);
        }
        // 锁 Key
        String lockKey = RedisKeyConstants.SKILL_LOCK_KEY_PREFIX + activityRelationId +":" + usedSkillCount;
        boolean lock = redisUtils.setNx(lockKey, 350L);
        if (!lock) {
            log.info("活动{}秒杀扣减库存获取分布式锁失败：{}", activityRelationId, lockKey);
            throw new SkillException(SkillResponseCode.SKILL_COUNT_LOCK_FAIL);
        }
    }
}
