package com.tomato.skill.service;

import com.tomato.skill.database.SkillActivityMapper;
import com.tomato.skill.database.dataobject.SkillActivityDO;
import org.springframework.stereotype.Service;

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

    public void checkSkillActivity(Long activityId) {
        SkillActivityDO skillActivityDO = skillActivityMapper.getByActivityId(activityId);
        System.out.println("skill");
    }
}
