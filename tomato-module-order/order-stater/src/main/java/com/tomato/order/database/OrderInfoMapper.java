package com.tomato.order.database;

import com.tomato.order.database.dataobject.OrderInfoCompleteDO;
import com.tomato.order.database.dataobject.OrderInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("select * from order_info where order_no = #{orderNo}")
    OrderInfoDO selectByOrderNo(@Param("orderNo") Long orderNo);

    /**
     * 完成订单
     * @param orderInfoCompleteDO
     * @return
     */
    int complete(OrderInfoCompleteDO orderInfoCompleteDO);

    @Update("update order_info set order_status = 400,version = version +1 where order_no = #{orderNo} and order_status = 200 and version = #{version}")
    int refund(@Param("orderNo") String orderNo,@Param("version") Integer version);
}
