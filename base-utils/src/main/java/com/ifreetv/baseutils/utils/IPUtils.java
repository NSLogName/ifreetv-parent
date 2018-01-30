/**
 * IPUtils.java
 *
 * @author huangwei
 * @since 2016-8-30
 * 描述：
 */
package com.ifreetv.baseutils.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IPUtils.java
 *
 * @author huangwei
 * @since 2016-8-30
 * 描述：
 */
public class IPUtils {
    /**
     * 获取本地ip
     */
    public static String getLocalIP() {
        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            return ip;
        } catch (UnknownHostException e) {
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return null;
        }

    }
}
