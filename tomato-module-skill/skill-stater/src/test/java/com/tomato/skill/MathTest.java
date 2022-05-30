package com.tomato.skill;

/**
 * MathTest
 *
 * @author lizhifu
 * @date 2022/5/28
 */
public class MathTest {
    public static void main(String[] args) {
        System.out.println(1001 / 200);
        System.out.println(1100 % 200);
        int a = 100;
        int b = 200;

        int c = 200;
        if(a != c && b != c) {
            System.out.println("a != c || b != c");
        }
    }
}
