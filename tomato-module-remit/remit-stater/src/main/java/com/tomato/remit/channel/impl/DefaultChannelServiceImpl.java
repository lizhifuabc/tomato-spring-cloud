package com.tomato.remit.channel.impl;

import com.tomato.remit.channel.ChannelService;
import com.tomato.remit.channel.dto.SendRemitResult;
import com.tomato.remit.dto.RemitOrderReq;
import com.tomato.remit.enums.RemitStatusEnum;
import com.tomato.utils.RandomUtil;
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
        // TODO 打款下单，模拟打款结果
        result.setRemitStatus(RemitStatusEnum.REMIT_ING.getCode());
        result.setExceptionCode("200");
        result.setExceptionInfo("打款处理中");
        int random = RandomUtil.randomInt(10);
        // 模拟打款各种情况
        if (random < 2) {
            // 直连类型：打款成功
            result.setRemitStatus(RemitStatusEnum.REMIT_SUCCESS.getCode());
            result.setExceptionCode("200");
            result.setExceptionInfo("打款成功");
        }else if (random < 4) {
            // 直连类型：打款失败-->据单
            result.setRemitStatus(RemitStatusEnum.REMIT_FAIL.getCode());
            result.setExceptionCode("100");
            result.setExceptionInfo("EXCEPTION_INFO:据单");
        } else if (random < 6) {
            // 非直连类型：收单失败-->打款失败-只有针对拒绝订单这种情况
            result.setRemitStatus(RemitStatusEnum.REMIT_FAIL.getCode());
            result.setExceptionCode("100");
            result.setExceptionInfo("EXCEPTION_INFO:据单");
        } else if (random < 8) {
            // 其他所有，未知或者异常，进入人工处理流程
            result.setRemitStatus(RemitStatusEnum.MANUAL_PROCESS.getCode());
            result.setExceptionCode("200");
            result.setExceptionInfo("EXCEPTION_INFO:其他所有，未知或者异常，进入人工处理流程");
        }
        return result;
    }
}
