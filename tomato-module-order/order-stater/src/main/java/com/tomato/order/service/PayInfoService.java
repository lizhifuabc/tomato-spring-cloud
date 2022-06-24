package com.tomato.order.service;

import com.tomato.channel.dto.ChannelSendRep;
import com.tomato.order.database.PayInfoMapper;
import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.database.dataobject.PayInfoDO;
import org.springframework.stereotype.Service;

/**
 * 支付信息服务
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Service
public class PayInfoService {
    private final PayInfoMapper payInfoMapper;
    private final ChannelService channelService;

    public PayInfoService(PayInfoMapper payInfoMapper, ChannelService channelService) {
        this.payInfoMapper = payInfoMapper;
        this.channelService = channelService;
    }

    public void createPay(OrderInfoDO orderInfoDO) {
        PayInfoDO payInfoDO = new PayInfoDO();
        payInfoDO.setOrderNo(orderInfoDO.getOrderNo());
        payInfoDO.setPayType(orderInfoDO.getPayType());
        // TODO 订单号生成-分布式id生成策略
        payInfoDO.setPayNo(System.currentTimeMillis() + "");

        ChannelSendRep channelSendRep = channelService.send(orderInfoDO, payInfoDO);
        payInfoDO.setSendUrl(channelSendRep.getSendUrl());
        payInfoDO.setChannelRate(channelSendRep.getChannelRate());
        payInfoDO.setChannelFlag(channelSendRep.getChannelFlag());
        payInfoDO.setRemarksInfo(channelSendRep.getRemarksInfo());
        // 发送通道异常，不创建支付信息
        payInfoMapper.insert(payInfoDO);
    }
}
