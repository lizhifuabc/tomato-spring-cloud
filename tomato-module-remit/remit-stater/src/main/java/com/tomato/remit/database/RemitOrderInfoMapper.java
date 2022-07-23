package com.tomato.remit.database;

import com.tomato.remit.database.dataobject.RemitOrderInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 打款订单表
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@Mapper
public interface RemitOrderInfoMapper {
    /**
     * 插入打款订单表
     *
     * @param remitOrderInfoDO 打款订单表
     * @return 插入结果
     */
    void insert(RemitOrderInfoDO remitOrderInfoDO);


    /**
     * 查询打款订单表
     * @param merchantNo
     * @param remitRequestNo
     * @return
     */
    @Select("select count(*) from remit_order_info where merchant_no = #{merchantNo} and remit_request_no = #{remitRequestNo} limit 1")
    boolean checkThirdNo(@Param("merchantNo") String merchantNo, @Param("remitRequestNo") String remitRequestNo);
}
