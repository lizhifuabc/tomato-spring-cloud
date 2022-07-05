package com.tomato.order;

import com.tomato.utils.lang.Pid;

/**
 * Pid
 *
 * @author lizhifu
 * @date 2022/7/5
 */
public class PidTest {
    public static void main(String[] args) {
        System.out.println(Pid.INSTANCE.get());
    }
}
