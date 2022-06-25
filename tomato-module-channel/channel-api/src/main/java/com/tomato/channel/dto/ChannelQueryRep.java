package com.tomato.channel.dto;

import com.tomato.data.dto.Rep;
import lombok.Data;

/**
 * 查单返回
 *
 * @author lizhifu
 * @date 2022/6/25
 */
@Data
public class ChannelQueryRep extends Rep {
    private boolean success;
}
