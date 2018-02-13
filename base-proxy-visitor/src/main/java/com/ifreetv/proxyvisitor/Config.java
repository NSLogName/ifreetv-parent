package com.ifreetv.proxyvisitor;

import java.net.URL;

/*******************************
 * @Title: Config
 * @package com.ifreetv.proxyvisitor
 * @Description:抓取代理的相关设置
 *
 * @author XCTY
 * @date 2017/12/12 10:55
 * @version
 ********************************/
public class Config {
    public final static String URL_ADDRESS;

    public static final String URL_KUAI_ADDRESS;

    /** 抓取代理地址服务执行间隔时间 */
    public final static int PROXY_SPIDER_SLEEP_TIME;

    /** 连接超时时间 */
    public final static int CONNECT_TIMEOUT;

    /** 读取数据超时时间 */
    public final static int READ_TIMEOUT;

    static {
        URL_KUAI_ADDRESS = "https://www.kuaidaili.com/ops/";
        URL_ADDRESS = "http://www.xicidaili.com/wt/";
        PROXY_SPIDER_SLEEP_TIME = 60 * 1000;
        CONNECT_TIMEOUT = 30;
        READ_TIMEOUT = 30;
    }
}
