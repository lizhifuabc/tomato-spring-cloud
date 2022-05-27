package com.tomato.module.sys.biz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志演示
 *
 * @author lizhifu
 * @date 2022/5/20
 */
@RestController
@Slf4j
public class LogDemoController {
    @GetMapping("/log/demo")
    public String demo(DemoReq req) {
        String result = "success";
        // 入参
        log.info("demo begin param:{}",req);
        try {
            Thread.sleep(1000);
        }catch(Exception e){
            // 记录关键信息，方便排查问题，因为 exception 内容较长，记录关键信息能够快速记录
            log.error("demo fail,name：{}",req.getName());
            log.error("demo fail,exception",e);
        }
        // 出参
        log.info("demo end param:{}",result);
        return result;
    }
}
