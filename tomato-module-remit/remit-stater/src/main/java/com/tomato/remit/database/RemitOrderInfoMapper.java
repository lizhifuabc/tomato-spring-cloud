package com.tomato.remit.database;

import com.tomato.remit.database.dataobject.CompleteOrderDO;
import com.tomato.remit.database.dataobject.RemitOrderInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 根据单号查询打款订单表
     * @param remitOrderNo
     * @return
     */
    @Select("select * from remit_order_info where remit_order_no = #{remitOrderNo}")
    RemitOrderInfoDO selectByRemitOrderNo(@Param("remitOrderNo") String remitOrderNo);

    /**
     * 更新打款状态:人工处理
     * @param completeOrderDO
     */
    void manualProcess(CompleteOrderDO completeOrderDO);
    /**
     * 更新打款状态:失败
     * @param completeOrderDO
     */
    void remitFail(CompleteOrderDO completeOrderDO);
    /**
     * 更新打款状态:成功
     * @param completeOrderDO
     */
    void remitSuccess(CompleteOrderDO completeOrderDO);
}
