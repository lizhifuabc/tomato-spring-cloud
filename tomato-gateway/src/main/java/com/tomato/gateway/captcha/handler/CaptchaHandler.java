package com.tomato.gateway.captcha.handler;

import com.tomato.captcha.spring.boot.autoconfigure.component.CaptchaComponent;
import com.tomato.captcha.spring.boot.autoconfigure.enums.CaptchaTypeEnum;
import com.tomato.data.dto.response.SingleResponse;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证码处理器
 *
 * @author lizhifu
 * @date 2022/5/26
 */
@Component
public class CaptchaHandler implements HandlerFunction<ServerResponse> {
    private static final CaptchaTypeEnum captchaType = CaptchaTypeEnum.CHINESE;
    private final CaptchaComponent captchaComponent;

    public CaptchaHandler(CaptchaComponent captchaComponent) {
        this.captchaComponent = captchaComponent;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        Captcha captcha = captchaComponent.getCaptcha(captchaType);

        // 获取到验证码Base64编码字符串
        String captchaBase64 = captcha.toBase64();
        Map<String, String> result = new HashMap<>(2);
        result.put("uuid", "uuid");
        result.put("img", captchaBase64);
        return ServerResponse.ok().body(BodyInserters.fromValue(SingleResponse.of(result)));
    }
}
