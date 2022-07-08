package com.tomato.utils.crypto;

import com.tomato.utils.HexUtil;
import com.tomato.utils.StringUtils;
import com.tomato.utils.crypto.digest.HmacAlgorithm;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * HMAC摘要算法<br>
 * HMAC，全称为“Hash Message Authentication Code”，中文名“散列消息鉴别码”<br>
 * 主要是利用哈希算法，以一个密钥和一个消息为输入，生成一个消息摘要作为输出。<br>
 * 一般的，消息鉴别码用于验证传输于两个共 同享有一个密钥的单位之间的消息。<br>
 * HMAC 可以与任何迭代散列函数捆绑使用。MD5 和 SHA-1 就是这种散列函数。HMAC 还可以使用一个用于计算和确认消息鉴别值的密钥。<br>
 *
 * @author lizhifu
 * @date 2022/7/8
 */
public class HMacUtil {
    /**
     * 实现Hmac系列的加密算法HmacSHA1、HmacMD5等
     *
     * @param input 需要加密的输入参数
     * @param key 密钥
     * @param hmacAlgorithm 选择加密算法
     * @return 加密后的值
     **/
    public static String encrypt(String input, String key, HmacAlgorithm hmacAlgorithm) {
        String cipher = "";
        try {
            byte[] data = key.getBytes(StandardCharsets.UTF_8);
            //根据给定的字节数组构造一个密钥，第二个参数指定一个密钥的算法名称，生成HmacSHA1专属密钥
            SecretKey secretKey = new SecretKeySpec(data, hmacAlgorithm.getValue());
            //生成一个指定Mac算法的Mac对象
            Mac mac = Mac.getInstance(hmacAlgorithm.getValue());
            //用给定密钥初始化Mac对象
            mac.init(secretKey);
            byte[] text = input.getBytes(StandardCharsets.UTF_8);
            byte[] encryptByte = mac.doFinal(text);
            cipher = HexUtil.encodeHexStr(encryptByte);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return cipher;
    }

    public static void main(String[] args) {
        System.out.println(HMacUtil.encrypt("123456", "123456", HmacAlgorithm.HmacSHA512));
    }
}
