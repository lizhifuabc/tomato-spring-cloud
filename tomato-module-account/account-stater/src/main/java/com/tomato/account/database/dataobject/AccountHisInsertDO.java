package com.tomato.account.database.dataobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * AccountHisUpdateDO
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Data
public class AccountHisInsertDO {
    /**
     * 账户历史表ID
     */
    private Long accountHisId;
    /**
     * 账户ID
     */
    private Long accountId;
    /**
     * 发生金额
     */
    private BigDecimal amount;
    /**
     * 第三方流水号
     */
    private String thirdNo;
    /**
     * 发生类型
     */
    private String accountHisType;
}
