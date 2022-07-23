package com.tomato.remit.channel;

import com.tomato.remit.channel.handle.ChannelHandle;
import com.tomato.remit.database.dataobject.RemitChannelInfoDO;
import com.tomato.remit.dto.RemitOrderReq;
import com.tomato.remit.exception.RemitException;
import com.tomato.remit.exception.RemitResponseCode;
import com.tomato.remit.service.RemitChannelService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 通道策略
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Component
public class ChannelStrategy {
    private final RemitChannelService remitChannelService;

    public ChannelStrategy(RemitChannelService remitChannelService) {
        this.remitChannelService = remitChannelService;
    }

    /**
     * 通道策略
     * @param remitOrderReq
     * @return
     */
    public RemitChannelInfoDO getChannel(RemitOrderReq remitOrderReq) {
        List<RemitChannelInfoDO> channel = remitChannelService.getChannel(remitOrderReq);
        if (channel.isEmpty()) {
            throw new RemitException(RemitResponseCode.REMIT_FAILURE);
        }
        // TODO 通道筛选，金额，限流等等
        return channel.get(0);
    }
}
