package com.tomato.utils.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * 网络相关工具
 * copy from <a href="https://gitee.com/dromara/hutool">hutool</a>
 * @author lizhifu
 * @date 2022/7/5
 */
public class NetUtil {
    /**
     * 获得指定地址信息中的硬件地址
     *
     * @param inetAddress {@link InetAddress}
     * @return 硬件地址
     * @since 5.7.3
     */
    public static byte[] getHardwareAddress(InetAddress inetAddress) {
        if (null == inetAddress) {
            return null;
        }

        try {
            final NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
            if (null != networkInterface) {
                return networkInterface.getHardwareAddress();
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
