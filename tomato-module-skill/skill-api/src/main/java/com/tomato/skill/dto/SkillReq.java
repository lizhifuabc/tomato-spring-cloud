package com.tomato.skill.dto;

import com.tomato.data.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 秒杀资格请求
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@Data
public class SkillReq extends Command {
    @NotNull(message = "活动ID不能为空")
    private Long activityId;
    @NotNull(message = "userId不能为空")
    private Long userId;
}
