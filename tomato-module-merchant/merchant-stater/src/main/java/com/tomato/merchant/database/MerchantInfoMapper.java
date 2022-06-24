package com.tomato.merchant.database;

import com.tomato.merchant.database.dataobject.MerchantInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 商户信息
 *
 * @author lizhifu
 * @date 2022/6/18
 */
@Mapper
public interface MerchantInfoMapper {
    /**
     * 插入商户信息
     * @param merchantInfoDO
     * @return
     */
    int insert(MerchantInfoDO merchantInfoDO);

    /**
     * 查询商户信息
     * @param merchantNo
     * @return
     */
    @Select("select * from merchant_info where merchant_no = #{merchantNo} and status = 0")
    MerchantInfoDO selectByMerchantNo(@Param("merchantNo") String merchantNo);

    /**
     * 查询商户信息
     * @param mail
     * @return
     */
    @Select("select * from merchant_info where mail = #{mail}")
    MerchantInfoDO selectByMail(@Param("mail") String mail);

    /**
     * 检查邮箱是否存在
     * @param mail
     * @return
     */
    @Select("select exists (select 1 from merchant_info where mail = #{mail} LIMIT 1)")
    boolean checkMail(@Param("mail") String mail);
}
