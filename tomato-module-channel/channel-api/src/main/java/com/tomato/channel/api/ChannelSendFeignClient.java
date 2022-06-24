package com.tomato.channel.api;

import com.tomato.channel.api.fallback.ChannelSendFeignClientFallback;
import com.tomato.channel.dto.ChannelSendRep;
import com.tomato.channel.dto.ChannelSendReq;
import com.tomato.data.response.SingleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 通道发送请求
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@FeignClient(value = "tomato-module-channel", fallback = ChannelSendFeignClientFallback.class)
public interface ChannelSendFeignClient {
    @RequestMapping(value = "channel/send", method = RequestMethod.POST)
    SingleResponse<ChannelSendRep> send(@RequestBody ChannelSendReq channelSendReq);
}
