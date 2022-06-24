package com.tomato.channel.controller;

import com.tomato.channel.dto.ChannelSendRep;
import com.tomato.channel.dto.ChannelSendReq;
import com.tomato.data.response.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 通道发送请求
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@RestController
@Slf4j
@RequestMapping("/channel")
public class ChannelSendController {
    @PostMapping("/send")
    public SingleResponse<ChannelSendRep> send(@Valid @RequestBody ChannelSendReq channelSendReq){
        log.info("Begin to send message to channel:{}",channelSendReq);
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        log.info("End sending message to channel, Time used: "+ (endTime - startTime)+":{}",channelSendReq);

        ChannelSendRep channelSendRep = new ChannelSendRep();
        Long sign = System.currentTimeMillis();
        if(sign%2==0){
            channelSendRep.setSendUrl("http://www.baidu.com");
        }else {
            throw new RuntimeException("发送失败");
        }
        return SingleResponse.of(channelSendRep);
    }
}
