package com.tomato.skill.controller;

import com.tomato.skill.exception.SkillException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 *
 * @author lizhifu
 * @date 2022/5/28
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/hello")
    public String hello() {
        throw new SkillException("1000","hello");
    }
}
