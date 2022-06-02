package com.tomato.skill;

/**
 * MathTest
 *
 * @author lizhifu
 * @date 2022/5/28
 */
public class MathTest {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(8));
        System.out.println(Integer.toBinaryString(8-1));
        System.out.println(Integer.toBinaryString(16));
        System.out.println(Integer.toBinaryString(16-1));
        System.out.println(tableSizeFor(16));
        System.out.println(Integer.toBinaryString(15));
        System.out.println("10:"+Integer.toBinaryString(9|9>>>1));

        int x = "程序员".hashCode();
        System.out.println(Integer.toBinaryString(hash(x)));
        System.out.println("与运营"+Integer.toBinaryString(10 & hash(x)));


        System.out.println(x);
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(x >>> 16));
        // %是取余，Math.floorMod(a,b)是取模。
        // 取余运算是取商更接近于0得到的余数，取模运算是取商更接近于负⽆穷得到的余数。
        System.out.println(11 & 10);
        System.out.println(11 % 10);
        System.out.println(11 / 10);
        System.out.println(Math.floorMod(11, 10));
    }
    static final int hash(Object key) {
        int h;
        // 如果key为null，返回0
        // hashCode的高16位异或低16位得到哈希值
        // 主要从性能、哈希碰撞角度考虑，减少系统开销，不会造成因为高位没有参与下标计算从而引起的碰撞。
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return  n + 1;
    }
}
