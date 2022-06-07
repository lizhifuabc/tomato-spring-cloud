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
public class AccountHisUpdateDO {
    /**
     * 账户历史表ID
     */
    private Long accountHisId;
    /**
     * 状态,10:创建,11:成功,12:失败
     */
    private Integer state;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 发生前余额
     */
    private BigDecimal beforeBalance;
    /**
     * 发生后余额
     */
    private BigDecimal afterBalance;
}
