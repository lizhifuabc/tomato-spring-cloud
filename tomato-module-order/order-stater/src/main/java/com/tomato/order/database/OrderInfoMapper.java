package com.tomato.order.database;

import com.tomato.order.database.dataobject.OrderInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 校验商户订单号
     * @param merchantNo
     * @param merchantOrderNo
     * @return
     */
    @Select("select exists (select 1 from order_info where merchant_no = #{merchantNo} and merchant_order_no = #{merchantOrderNo} limit 1)")
    boolean checkMerchantOrderNo(@Param("merchantNo") String merchantNo,@Param("merchantOrderNo") String merchantOrderNo);
}
