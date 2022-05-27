package com.tomato.captcha.spring.boot.autoconfigure;

import com.tomato.captcha.spring.boot.autoconfigure.component.CaptchaComponent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 自动配置类
 *
 * @author lizhifu
 * @date 2022/5/26
 */
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(CaptchaComponent.class)
    public CaptchaComponent captchaService(CaptchaProperties captchaProperties) {
        return new CaptchaComponent(captchaProperties);
    }
}
