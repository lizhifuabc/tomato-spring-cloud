package com.tomato.account.dto;

import com.tomato.data.dto.Req;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 账户创建请求
 *
 * @author lizhifu
 * @date 2022/7/1
 */
@Data
public class AccountCreateReq extends Req {
    /**
     * 商户编号
     */
    @NotBlank(message = "商户编号不能为空")
    @Length(min = 4, message = "商户编号长度不能低于4位")
    private String merchantNo;
}
