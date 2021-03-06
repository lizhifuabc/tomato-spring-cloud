package com.tomato.account.database.dataobject;

import lombok.Data;

import java.time.LocalDate;

/**
 * 日汇总账户待结算金额
 *
 * @author lizhifu
 * @date 2022/7/13
 */
@Data
public class AccountHisDailyCollectDO {
    /**
     * 账户ID
     */
    private String accountNo;
    /**
     * 汇总日期
     */
    private LocalDate collectDate;
    /**
     * 风险时间
     */
    private Integer riskDay;
}
