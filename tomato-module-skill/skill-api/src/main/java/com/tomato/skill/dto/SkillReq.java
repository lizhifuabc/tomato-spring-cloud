package com.tomato.skill.dto;

import com.tomato.data.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 秒杀
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Data
public class SkillSkuRelationDeductReq extends Command {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotNull(message = "userId不能为空")
    private Long userId;
}
