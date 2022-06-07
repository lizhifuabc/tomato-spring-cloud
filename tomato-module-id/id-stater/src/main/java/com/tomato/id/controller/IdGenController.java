package com.tomato.id.controller;

import com.tomato.data.dto.rep.Response;
import com.tomato.data.dto.rep.SingleResponse;
import com.tomato.id.strategy.IdGeneratorStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ID 生成器控制器
 *
 * @author lizhifu
 * @date 2022/5/27
 */
@RestController
@RequestMapping("/id-api/")
public class IdGenController {
    private final IdGeneratorStrategy idGeneratorStrategy;

    public IdGenController(IdGeneratorStrategy idGeneratorStrategy) {
        this.idGeneratorStrategy = idGeneratorStrategy;
    }

    @RequestMapping("/id")
    public Response id() {
        long nextId = idGeneratorStrategy.nextId();
        return SingleResponse.of(nextId);
    }
}
