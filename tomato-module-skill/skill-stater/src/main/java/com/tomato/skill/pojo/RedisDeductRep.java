package com.tomato.skill.pojo;

import com.tomato.data.dto.rep.RepObject;
import lombok.Data;

/**
 * RedisDeduct
 *
 * @author lizhifu
 * @date 2022/5/30
 */
@Data
public class RedisDeductRep extends RepObject {
    /**
     * 库存 Key
     */
    private String skillKey;
    /**
     * 锁 Key，一个秒杀库存一个锁
     */
    private String lockKey;
}
