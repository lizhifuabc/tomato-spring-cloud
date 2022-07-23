package com.tomato.remit.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 打款渠道表
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Data
public class RemitChannelInfoDO extends BaseDO {
    /**
     * 版本号
     */
    private Integer version;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道状态【0->开；1->关】
     */
    private Integer channelStatus;

    /**
     * 渠道成本
     */
    private BigDecimal channelCost;

    /**
     * 所在银行账号
     */
    private String bankAccountNo;

    /**
     * 渠道速度【0->快；1->慢】
     */
    private Integer channelSpeed;
    public RemitChannelInfoDO() {}
}
