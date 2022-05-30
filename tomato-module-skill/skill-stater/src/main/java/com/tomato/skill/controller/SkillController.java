package com.tomato.skill.controller;

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
@RequestMapping("/skill/")
public class SkillController {
    @PostMapping
    public void skill(@Valid @RequestBody SkillReq skillReq) {

    }
}
