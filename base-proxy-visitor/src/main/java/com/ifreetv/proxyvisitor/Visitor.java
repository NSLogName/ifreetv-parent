package com.ifreetv.proxyvisitor;

import com.machinepublishers.jbrowserdriver.*;

/*******************************
 * @Title: Visitor
 * @package com.ifreetv.proxyvisitor
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/12 10:56
 * @version
 ********************************/
public class Visitor {

    /**
     * 不使用代理获取网页内容
     * @param url 待抓取网页地址
     * @param openJS 是否支持JS渲染
     * @param isMobile 是否模拟手机
     * @return 抓取的网页内容
     */
    public static String getHtmlSource(String url, boolean openJS, boolean isMobile) {
        return getHtmlSource(url, null, openJS, isMobile);
    }
    /**
     * 获取网页内容
     * @param url 待抓取网页地址
     * @param proxyInfo 代理
     * @param openJS 是否支持JS渲染
     * @param isMobile 是否模拟手机
     * @return 抓取的网页内容
     */
    public static String getHtmlSource(String url, ProxyInfo proxyInfo, boolean openJS, boolean isMobile) {
        // 选择是否模拟手机浏览器
        UserAgent userAgent;
        if (isMobile) {
            userAgent = new UserAgent(UserAgent.Family.MOZILLA,
                    "Apple Inc.",
                    "iPhone",
                    "iPhone OS 10_3 like Mac OS X",
                    "5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36",
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
        } else {
            userAgent = UserAgent.CHROME;
        }

        // JBrowserDriver基础设置
        Settings settings;
        if (proxyInfo != null) {
            ProxyConfig proxyConfig = new ProxyConfig(null, proxyInfo.getAddress(), proxyInfo.getPort());
            settings = Settings.builder().proxy(proxyConfig).javascript(openJS).userAgent(userAgent).timezone(Timezone.ASIA_SHANGHAI).build();
        } else {
            settings = Settings.builder().javascript(openJS).userAgent(userAgent).timezone(Timezone.ASIA_SHANGHAI).build();
        }
        JBrowserDriver jBrowserDriver = new JBrowserDriver(settings);
        jBrowserDriver.get(url);
        String htmlSource = jBrowserDriver.getPageSource();
        jBrowserDriver.quit();
        return htmlSource;
    }
}
