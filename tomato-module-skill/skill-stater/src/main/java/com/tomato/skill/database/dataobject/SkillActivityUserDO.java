package com.tomato.skill.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

/**
 * 用户参与活动记录
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Data
public class SkillActivityUserDO extends BaseDO {
    /**
     * 用户参与活动记录ID
     */
    private Long activityUserId;
    /**
     * 秒杀活动商品记录ID
     */
    private Long activityRelationId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 秒杀次数
     */
    private Integer activityCount;
}
