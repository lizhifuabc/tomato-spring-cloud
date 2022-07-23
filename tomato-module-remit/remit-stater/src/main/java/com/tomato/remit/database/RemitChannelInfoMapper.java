package com.tomato.remit.database;

import com.tomato.remit.database.dataobject.RemitChannelInfoDO;
import com.tomato.remit.database.dataobject.RemitChannelInfoQueryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 打款渠道表
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Mapper
public interface RemitChannelInfoMapper {
    /**
     * 查询打款渠道
     * @param remitChannelInfoQueryDO
     * @return
     */
    List<RemitChannelInfoDO> selectByMerchantNo(RemitChannelInfoQueryDO remitChannelInfoQueryDO);
}
