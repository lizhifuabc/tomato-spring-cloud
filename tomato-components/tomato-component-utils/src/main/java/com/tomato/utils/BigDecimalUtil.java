package com.tomato.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal
 *
 * @author lizhifu
 * @date 2022/6/22
 */
public class BigDecimalUtil {
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b).setScale(2, RoundingMode.HALF_UP);
    }
}
