package com.tomato.account.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * AccountDO
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Data
public class AccountDO extends BaseDO {
    /**
     * 账户ID
     */
    private Long accountId;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 版本号
     */
    private Integer version;
}
