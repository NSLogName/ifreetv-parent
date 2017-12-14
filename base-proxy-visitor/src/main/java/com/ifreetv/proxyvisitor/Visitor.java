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
     * 不使用代理抓取网页内容
     * @param url 网也地址
     * @param openJS 是否开启JS
     * @return 网页内容
     */
    public static String getHtmlSource(String url, boolean openJS) {
        return BrowserDriver.getHtmlSource(url, null, openJS);
    }
}
