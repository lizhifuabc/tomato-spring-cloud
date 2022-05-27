package com.tomato.validator.spring.boot.autoconfigure.validator;


import com.tomato.validator.spring.boot.autoconfigure.annotation.Mobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号校验
 *
 * @author lizhifu
 * @date 2021/12/10
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$"
    );

    @Override
    public void initialize(Mobile constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.length() == 0 ) {
            return false;
        }
        Matcher m = PHONE_PATTERN.matcher(value);
        return m.matches();
    }
}
