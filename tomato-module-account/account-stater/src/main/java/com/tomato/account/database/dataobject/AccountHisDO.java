package com.tomato.account.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 账户历史表
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Data
public class AccountHisDO extends BaseDO {
    /**
     * 账户历史表ID
     */
    private Long accountHisId;
    /**
     * 账户ID
     */
    private Long accountId;
    /**
     * 发生前余额
     */
    private BigDecimal beforeBalance;
    /**
     * 发生后余额
     */
    private BigDecimal afterBalance;
    /**
     * 发生金额
     */
    private BigDecimal amount;
    /**
     * 第三方流水号
     */
    private String thirdNo;
    /**
     * 状态,10:创建,11:成功,12:失败
     */
    private Integer state;
    /**
     * 发生类型
     */
    private String accountHisType;
    /**
     * 版本号
     */
    private Integer version;
}
