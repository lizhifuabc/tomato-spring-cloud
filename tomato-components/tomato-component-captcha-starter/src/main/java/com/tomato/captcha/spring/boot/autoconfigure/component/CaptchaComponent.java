package com.tomato.captcha.spring.boot.autoconfigure.component;

import com.tomato.captcha.spring.boot.autoconfigure.CaptchaProperties;
import com.tomato.captcha.spring.boot.autoconfigure.enums.CaptchaTypeEnum;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Component;

/**
 * 验证码组件
 *
 * @author lizhifu
 * @date 2022/5/26
 */
@Component
public class CaptchaComponent {
    private final CaptchaProperties captchaProperties;

    public CaptchaComponent(CaptchaProperties captchaProperties) {
        this.captchaProperties = captchaProperties;
    }

    public Captcha getCaptcha(CaptchaTypeEnum captchaTypeEnum) {
        Captcha captcha;
        // yield 关键字用于从 case 的代码块中返回值
        return switch (captchaTypeEnum) {
            case ARITHMETIC -> {
                captcha = new ArithmeticCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
                // 几位数运算，默认是两位
                captcha.setLen(2);
                yield captcha;
            }
            case CHINESE -> {
                captcha = new ChineseCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
                captcha.setLen(captchaProperties.getLength());
                yield captcha;
            }
            case CHINESE_GIF -> {
                captcha = new ChineseGifCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
                captcha.setLen(captchaProperties.getLength());
                yield captcha;
            }
            case GIF -> {
                // 最后一位是位数
                captcha = new GifCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
                captcha.setLen(captchaProperties.getLength());
                yield captcha;
            }
            case SPEC -> {
                captcha = new SpecCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
                captcha.setLen(captchaProperties.getLength());
                yield captcha;
            }
            default -> {
                throw new RuntimeException("验证码配置信息错误");
            }
        };
    }
}
