package com.tomato.remit.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 打款批次表
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@Data
public class RemitBatchInfoDO extends BaseDO {
    /**
     * 版本号
     */
    private Integer version;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 打款渠道
     */
    private String channelCode;

    /**
     * 打款状态【0->打款中；1->打款成功；2->打款失败；3->人工处理】
     */
    private Integer remitStatus;

    /**
     * 打款笔数
     */
    private Integer remitCount;

    /**
     * 打款总金额
     */
    private BigDecimal remitSumAmount;
    /**
     * 完成时间
     */
    private LocalDateTime completeTime;
    public RemitBatchInfoDO() {}
}
