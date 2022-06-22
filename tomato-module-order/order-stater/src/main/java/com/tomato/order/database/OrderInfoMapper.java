package com.tomato.order.database;

import com.tomato.order.database.dataobject.OrderInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * order_info
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Mapper
public interface OrderInfoMapper {
    /**
     * 插入订单
     * @param orderInfoDO
     */
    void insert(OrderInfoDO orderInfoDO);
}
