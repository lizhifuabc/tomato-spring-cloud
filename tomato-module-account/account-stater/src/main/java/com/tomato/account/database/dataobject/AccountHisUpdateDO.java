package com.tomato.account.database.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * AccountHisUpdateDO
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Data
public class AccountHisUpdateDO {
    /**
     * 账号编号
     */
    private String accountNo;
    /**
     * 账户历史表ID
     */
    private Long accountHisId;
    /**
     * 入账状态
     */
    private Integer accountStatus;
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
