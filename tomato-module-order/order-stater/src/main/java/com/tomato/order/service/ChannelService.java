package com.tomato.order.service;

import com.tomato.channel.api.ChannelSendFeignClient;
import com.tomato.channel.dto.ChannelSendRep;
import com.tomato.channel.dto.ChannelSendReq;
import com.tomato.data.response.SingleResponse;
import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.database.dataobject.PayInfoDO;
import com.tomato.order.exception.OrderException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 通道服务
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Service
public class ChannelService {
    private final ChannelSendFeignClient channelSendFeignClient;

    public ChannelService(ChannelSendFeignClient channelSendFeignClient) {
        this.channelSendFeignClient = channelSendFeignClient;
    }
    public ChannelSendRep send(OrderInfoDO orderInfoDO, PayInfoDO payInfoDO) {
        ChannelSendReq channelSendReq = new ChannelSendReq();
        channelSendReq.setPayNo(payInfoDO.getPayNo());
        channelSendReq.setRequestAmount(orderInfoDO.getRequestAmount());
        channelSendReq.setPayType(payInfoDO.getPayType());
        channelSendReq.setMerchantNo(orderInfoDO.getMerchantNo());
        SingleResponse<ChannelSendRep> channelSendRep = channelSendFeignClient.send(channelSendReq);
        if (channelSendRep.isSuccess()) {
            return channelSendRep.getData();
        }else {
            throw new OrderException(channelSendRep.getCode(), channelSendRep.getMessage());
        }
    }
}
