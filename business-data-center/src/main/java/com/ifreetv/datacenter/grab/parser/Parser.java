package com.ifreetv.datacenter.grab.parser;

import java.util.List;

/*******************************
 * @Title: Parser
 * @package com.ifreetv.datacenter.grab.parser
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 14:41
 * @version
 ********************************/
public interface Parser<T> {
    /**
     * 解析数据
     * @param html 抓取到的页面
     * @return
     */
     List<T> parseData(String html);
}
