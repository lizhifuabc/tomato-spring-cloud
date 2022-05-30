package com.tomato.skill.service;

import com.tomato.skill.database.SkillActivityMapper;
import com.tomato.skill.database.SkillActivityRelationMapper;
import com.tomato.skill.database.SkillActivityUserMapper;
import com.tomato.skill.database.dataobject.SkillActivityDO;
import com.tomato.skill.database.dataobject.SkillActivityRelationDO;
import com.tomato.skill.database.dataobject.SkillActivityUserDO;
import com.tomato.skill.exception.SkillException;
import com.tomato.skill.exception.SkillResponseCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 秒杀 service
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Service
public class SkillService {
    private final SkillActivityMapper skillActivityMapper;
    private final SkillActivityRelationMapper skillActivityRelationMapper;
    private final SkillActivityUserMapper skillActivityUserMapper;
    public SkillService(SkillActivityMapper skillActivityMapper, SkillActivityRelationMapper skillActivityRelationMapper, SkillActivityUserMapper skillActivityUserMapper) {
        this.skillActivityMapper = skillActivityMapper;
        this.skillActivityRelationMapper = skillActivityRelationMapper;
        this.skillActivityUserMapper = skillActivityUserMapper;
    }

    /**
     * 查询秒杀活动 + 校验秒杀活动是否存在
     * @param activityId
     * @return
     */
    public SkillActivityDO checkSkillActivity(Long activityId) {
        // 查询秒杀活动，校验秒杀活动是否存在 TODO 加入缓存
        SkillActivityDO skillActivityDO = skillActivityMapper.getByActivityId(activityId);
        Optional.ofNullable(skillActivityDO)
                .orElseThrow(() -> new SkillException(SkillResponseCode.SKILL_ACTIVITY_FAILURE));
        LocalDateTime now = LocalDateTime.now();
        // 校验秒杀活动是否已经结束
        if (now.isAfter(skillActivityDO.getEndTime())) {
            throw new SkillException(SkillResponseCode.SKILL_ACTIVITY_FAILURE_END);
        }
        // 校验秒杀活动是否已经开始
        if (now.isBefore(skillActivityDO.getStartTime())) {
            throw new SkillException(SkillResponseCode.SKILL_ACTIVITY_FAILURE_START);
        }
        return skillActivityDO;
    }
    /**
     * 校验秒杀活动商品记录
     * @param activityRelationId
     * @return
     */
    public SkillActivityRelationDO checkSkillActivityRelation(Long activityRelationId) {
        // 秒杀活动商品记录，校验秒杀活动商品记录 TODO 加入缓存
        SkillActivityRelationDO skillActivityRelationDO = skillActivityRelationMapper.getByActivityRelationId(activityRelationId);
        Optional.ofNullable(skillActivityRelationDO)
                .orElseThrow(() -> new SkillException(SkillResponseCode.SKILL_ACTIVITY_FAILURE));
        if (skillActivityRelationDO.getSkillSurplusCount() <= 0) {
            throw new SkillException(SkillResponseCode.SKILL_ACTIVITY_FAILURE_LIMIT);
        }
        return skillActivityRelationDO;
    }
    /**
     * 校验用户秒杀活动记录
     * @param activityRelationId
     * @param userId
     * @param skillLimit
     * @return
     */
    public SkillActivityUserDO checkSkillActivityUser(Long activityRelationId, Long userId,Integer skillLimit) {
        // 用户秒杀活动记录，校验用户秒杀活动记录 TODO 加入缓存
        SkillActivityUserDO skillActivityUserDO = skillActivityUserMapper.getByUserIdAndActivityRelationId(activityRelationId, userId);
        Optional.ofNullable(skillActivityUserDO).ifPresent(skill -> {
            if (skill.getActivityCount() >= skillLimit) {
                throw new SkillException(SkillResponseCode.SKILL_ACTIVITY_FAILURE_USER_LIMIT);
            }
        });
        return skillActivityUserDO;
    }
}
