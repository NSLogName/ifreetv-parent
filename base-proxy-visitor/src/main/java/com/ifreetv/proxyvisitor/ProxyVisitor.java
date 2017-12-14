package com.ifreetv.proxyvisitor;

import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.proxyvisitor.spider.XICIProxySpider;

import java.util.List;

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
     * @param url 待抓取网页
     * @param isMobile 是否模拟手机
     * @return 网页抓取内容
     */
    public static String getHtmlSource(String url, boolean isMobile) {
        getProxyInfoList();
        return Visitor.getHtmlSource(url, list.get(0),true, isMobile);
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

    public static void main(String[] args) {
        System.out.println("---------");
        String url = "http://iptv.cdzmn.com/tv.php";
        String str = getHtmlSource(url, true);
        System.out.println("str=" + str);
    }
}
