package com.tomato.remit.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

import java.math.BigDecimal;

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
    private int urgent;

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
     * 打款金额
     */
    private BigDecimal requestAmount;
    public RemitOrderInfoDO() {}
}
