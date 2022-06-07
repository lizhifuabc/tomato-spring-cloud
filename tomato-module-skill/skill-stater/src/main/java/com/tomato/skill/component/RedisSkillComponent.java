package com.tomato.skill.component;

import com.tomato.redis.util.RedisUtils;
import com.tomato.skill.constants.RedisKeyConstants;
import com.tomato.skill.exception.SkillException;
import com.tomato.skill.exception.SkillResponseCode;
import com.tomato.skill.pojo.RedisDeductRep;
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
    public RedisDeductRep deduct(Long activityRelationId, Integer skillCount){
        RedisDeductRep redisDeductRep = new RedisDeductRep();
        // 库存 Key
        String skillKey = RedisKeyConstants.SKILL_KEY_PREFIX + activityRelationId;
        redisDeductRep.setSkillKey(skillKey);

        // 已经秒杀的库存数量，这里使用 incr decr 为了后续增加库存或者减少库存时方便
        long usedSkillCount = redisUtils.incr(skillKey, 1);
        // 已经秒杀的库存数量 > 秒杀数量
        if (usedSkillCount > skillCount) {
            log.info("秒杀失败，库存不足，activityRelationId={},skillKey={},usedSkillCount={}", activityRelationId, skillKey, usedSkillCount);
            // 库存不足，返回秒杀失败
            redisUtils.decr(skillKey, 1);
            throw new SkillException(SkillResponseCode.SKILL_COUNT_NOT_ENOUGH);
        }
        // 锁 Key，一个秒杀库存一个锁
        String lockKey = RedisKeyConstants.SKILL_LOCK_KEY_PREFIX + activityRelationId +":" + usedSkillCount;
        redisDeductRep.setLockKey(lockKey);

        boolean lock = redisUtils.setNx(lockKey, 350L);
        if (!lock) {
            log.info("活动{}秒杀扣减库存获取分布式锁失败：{}", activityRelationId, lockKey);
            throw new SkillException(SkillResponseCode.SKILL_COUNT_LOCK_FAIL);
        }
        return redisDeductRep;
    }

    public void deleteLock(RedisDeductRep redisDeductRep) {
        // 删除分布式锁 Key
        redisUtils.del(redisDeductRep.getLockKey());
    }

    /**
     * 查询已经售出的库存数量
     * @param activityRelationId
     * @return
     */
    public Integer usedSkillCount(Long activityRelationId) {
        // 库存 Key
        String skillKey = RedisKeyConstants.SKILL_KEY_PREFIX + activityRelationId;
        return (Integer) redisUtils.get(skillKey);
    }
}
