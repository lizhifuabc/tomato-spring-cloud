package com.tomato.skill.controller;

import com.tomato.data.dto.rep.Response;
import com.tomato.data.dto.rep.SingleResponse;
import com.tomato.skill.component.RedisSkillComponent;
import com.tomato.skill.component.SkillComponent;
import com.tomato.skill.constants.RedisKeyConstants;
import com.tomato.skill.dto.SkillReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 活动
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@RestController
@RequestMapping("/skill")
public class SkillController {
    private final SkillComponent skillComponent;
    private final RedisSkillComponent redisSkillComponent;
    public SkillController(SkillComponent skillComponent, RedisSkillComponent redisSkillComponent) {
        this.skillComponent = skillComponent;
        this.redisSkillComponent = redisSkillComponent;
    }

    @PostMapping("/skill")
    public Response skill(@Valid @RequestBody SkillReq skillReq) {
        skillComponent.skill(skillReq);
        return Response.buildSuccess();
    }
    @RequestMapping("/info")
    public SingleResponse skill(@RequestParam Long activityRelationId) {
        Integer usedSkillCount = redisSkillComponent.usedSkillCount(activityRelationId);
        return SingleResponse.of(usedSkillCount);
    }
}
