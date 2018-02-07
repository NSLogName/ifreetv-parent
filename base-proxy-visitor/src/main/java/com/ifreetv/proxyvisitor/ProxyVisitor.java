package com.ifreetv.proxyvisitor;

import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.proxyvisitor.spider.XICIProxySpider;

import java.util.List;
import java.util.Random;

/*******************************
 * @Title: ProxyVisitor
 * @package com.ifreetv.proxyvisitor
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/12 10:56
 * @version
 ********************************/
public class ProxyVisitor {
    /**
     * 代理列表
     */
    private static List<ProxyInfo> list;

    /**
     * 使用代理抓取网页
     *
     * @param url 待抓取网页
     * @return 网页抓取内容
     */
    public static String getHtmlSource(String url) {
        return getHtmlSource(url, null);
    }

    /**
     * 使用代理抓取网页
     *
     * @param url      待抓取网页
     * @param platform 模拟平台
     * @return 网页抓取内容
     */
    public static String getHtmlSource(String url, PlatformUtil platform) {
        getProxyInfoList();
        Random random = new Random();
        int i = random.nextInt(list.size());
        return Visitor.getHtmlSource(url, list.get(i), platform);
    }

    /**
     * 获取代理
     */
    private static void getProxyInfoList() {
        XICIProxySpider xICIProxySpider = XICIProxySpider.getInstance();
        if (!xICIProxySpider.isAlive()) {
            xICIProxySpider.start();
        }
        list = ProxyInfoManager.getAllValidateProxyInfoList();
        while (list.size() < 1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LoggerUtils.getLogger().error("用代理抓取网页内容出现错误：" + e.getMessage(), e);
            }
        }
    }
}
