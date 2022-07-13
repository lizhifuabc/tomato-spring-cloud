package com.tomato.account.database.dataobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 日汇总账户待结算金额
 *
 * @author lizhifu
 * @date 2022/7/13
 */
@Data
public class AccountHisDailyCollectRepDO {
    /**
     * 总金额
     */
    private BigDecimal totalAmount;
    /**
     * 总笔数
     */
    private Integer totalCount;
}
