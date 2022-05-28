package com.tomato.skill.component;

import com.tomato.redis.util.RedisUtils;
import com.tomato.skill.constants.RedisKeyConstants;
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
public class RedisSkillSkuRelationComponent {
    private final RedisUtils redisUtils;

    public RedisSkillSkuRelationComponent(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * redis 扣减库存
     * @param id 标识
     * @param skillCount 库存数量
     * @return
     */
    public boolean deduct(Long id,Integer skillCount){
        // 库存 Key
        String skillKey = RedisKeyConstants.SKILL_KEY_PREFIX + id;
        // 已经秒杀的库存数量
        long usedSkillCount = redisUtils.incr(skillKey, 1);
        // 已经秒杀的库存数量 > 秒杀数量
        if (usedSkillCount > skillCount) {
            log.info("活动{}秒杀库存不足：{}", id, skillKey);
            // 库存不足，返回秒杀失败
            redisUtils.decr(skillKey, 1);
            return false;
        }
        // 锁 Key
        String lockKey = RedisKeyConstants.SKILL_LOCK_KEY_PREFIX + id +":" + usedSkillCount;
        boolean lock = redisUtils.setNx(lockKey, 350L);
        if (!lock) {
            log.info("活动{}秒杀扣减库存获取分布式锁失败：{}", id, lockKey);
            return false;
        }
        return true;
    }
}
