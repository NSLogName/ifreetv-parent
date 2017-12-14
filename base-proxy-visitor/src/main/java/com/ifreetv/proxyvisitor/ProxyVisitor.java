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
    public static String getHtmlSource(String url) {
        XICIProxySpider xICIProxySpider = XICIProxySpider.getInstance();
        if (!xICIProxySpider.isAlive()) {
            xICIProxySpider.start();
        }
        List<ProxyInfo> list = ProxyInfoManager.getAllValidateProxyInfoList();
        while (list.size() < 1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LoggerUtils.getLogger().error("用代理抓取网页内容出现错误：" + e.getMessage(), e);
            }
        }
        return Visitor.getHtmlSource(url, list.get(0), true);
    }

//    public static void main(String[] args) {
//        System.out.println("---------");
//        String url = "https://www.vipleague.mobi/";
//        String str = getHtmlSource(url);
//        System.out.println("str=" + str);
//    }
}
