package com.tomato.merchant.dto;

import com.tomato.data.dto.Req;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商户创建
 *
 * @author lizhifu
 * @date 2022/6/18
 */
@Data
public class MerchantCreateReq extends Req {
    /**
     * 注册邮箱
     */
    @NotBlank(message = "注册邮箱不能为空")
    @Email(message = "注册邮箱格式不正确")
    private String mail;

    /**
     * 商户名称
     */
    @NotBlank(message = "商户名称不能为空")
    private String merchantName;

    /**
     * 商户类型
     */
    @NotNull(message = "商户类型不能为空")
    private Integer merchantType;
}
