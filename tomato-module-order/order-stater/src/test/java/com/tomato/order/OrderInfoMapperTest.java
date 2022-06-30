package com.tomato.order;

import com.tomato.order.database.OrderInfoMapper;
import com.tomato.order.database.dataobject.OrderInfoDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * OrderInfoMapper
 *
 * @author lizhifu
 * @date 2022/6/29
 */
@SpringBootTest
public class OrderInfoMapperTest {
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Test
    public void test() {
        System.out.println(orderInfoMapper.checkMerchantOrderNo("1000000000", "1"));
//        orderInfoMapper.selectByOrderNo(1656134921123123L);
    }
    @Test
    public void insert(){
        OrderInfoDO orderInfoDO = new OrderInfoDO();
        orderInfoMapper.insert(orderInfoDO);
    }
}
