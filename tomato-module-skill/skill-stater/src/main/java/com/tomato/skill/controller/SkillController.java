package com.tomato.skill.controller;

import com.tomato.data.dto.rep.Response;
import com.tomato.skill.component.SkillComponent;
import com.tomato.skill.dto.SkillReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public SkillController(SkillComponent skillComponent) {
        this.skillComponent = skillComponent;
    }

    @PostMapping("/skill")
    public Response skill(@Valid @RequestBody SkillReq skillReq) {
        skillComponent.skill(skillReq);
        return Response.buildSuccess();
    }
}
