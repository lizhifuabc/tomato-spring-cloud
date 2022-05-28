package com.tomato.skill.database.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀活动记录
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Data
public class SkillActivityDO extends BaseDO {
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动描述
     */
    private String activityDesc;

    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 活动状态：1-开启  0-关闭
     */
    private Integer state;
}
