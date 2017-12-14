package com.ifreetv.proxyvisitor.spider;

import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.proxyvisitor.Config;
import com.ifreetv.proxyvisitor.ProxyInfo;
import com.ifreetv.proxyvisitor.ProxyInfoManager;
import com.ifreetv.proxyvisitor.Visitor;
import com.machinepublishers.jbrowserdriver.ProxyConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/*******************************
 * @Title: XICIProxySpider
 * @package com.ifreetv.proxyvisitor.spider
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/12 10:57
 * @version
 ********************************/
public class XICIProxySpider extends Thread implements IProxySpider {
    private boolean flag = true;

    private static XICIProxySpider instance = new XICIProxySpider();

    private XICIProxySpider() { }

    public static XICIProxySpider getInstance() {
        return instance;
    }

    @Override
    public void run() {
        while(flag) {
            try {
                IProxySpider spider = XICIProxySpider.getInstance();
                ProxyInfoManager.clear();
                spider.scan(ProxyInfoManager.getAllValidateProxyInfoList());
                for(ProxyInfo proxyInfo:ProxyInfoManager.getAllValidateProxyInfoList()){
                    LoggerUtils.getLogger().info("有效地址:" + proxyInfo.getAddress() +  ":"  + proxyInfo.getPort());
                }
                Thread.sleep(Config.PROXY_SPIDER_SLEEP_TIME);
            } catch (Exception e) {
                LoggerUtils.getLogger().error("执行抓取代理ip地址出错：" + e.getMessage(), e);
            }
        }
    }

    @Override
    public void scan(List<ProxyInfo> allValidateProxyInfoList) {
        try{
            LoggerUtils.getLogger().info("正在抓取：" + Config.URL_ADDRESS + "代理IP");
            String htmlResult = Visitor.getHtmlSource(Config.URL_ADDRESS, null, true);
            if(htmlResult != null){
                Document doc = Jsoup.parse(htmlResult);
                Elements els = doc.getElementsByClass("odd");
                for (Element e : els) {
                    // 只取双优的代理
                    int fastNum = e.getElementsByClass("fast").size();
                    if (fastNum < 2) {
                        continue;
                    }
                    String address = e.getElementsByTag("td").get(1).text().trim();
                    Integer port = Integer.parseInt(e.getElementsByTag("td").get(2).text().trim());
                    ProxyInfo proxyInfo = new ProxyInfo(address, port);
                    if(ValidateProxyAvailability.validateProxy(proxyInfo)){
                        allValidateProxyInfoList.add(new ProxyInfo(address, port));
                        LoggerUtils.getLogger().info("可用代理：" + address + "：" + port);
                    }else{
                        LoggerUtils.getLogger().info(address + "：" + port + "不可用");
                    }
                }
            }
            ProxyInfoManager.bak();
        }catch(Exception e){
            LoggerUtils.getLogger().error("执行抓取代理ip地址出错：" + e.getMessage());
        }
    }

    @Override
    public void alive() {
        if(!instance.isAlive()) {
            instance.start();
        }
    }
}
