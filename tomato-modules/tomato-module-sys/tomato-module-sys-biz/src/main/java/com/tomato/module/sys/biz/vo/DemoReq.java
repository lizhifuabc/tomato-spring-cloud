package com.tomato.module.sys.biz.vo;

import com.tomato.validator.spring.boot.autoconfigure.annotation.Mobile;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 请求demo
 *
 * @author lizhifu
 * @date 2022/5/18
 */
@Data
public class DemoReq {
    @NotNull(message = "年龄不能为空")
    @Max(value = 120,message = "年龄不能超过120岁")
    @Min(value = 0,message = "年龄不能为负数")
    private int age;

    @Mobile
    private String mobile;

    @NotNull(message = "demoReq2不能为空")
    @Valid // 主要用来校验DemoReq2内设置的规则
    private DemoReq2 demoReq2;
}
