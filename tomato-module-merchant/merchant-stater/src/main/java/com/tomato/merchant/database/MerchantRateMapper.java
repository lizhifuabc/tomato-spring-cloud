package com.tomato.merchant.database;

import com.tomato.merchant.database.dataobject.MerchantRateDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 商户费率
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Mapper
public interface MerchantRateMapper {
    /**
     * 根据商户编号和支付类型查询商户费率
     * @param merchantNo
     * @param payType
     * @return
     */
    @Select("select * from merchant_rate where merchant_no = #{merchantNo} and pay_type = #{payType} and status = 0")
    MerchantRateDO selectByMerchantNoAndPayType(@Param("merchantNo") String merchantNo,@Param("payType") Integer payType);
}
