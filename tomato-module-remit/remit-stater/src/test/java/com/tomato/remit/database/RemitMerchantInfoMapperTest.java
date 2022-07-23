package com.tomato.remit.database;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * RemitMerchantInfoMapper
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@SpringBootTest
public class RemitMerchantInfoMapperTest {
    @Resource
    private RemitMerchantInfoMapper remitMerchantInfoMapper;

    @Test
    public void selectChannelByMerchantNo() {
        List<String> channelList = remitMerchantInfoMapper.selectChannelByMerchantNo("M000001");
        System.out.println(channelList);
    }
}
