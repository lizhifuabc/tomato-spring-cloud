package com.tomato.remit.service;

import com.tomato.remit.database.RemitOrderInfoMapper;
import com.tomato.remit.database.dataobject.RemitOrderInfoDO;
import com.tomato.remit.exception.RemitException;
import com.tomato.remit.exception.RemitResponseCode;
import com.tomato.utils.lang.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 打款
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Service
@Slf4j
public class RemitOrderService {
    private final RemitOrderInfoMapper remitOrderInfoMapper;
    private final Snowflake snowflake;
    public RemitOrderService(RemitOrderInfoMapper remitOrderInfoMapper, Snowflake snowflake) {
        this.remitOrderInfoMapper = remitOrderInfoMapper;
        this.snowflake = snowflake;
    }
    public void createOrder(RemitOrderInfoDO remitOrderInfoDO) {
        // 打款系统属于内部服务，暂时不需要 redis 唯一性检查
        // TODO 重复收单应该怎么返回比较好？
        if (remitOrderInfoMapper.checkThirdNo(remitOrderInfoDO.getMerchantNo(),remitOrderInfoDO.getRemitRequestNo())){
            log.error("打款已存在，不能重复插入:{}",remitOrderInfoDO);
            throw new RemitException(RemitResponseCode.REMIT_EXIST);
        }
        remitOrderInfoDO.setRemitOrderNo(snowflake.nextIdStr());
        remitOrderInfoMapper.insert(remitOrderInfoDO);
    }
}
