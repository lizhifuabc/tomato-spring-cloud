package com.tomato.order;

import org.apache.shardingsphere.sharding.algorithm.keygen.SnowflakeKeyGenerateAlgorithm;

/**
 * SnowflakeKeyGenerateAlgorithm
 *
 * @author lizhifu
 * @date 2022/7/2
 */
public class SnowflakeKeyGenerateAlgorithmTest {
    public static void main(String[] args) {
        SnowflakeKeyGenerateAlgorithm snowflakeKeyGenerateAlgorithm = new SnowflakeKeyGenerateAlgorithm();
        for (int i = 0; i < 10; i++) {
            System.out.println(snowflakeKeyGenerateAlgorithm.generateKey());
        }
    }
}
