package com.tomato.order.database;

import com.tomato.order.database.dataobject.PayInfoDO;
import com.tomato.order.database.dataobject.PayInfoSelectDO;
import com.tomato.order.enums.PayStatusEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 支付信息表
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Mapper
public interface PayInfoMapper {
    /**
     * 插入支付信息
     * @param payInfoDO
     */
    void insert(PayInfoDO payInfoDO);

    /**
     * 完成支付 {@link PayStatusEnum}
     * @param payNo
     * @param version
     * @param payStatus
     * @return
     */
    int complete(@Param("payNo") String payNo,@Param("version") Integer version,@Param("payStatus") Integer payStatus);

    /**
     * 退款  {@link PayStatusEnum}
     * @param payNo
     * @param version
     * @return
     */
    @Update("update pay_info set pay_status = 400,version = version +1 where pay_no = #{payNo} and pay_status = 200 and version = #{version}")
    int refund(@Param("payNo") String payNo,@Param("version") Integer version);

    @Select("select order_no,version,pay_no,pay_status from pay_info where pay_no = #{payNo}")
    PayInfoSelectDO selectByPayNo(@Param("payNo")String payNo);
}