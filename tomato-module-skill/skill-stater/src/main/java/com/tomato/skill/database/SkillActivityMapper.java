package com.tomato.skill.database;

import com.tomato.skill.database.dataobject.SkillActivityDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 秒杀活动记录
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Mapper
public interface SkillActivityMapper {
    /**
     * 查询秒杀活动记录
     * @param activityId
     * @return
     */
    SkillActivityDO getByActivityId(@Param("activityId") Long activityId);
}
