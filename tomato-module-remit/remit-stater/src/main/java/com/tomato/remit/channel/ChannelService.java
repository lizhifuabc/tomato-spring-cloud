package com.tomato.remit.channel;

import com.tomato.remit.channel.dto.SendRemitResult;
import com.tomato.remit.dto.RemitOrderReq;

/**
 * 打款接口
 *
 * @author lizhifu
 * @date 2022/7/23
 */
public interface ChannelService {
    /**
     * 通道标识
     * @return
     */
    String channelCode();
    /**
     * 通道名称
     * @return
     */
    String channelName();

    /**
     * 打款下单
     * @param remitOrderReq
     * @return
     */
    SendRemitResult remit(RemitOrderReq remitOrderReq);
}
