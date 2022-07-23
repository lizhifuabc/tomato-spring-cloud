package com.tomato.remit.database;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户绑定打款渠道
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Mapper
public interface RemitMerchantInfoMapper {
    /**
     * 查询商户绑定打款渠道
     * @param merchantNo
     * @return
     */
    List<String> selectChannelByMerchantNo(@Param("merchantNo") String merchantNo);
}
