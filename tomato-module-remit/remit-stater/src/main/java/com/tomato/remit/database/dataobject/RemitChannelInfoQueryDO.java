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
public class RemitChannelInfoQueryDO extends BaseDO {
    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 渠道速度【0->快；1->慢】
     */
    private Integer channelSpeed;
    public RemitChannelInfoQueryDO() {}
}
