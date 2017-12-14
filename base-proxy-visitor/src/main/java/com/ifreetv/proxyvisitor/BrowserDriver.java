package com.ifreetv.proxyvisitor;

import com.machinepublishers.jbrowserdriver.*;

/*******************************
 * @Title: BrowserDriver
 * @package com.ifreetv.proxyvisitor
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/14 9:50
 * @version
 ********************************/
public class BrowserDriver {
    /**
     * 获取网页内容
     * @param url 网页地址
     * @param proxyInfo 代理
     * @return 网页内容
     */
    public static String getHtmlSource(String url, ProxyInfo proxyInfo, boolean openJS) {
        // JBrowserDriver基础设置
        Settings settings;
        if (proxyInfo != null) {
            ProxyConfig proxyConfig = new ProxyConfig(null, proxyInfo.getAddress(), proxyInfo.getPort());
            settings = Settings.builder().proxy(proxyConfig).javascript(openJS).userAgent(UserAgent.CHROME).timezone(Timezone.ASIA_SHANGHAI).build();
        } else {
            settings = Settings.builder().javascript(openJS).userAgent(UserAgent.CHROME).timezone(Timezone.ASIA_SHANGHAI).build();
        }
        JBrowserDriver jBrowserDriver = new JBrowserDriver(settings);
        jBrowserDriver.get(url);
        String htmlSource = jBrowserDriver.getPageSource();
        jBrowserDriver.quit();
        return htmlSource;
    }
}
