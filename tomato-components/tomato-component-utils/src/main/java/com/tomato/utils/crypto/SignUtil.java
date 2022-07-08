package com.tomato.utils.crypto;

import com.tomato.utils.crypto.digest.HmacAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * 签名
 *
 * @author lizhifu
 * @date 2022/7/8
 */
public class SignUtil {
    /**
     * 签名-不区分大小写的顺序排序
     *
     * @param map 参数
     * @param secret 秘钥
     * @return 签名
     */
    public static String sign(Map<String,Object> map, String secret, HmacAlgorithm algorithm) {
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(null != entry.getValue() && !"".equals(entry.getValue())){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "secret=" + secret;
        result = HMacUtil.encrypt(result, secret, algorithm).toUpperCase();
        return result;
    }
}
