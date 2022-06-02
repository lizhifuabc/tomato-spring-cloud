package com.tomato.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashMapTest
 *
 * @author lizhifu
 * @date 2022/5/31
 */
public class HashMapTest {
    public static void main(String[] args) {
        System.out.println();
        // 初始化一组字符串
        List<String> list = new ArrayList<>();
        list.add("fasdfsad");
        list.add("lo12312pi");
        list.add("asdfas");
        list.add("jhofajos");
        list.add("12312");
        list.add("fasfasdf");

        // 定义要存放的数组
        String[] tab = new String[16];

        // 循环存放
        for (String key : list) {
            int idx = key.hashCode() & (tab.length - 1);  // 计算索引位置
            System.out.println(String.format("key值=%s Idx=%d", key, idx));
            if (null == tab[idx]) {
                tab[idx] = key;
                continue;
            }
            tab[idx] = tab[idx] + "->" + key;
        }
        // 输出测试结果
        System.out.println(tab.length);
    }
    static final int hash(Object key) {
        int h;
        // 如果key为null，返回0
        // hashCode的高16位异或低16位得到哈希值
        // 主要从性能、哈希碰撞角度考虑，减少系统开销，不会造成因为高位没有参与下标计算从而引起的碰撞。
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
