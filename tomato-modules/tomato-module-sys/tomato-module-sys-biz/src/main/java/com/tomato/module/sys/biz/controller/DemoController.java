package com.tomato.module.sys.biz.controller;

import com.tomato.module.sys.biz.config.JwtProperties;
import com.tomato.module.sys.biz.vo.DemoReq;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * demo controller
 *
 * @author lizhifu
 * @date 2022/5/17
 */
@RestController
public class DemoController {
    @Resource
    private JwtProperties jwtProperties;
    @GetMapping("/demo")
    public String getDemo() {
        return jwtProperties.getAlias();
    }
    @GetMapping("/valid")
    public String valid(@Valid DemoReq demoReq, BindingResult result) {
        if (result.hasErrors()) {
            return result.getAllErrors().get(0).getDefaultMessage();
        }
        return "success";
    }
    @GetMapping("/validated")
    public String validated(@Validated DemoReq demoReq, BindingResult result) {
        if (result.hasErrors()) {
            return result.getAllErrors().get(0).getDefaultMessage();
        }
        return "success";
    }
}
