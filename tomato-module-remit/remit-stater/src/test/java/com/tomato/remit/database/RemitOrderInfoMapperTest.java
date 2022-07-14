package com.tomato.remit.database;

import com.tomato.remit.database.dataobject.RemitOrderInfoDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * RemitOrderInfoMapper
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@SpringBootTest
public class RemitOrderInfoMapperTest {
    @Resource
    private RemitOrderInfoMapper remitOrderInfoMapper;

    @Test
    public void insert() {
        RemitOrderInfoDO remitOrderInfoDO = new RemitOrderInfoDO();
        remitOrderInfoDO.setRemitRequestNo("remitRequestNo");
        remitOrderInfoDO.setRemitOrderNo("remitOrderNo");
        remitOrderInfoDO.setMerchantNo("merchantNo");
        remitOrderInfoDO.setMerchantName("merchantName");
        remitOrderInfoDO.setUrgent(0);
        remitOrderInfoDO.setAccountName("accountName");
        remitOrderInfoDO.setAccountNo("accountNo");
        remitOrderInfoDO.setBankName("bankName");
        remitOrderInfoDO.setBankNo("bankNo");
        remitOrderInfoDO.setProvince("province");
        remitOrderInfoDO.setCity("city");
        remitOrderInfoDO.setBranchBankName("bankBranch");
        remitOrderInfoDO.setRequestAmount(new BigDecimal(100));
        remitOrderInfoDO.setRemitChannelCode("remitChannelCode");
        remitOrderInfoMapper.insert(remitOrderInfoDO);
    }
}
