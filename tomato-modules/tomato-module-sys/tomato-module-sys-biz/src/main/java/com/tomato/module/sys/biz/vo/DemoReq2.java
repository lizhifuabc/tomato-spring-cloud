package com.tomato.module.sys.biz.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 请求demo
 *
 * @author lizhifu
 * @date 2022/5/18
 */
@Data
public class DemoReq2 {
    @NotBlank(message = "密码不能为空")
    @Length(min = 20000)
    private String passWord;
}
