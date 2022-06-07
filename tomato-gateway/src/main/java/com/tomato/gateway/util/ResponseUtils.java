package com.tomato.gateway.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomato.data.dto.rep.IResponseCode;
import com.tomato.data.dto.rep.Response;
import lombok.SneakyThrows;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 异常响应工具类
 *
 * @author lizhifu
 * @date 2022/6/4
 */
public class ResponseUtils {
    @SneakyThrows
    public static Mono<Void> writeErrorInfo(ServerHttpResponse response, IResponseCode responseCode) {
        Response responseDto = Response.buildFailure(responseCode.getCode(), responseCode.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        DataBuffer buffer = response.bufferFactory().wrap(objectMapper.writeValueAsString(responseDto).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> DataBufferUtils.release(buffer));
    }
}
