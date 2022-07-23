package com.tomato.remit.channel.dto;

import com.tomato.remit.enums.RemitStatusEnum;
import lombok.Data;

/**
 * 发送打款结果
 * 直连类型：
 * -- 打款成功
 * -- 打款失败-->据单
 * -- 打款处理中
 *
 * 非直连类型：
 * -- 收单失败-->打款失败-只有针对拒绝订单这种情况
 * -- 收单成功-打款处理中
 *
 * -- 其他所有，未知或者异常，进入人工处理流程
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Data
public class SendRemitResult {
    /**
     * {@link RemitStatusEnum}
     * TODO 打款失败处理-通知上游系统
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
