package com.tomato.id.api;

import com.tomato.data.dto.rep.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Feign 客户端
 *
 * @author lizhifu
 * @date 2022/5/27
 */
@FeignClient(name = "tomato-module-id", contextId = "id")
public interface IdGenFeignClient {
    @GetMapping("/id-api/id")
    Response nextId();
}