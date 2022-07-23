package com.tomato.remit.service;

import com.tomato.remit.database.RemitChannelInfoMapper;
import com.tomato.remit.database.dataobject.RemitChannelInfoDO;
import com.tomato.remit.database.dataobject.RemitChannelInfoQueryDO;
import com.tomato.remit.dto.RemitOrderReq;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通道服务
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Service
public class RemitChannelService {
    private final RemitChannelInfoMapper remitChannelInfoMapper;

    public RemitChannelService(RemitChannelInfoMapper remitChannelInfoMapper) {
        this.remitChannelInfoMapper = remitChannelInfoMapper;
    }

    public List<RemitChannelInfoDO> getChannel(RemitOrderReq remitOrderReq) {
        // TODO 通道筛选，金额，限流等等
        RemitChannelInfoQueryDO remitChannelInfoQueryDO = new RemitChannelInfoQueryDO();
        remitChannelInfoQueryDO.setMerchantNo(remitOrderReq.getMerchantNo());
        remitChannelInfoQueryDO.setChannelSpeed(0);
        return remitChannelInfoMapper.selectByMerchantNo(remitChannelInfoQueryDO);
    }
}
