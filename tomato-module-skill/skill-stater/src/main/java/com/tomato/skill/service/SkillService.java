package com.tomato.skill.service;

import com.tomato.skill.database.SkillActivityMapper;
import com.tomato.skill.database.dataobject.SkillActivityDO;
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

    public SkillService(SkillActivityMapper skillActivityMapper) {
        this.skillActivityMapper = skillActivityMapper;
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
}
