package com.tomato.skill.database;

import com.tomato.skill.database.dataobject.SkillActivityRelationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 秒杀活动商品记录
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Mapper
public interface SkillActivityRelationMapper {
    /**
     * 查询秒杀活动商品记录
     * @param activityRelationId
     * @return
     */
    SkillActivityRelationDO getByActivityRelationId(@Param("activityRelationId") Long activityRelationId);

    /**
     * 秒杀剩余量减一
     * @param activityRelationId
     * @return
     */
    int updateSkillSurplusCount(@Param("activityRelationId") Long activityRelationId);
}
