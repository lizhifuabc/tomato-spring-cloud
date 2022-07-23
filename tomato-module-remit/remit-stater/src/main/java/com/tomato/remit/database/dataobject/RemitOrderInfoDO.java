package com.tomato.remit.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 打款订单表
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@Data
public class RemitOrderInfoDO extends BaseDO {
    /**
     * 版本号
     */
    private Integer version;

    /**
     * 打款请求流水号
     */
    private String remitRequestNo;

    /**
     * 打款系统唯一流水号
     */
    private String remitOrderNo;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 是否加急【0->是；1->否】
     */
    private Integer urgent;

    /**
     * 银行卡账户名称
     */
    private String accountName;

    /**
     * 银行卡号
     */
    private String accountNo;

    /**
     * 银行编码
     */
    private String bankNo;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 分行名称
     */
    private String branchBankName;

    /**
     * 省份编码
     */
    private String province;

    /**
     * 城市编码
     */
    private String city;

    /**
     * 通知地址
     */
    private String notifyAddress;

    /**
     * 是否创建了批次【0->是；1->否】
     */
    private Integer createBatch;

    /**
     * 打款渠道
     */
    private String channelCode;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 打款状态【0->打款中；1->打款成功；2->打款失败；3->人工处理】
     */
    private Integer remitStatus;

    /**
     * 是否直接打款【0->是；1->否】
     */
    private Integer directRemit;

    /**
     * 异常码
     */
    private String exceptionCode;

    /**
     * 异常信息
     */
    private String exceptionInfo;

    /**
     * 打款金额
     */
    private BigDecimal requestAmount;
    /**
     * 完成时间
     */
    private LocalDateTime completeTime;
    public RemitOrderInfoDO() {}
}
