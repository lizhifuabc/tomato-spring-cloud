package com.tomato.web.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * hibernate参数验证配置
 * 校验模式：如果出现多个字段效验失败，会返回所有的验证失败错误信息。通常情况下，当第一个字段/参数效验失败时，直接返回。
 * 普通模式：默认使用的就是普通模式，校验完所有的属性之后，返回所有的验证失败信息。
 * 快速失败模式：只要有一个字段效验失败，直接返回
 * @author lizhifu
 * @date 2022/5/16
 */
//@Configuration
public class ValidatorAutoConfiguration implements WebMvcConfigurer {

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    /**
     * requestParam方式的校验
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {

        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(validator());
        return methodValidationPostProcessor;
    }


    @Override
    public org.springframework.validation.Validator getValidator() {
        return new SpringValidatorAdapter(validator());
    }
}
