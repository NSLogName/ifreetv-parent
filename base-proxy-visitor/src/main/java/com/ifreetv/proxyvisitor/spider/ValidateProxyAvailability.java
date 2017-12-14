package com.ifreetv.proxyvisitor.spider;

import com.ifreetv.proxyvisitor.BrowserDriver;
import com.ifreetv.proxyvisitor.ProxyInfo;
import com.ifreetv.proxyvisitor.Visitor;

/*******************************
 * @Title: ValidateProxyAvailability
 * @package com.ifreetv.proxyvisitor.spider
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/12 10:57
 * @version
 ********************************/
public class ValidateProxyAvailability {
    /**
     * 检查代理地址是否可用
     * @param proxyInfo
     * @return
     */
    public static Boolean validateProxy(ProxyInfo proxyInfo){
        String result = Visitor.getHtmlSource("http://www.baidu.com/", false);
        return result != null;
    }
}
