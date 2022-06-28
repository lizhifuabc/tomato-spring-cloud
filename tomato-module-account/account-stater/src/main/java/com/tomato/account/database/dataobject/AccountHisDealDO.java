package com.tomato.account.database.dataobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 查询未入账的账户历史返回
 *
 * @author lizhifu
 * @date 2022/6/28
 */
@Data
public class AccountHisDealDO {
    /**
     * 账户历史表ID
     */
    private String accountHisIds;
    /**
     * 金额汇总
     */
    private BigDecimal sum;
}
