package com.tomato.order.service;

import com.tomato.channel.api.ChannelSendFeignClient;
import com.tomato.channel.dto.ChannelSendRep;
import com.tomato.channel.dto.ChannelSendReq;
import com.tomato.data.response.SingleResponse;
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
    public void send(String payNo, BigDecimal requestAmount, Integer payType, String merchantNo) {
        ChannelSendReq channelSendReq = new ChannelSendReq();
        channelSendReq.setPayNo(payNo);
        channelSendReq.setRequestAmount(requestAmount);
        channelSendReq.setPayType(payType);
        channelSendReq.setMerchantNo(merchantNo);
        SingleResponse<ChannelSendRep> channelSendRep = channelSendFeignClient.send(channelSendReq);
    }
}
