package com.tomato.remit.channel.impl;

import com.tomato.remit.channel.ChannelService;
import com.tomato.remit.channel.dto.SendRemitResult;
import com.tomato.remit.dto.RemitOrderReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 默认通道服务实现
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Service
@Slf4j
public class DefaultChannelServiceImpl implements ChannelService {

    @Override
    public String channelCode() {
        return "default";
    }

    @Override
    public String channelName() {
        return "默认通道实现";
    }

    @Override
    public SendRemitResult remit(RemitOrderReq req) {
        log.info("默认通道实现，打款下单，req={}", req);
        SendRemitResult result = new SendRemitResult();
        result.setRemitStatus(0);
        result.setExceptionCode("");
        result.setExceptionInfo("");
        return result;
    }
}
