package com.tomato.remit.database;

import com.tomato.remit.database.dataobject.RemitChannelInfoDO;
import com.tomato.remit.database.dataobject.RemitChannelInfoQueryDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * RemitChannelInfoMapper
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@SpringBootTest
public class RemitChannelInfoMapperTest {
    @Resource
    private RemitChannelInfoMapper remitChannelInfoMapper;

    @Test
    public void selectByMerchantNo() {
        RemitChannelInfoQueryDO remitChannelInfoQueryDO = new RemitChannelInfoQueryDO();
        remitChannelInfoQueryDO.setMerchantNo("M000001");
        remitChannelInfoQueryDO.setChannelSpeed(0);
        List<RemitChannelInfoDO> remitChannelInfoDOS = remitChannelInfoMapper.selectByMerchantNo(remitChannelInfoQueryDO);
        System.out.println(remitChannelInfoDOS);
    }
}
