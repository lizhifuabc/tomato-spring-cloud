package com.tomato.remit.component;

import com.tomato.remit.channel.ChannelService;
import com.tomato.remit.channel.ChannelStrategy;
import com.tomato.remit.channel.dto.SendRemitResult;
import com.tomato.remit.channel.handle.ChannelHandle;
import com.tomato.remit.database.dataobject.RemitChannelInfoDO;
import com.tomato.remit.database.dataobject.RemitOrderInfoDO;
import com.tomato.remit.dto.RemitOrderReq;
import com.tomato.remit.service.RemitOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 打款
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Component
@Slf4j
public class RemitOrderComponent {
    private final RemitOrderService remitOrderService;
    private final ChannelStrategy channelStrategy;

    public RemitOrderComponent(RemitOrderService remitOrderService, ChannelStrategy channelStrategy) {
        this.remitOrderService = remitOrderService;
        this.channelStrategy = channelStrategy;
    }

    public void createOrder(RemitOrderReq remitOrderReq) {
        log.info("打款下单：createOrder: {}", remitOrderReq);
        RemitChannelInfoDO channel = channelStrategy.getChannel(remitOrderReq);

        RemitOrderInfoDO remitOrderInfoDO = new RemitOrderInfoDO();
        BeanUtils.copyProperties(remitOrderReq, remitOrderInfoDO);
        BeanUtils.copyProperties(channel, remitOrderInfoDO);
        remitOrderService.createOrder(remitOrderInfoDO);

        SendRemitResult remit = ChannelHandle.getChannelService(channel.getChannelCode()).remit(remitOrderReq);
        log.info("打款下单返回：remit: {}", remit);
    }
}
