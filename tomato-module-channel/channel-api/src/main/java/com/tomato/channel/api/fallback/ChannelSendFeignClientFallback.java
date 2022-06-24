package com.tomato.channel.api.fallback;

import com.tomato.channel.api.ChannelSendFeignClient;
import com.tomato.channel.dto.ChannelSendRep;
import com.tomato.channel.dto.ChannelSendReq;
import com.tomato.data.response.ResponseCode;
import com.tomato.data.response.SingleResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 通道发送请求
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Slf4j
public class ChannelSendFeignClientFallback implements ChannelSendFeignClient {

    @Override
    public SingleResponse<ChannelSendRep> send(ChannelSendReq channelSendReq) {
        log.error("feign远程调用系统用户服务异常后的降级方法");
        return SingleResponse.buildFailure(ResponseCode.FAILURE.getCode(),"feign远程调用通道服务异常后的降级方法");
    }
}
