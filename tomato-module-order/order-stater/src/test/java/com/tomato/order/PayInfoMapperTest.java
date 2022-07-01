package com.tomato.order;

import com.tomato.order.database.PayInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * PayInfoMapper
 *
 * @author lizhifu
 * @date 2022/7/1
 */
@SpringBootTest
public class PayInfoMapperTest {
    @Resource
    private PayInfoMapper payInfoMapper;
    @Test
    public void test(){
        payInfoMapper.selectByPayNo("123");
    }
}
