package com.ifreetv.proxyvisitor.spider;

import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.proxyvisitor.Config;
import com.ifreetv.proxyvisitor.ProxyInfo;
import com.ifreetv.proxyvisitor.ProxyInfoManager;
import com.ifreetv.proxyvisitor.Visitor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/*******************************
 * @Title KUAIProxySpider
 * @package com.ifreetv.proxyvisitor.spider
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/11 11:30
 * @version
 ********************************/
public class KUAIProxySpider extends Thread implements IProxySpider {
    private static KUAIProxySpider instance = new KUAIProxySpider();

    private KUAIProxySpider() {
    }

    public static KUAIProxySpider getInstance() {
        return instance;
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            try {
                IProxySpider spider = KUAIProxySpider.getInstance();
                ProxyInfoManager.clear();
                spider.scan(ProxyInfoManager.getAllValidateProxyInfoList());
                for (ProxyInfo proxyInfo : ProxyInfoManager.getAllValidateProxyInfoList()) {
                    LoggerUtils.getLogger().info("有效地址:" + proxyInfo.getAddress() + ":" + proxyInfo.getPort());
                }
                Thread.sleep(Config.PROXY_SPIDER_SLEEP_TIME);
            } catch (Exception e) {
                LoggerUtils.getLogger().error("执行抓取代理ip地址出错：" + e.getMessage(), e);
            }
        }
    }

    @Override
    public void scan(List<ProxyInfo> allValidateProxyInfoList) {
        try {
            LoggerUtils.getLogger().info("正在抓取：" + Config.URL_KUAI_ADDRESS + "代理IP");
            String htmlResult = Visitor.getHtmlSource(Config.URL_KUAI_ADDRESS, null, null);
            if (htmlResult != null) {
                Document doc = Jsoup.parse(htmlResult);
                Elements els = doc.getElementsByTag("tr");
                List<Element> els1 = els.subList(7, els.size());
                for (Element e : els1) {
                    String speed = e.getElementsByTag("td").get(6).text().trim();
                    String[] arr = StringUtils.split(speed, "秒");
                    if (arr.length > 0) {
                        float flag = Float.valueOf(arr[0]);
                        if (flag < 3.0) {
                            String address = e.getElementsByTag("td").get(0).text().trim();
                            Integer port = Integer.parseInt(e.getElementsByTag("td").get(1).text().trim());
                            ProxyInfo proxyInfo = new ProxyInfo(address, port);
                            if (ValidateProxyAvailability.validateProxy(proxyInfo)) {
                                allValidateProxyInfoList.add(new ProxyInfo(address, port));
                                LoggerUtils.getLogger().info("可用代理：" + address + "：" + port);
                            } else {
                                LoggerUtils.getLogger().info(address + "：" + port + "不可用");
                            }
                        }
                    }
                }
            }
            ProxyInfoManager.bak();
        } catch (Exception e) {
            LoggerUtils.getLogger().error("执行抓取代理ip地址出错：" + e.getMessage());
        }
    }

    @Override
    public void alive() {
        if (!instance.isAlive()) {
            instance.start();
        }
    }
}
