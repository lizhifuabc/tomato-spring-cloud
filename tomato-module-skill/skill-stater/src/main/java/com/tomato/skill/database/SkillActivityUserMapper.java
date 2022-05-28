package com.tomato.skill.database;

import com.tomato.skill.database.dataobject.SkillActivityUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * SkillActivityUser
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Mapper
public interface SkillActivityUserMapper {
    /**
     * 创建用户秒杀数据
     * @param skillActivityUserDO
     * @return
     */
    int create(SkillActivityUserDO skillActivityUserDO);

    /**
     * 更新用户秒杀数量
     * @param activityUserId
     * @param skillLimit
     * @return
     */
    int updateActivityCount(@Param("activityUserId") Long activityUserId, @Param("skillLimit") Integer skillLimit);

    /**
     * 查询用户秒杀数据
     * @param activityUserId
     * @return
     */
    SkillActivityUserDO getByActivityUserId(@Param("activityUserId") Long activityUserId);
}
