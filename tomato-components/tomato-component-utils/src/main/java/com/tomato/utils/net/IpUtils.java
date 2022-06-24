package com.tomato.utils.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP 工具类
 *
 * @author lizhifu
 * @date 2022/6/24
 */
public class IpUtils {
    /**
     * 获取IP地址
     *
     * @return 本地IP地址
     */
    public static String getHostIp()
    {
        try
        {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e)
        {
        }
        return "127.0.0.1";
    }
}
