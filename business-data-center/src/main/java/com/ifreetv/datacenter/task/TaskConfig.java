package com.ifreetv.datacenter.task;

/*******************************
 * @Title TaskConfig
 * @package com.ifreetv.datacenter.task
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 15:58
 * @version
 ********************************/

/**
 *  以max开头的是最长执行时间，如果超过这个时间，则会认定为线程出现问题
 */
public class TaskConfig {
    /**
     * IPTV抓取间隔时间
     */
    private static long iptvIntervalTime;

    /**
     * IPTV抓取最长间隔时间
     */
    private static long maxIptvIntervalTime;

    public static long getIptvIntervalTime() {
        return iptvIntervalTime;
    }

    public static void setIptvIntervalTime(long iptvIntervalTime) {
        TaskConfig.iptvIntervalTime = iptvIntervalTime;
    }

    public static long getMaxIptvIntervalTime() {
        return maxIptvIntervalTime;
    }

    public static void setMaxIptvIntervalTime(long maxIptvIntervalTime) {
        TaskConfig.maxIptvIntervalTime = maxIptvIntervalTime;
    }

    @Override
    public String toString() {
        return "TaskConfig{}";
    }
}
