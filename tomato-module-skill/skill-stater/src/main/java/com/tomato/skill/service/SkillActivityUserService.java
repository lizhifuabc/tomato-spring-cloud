package com.tomato.skill.service;

import com.tomato.skill.database.SkillActivityUserMapper;
import com.tomato.skill.database.dataobject.SkillActivityUserDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SkillActivityUserService
 *
 * @author lizhifu
 * @date 2022/5/30
 */
@Service
public class SkillActivityUserService {
    private final SkillActivityUserMapper skillActivityUserMapper;

    public SkillActivityUserService(SkillActivityUserMapper skillActivityUserMapper) {
        this.skillActivityUserMapper = skillActivityUserMapper;
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean skillActivity(Long activityRelationId, Long userId,Integer skillLimit) {
        SkillActivityUserDO skillActivityUserDO = skillActivityUserMapper.getByUserIdAndActivityRelationId(activityRelationId, userId);
        if (skillActivityUserDO == null) {
            skillActivityUserDO = new SkillActivityUserDO();
            skillActivityUserDO.setUserId(userId);
            // TODO 分布式ID获取
            skillActivityUserDO.setActivityUserId(System.currentTimeMillis());
            skillActivityUserDO.setActivityRelationId(activityRelationId);
            skillActivityUserDO.setActivityCount(1);
            return skillActivityUserMapper.create(skillActivityUserDO) > 0;
        }else {
            return skillActivityUserMapper.updateActivityCount(skillActivityUserDO.getActivityUserId(),skillLimit) > 0;
        }
    }
}
