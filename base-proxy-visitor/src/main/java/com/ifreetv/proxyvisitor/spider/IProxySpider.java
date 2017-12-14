package com.ifreetv.proxyvisitor.spider;

import com.ifreetv.proxyvisitor.ProxyInfo;

import java.util.List;

/*******************************
 * @Title: IProxySpider
 * @package com.ifreetv.proxyvisitor.spider
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/12 10:56
 * @version
 ********************************/
public interface IProxySpider {
    /**
     * 扫描自己的网页，完成获取代理地址信息
     * @return
     */
    public void scan(List<ProxyInfo> allValidateProxyInfoList);

    /**
     * 唤醒服务
     */
    public void alive();
}
