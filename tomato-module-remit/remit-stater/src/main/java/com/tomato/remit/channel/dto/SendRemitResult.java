package com.tomato.remit.channel.dto;

import lombok.Data;

/**
 * 发送打款结果
 * 直连类型：
 * -- 打款成功
 * -- 打款失败
 * -- 打款处理中
 *
 * 非直连类型：
 * -- 收单失败-打款失败-只有针对拒绝订单这种情况
 * -- 收单成功-打款处理中
 *
 * -- 其他所有，未知(异常)，进入人工处理流程
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Data
public class SendRemitResult {
    /**
     * 打款状态【0->打款中；1->打款完成】
     * TODO 打款失败怎么处理
     */
    private Integer remitStatus;
    /**
     * 异常码
     */
    private String exceptionCode;

    /**
     * 异常信息
     */
    private String exceptionInfo;
}
