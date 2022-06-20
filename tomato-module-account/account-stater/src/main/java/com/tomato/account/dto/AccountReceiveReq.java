package com.tomato.account.dto;

import com.tomato.data.dto.Req;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 账户系统收单请求
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Data
public class AccountReceiveReq extends Req {
    /**
     * 账户ID
     */
    @NotNull(message = "账户ID不能为空")
    private Long accountId;
    /**
     * 发生金额 TODO 0 判断
     */
    @NotNull(message = "发生金额不能为空")
    private BigDecimal amount;
    /**
     * 第三方流水号
     */
    @NotBlank(message = "第三方流水号不能为空")
    private String thirdNo;
    /**
     * 发生类型
     */
    @NotBlank(message = "发生类型不能为空")
    private String accountHisType;
}
