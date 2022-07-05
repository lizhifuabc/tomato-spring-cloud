package com.tomato.utils;

/**
 * 字符串工具类
 *
 * @author lizhifu
 * @date 2022/6/20
 */
public class StringUtils {
    /**
     * @see #isBlank(CharSequence)
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
    /**
     * 判断字符串中是否全是空白字符
     *
     * @param cs 需要判断的字符串
     * @return 如果字符串序列是 null 或者全是空白，返回 true
     */
    public static boolean isBlank(CharSequence cs) {
        if (cs != null) {
            int length = cs.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
