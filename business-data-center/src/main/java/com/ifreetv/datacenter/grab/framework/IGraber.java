package com.ifreetv.datacenter.grab.framework;

import com.ifreetv.datacenter.exception.HtmlParseException;
import com.ifreetv.datacenter.grab.parser.Parser;
import com.ifreetv.datacenter.grab.result.GrabResult;

import java.util.List;

/*******************************
 * @Title: IGraber
 * @package com.ifreetv.datacenter.grab.framework
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 14:38
 * @version
 ********************************/
public interface IGraber<T> {
    /**
     * 设置需要访问的地址
     * @param url 	url
     */
    void setUrlAddress(String url);

    /**
     * 需要访问的地址
     * @return 需要访问的地址
     */
    String getUrlAddress();

    /**
     * 获取来源
     * @return 获取来源
     */
    String getSource();

    /**
     * 解析html的解析器
     * @return 解析html的解析器
     */
    Parser<T> getParse() throws HtmlParseException;

    /**
     * 获取缓存，如果有缓存不需要再次抓取
     * @return 获取缓存，如果有缓存不需要再次抓取
     */
    List<T> getCacheList();

    /**
     * 抓取数据
     * @return 抓取数据
     */
    GrabResult<T> execute();

    /**
     * 描述信息
     * @return 描述信息
     */
    String getDesc();
}
