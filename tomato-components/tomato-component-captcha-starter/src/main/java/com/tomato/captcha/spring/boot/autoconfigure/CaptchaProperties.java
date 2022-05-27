package com.tomato.captcha.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.awt.*;

/**
 * 验证码配置
 *
 * @author lizhifu
 * @date 2022/5/26
 */
@ConfigurationProperties(prefix = CaptchaProperties.PREFIX)
public class CaptchaProperties {
    public static final String PREFIX = "tomato.captcha";
    /**
     * 验证码内容长度
     */
    private int length = 4;
    /**
     * 验证码宽度
     */
    private int width = 120;
    /**
     * 验证码高度
     */
    private int height = 36;

    /**
     * 验证码字体
     */
    private String fontName = "Verdana";

    /**
     * 字体风格
     */
    private Integer fontStyle = Font.PLAIN;

    /**
     * 字体大小
     */
    private int fontSize = 20;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public Integer getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(Integer fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public String toString() {
        return "CaptchaProperties{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", fontName='" + fontName + '\'' +
                ", fontStyle=" + fontStyle +
                ", fontSize=" + fontSize +
                '}';
    }
}
