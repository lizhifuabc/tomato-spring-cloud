package com.tomato.skill.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

/**
 * 秒杀活动商品记录
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Data
public class SkillActivityRelationDO extends BaseDO {
    /**
     * 秒杀活动商品记录ID
     */
    private Long activityRelationId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 商品ID
     */
    private Long skuId;
    /**
     * 秒杀价格
     */
    private Long skillPrice;
    /**
     * 秒杀总量
     */
    private Integer skillCount;
    /**
     * 秒杀剩余量
     */
    private Integer skillSurplusCount;
    /**
     * 每人限购数量
     */
    private Integer skillLimit;
    /**
     * 排序
     */
    private Integer skillSort;

}
