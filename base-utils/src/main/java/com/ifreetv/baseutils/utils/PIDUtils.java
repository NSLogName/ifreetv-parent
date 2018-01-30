/**
 * PIDUtils.java
 *
 * @author huangwei
 * @since 2016-8-30
 * 描述：
 */
package com.ifreetv.baseutils.utils;

import java.lang.management.ManagementFactory;

/**
 * PIDUtils.java
 *
 * @author huangwei
 * @since 2016-8-30
 * 描述：
 */
public class PIDUtils {
    /**
     * 获取当前进程PID
     */
    public static String getCurrentPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        return pid;
    }

    /**
     * 获取当前进程唯一标识
     */
    public static String getCurrentProcessUniqueMarks() {
        return IPUtils.getLocalIP() + "_" + getCurrentPid();
    }

}
