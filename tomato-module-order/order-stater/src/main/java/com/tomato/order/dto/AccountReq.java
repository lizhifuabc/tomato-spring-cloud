package com.tomato.order.dto;

import com.tomato.data.dto.Rep;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 入账请求
 *
 * @author lizhifu
 * @date 2022/6/20
 */
@Data
@Builder
public class AccountReq extends Rep {
    /**
     * 商户编号
     */
    private String merchantNo;
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
