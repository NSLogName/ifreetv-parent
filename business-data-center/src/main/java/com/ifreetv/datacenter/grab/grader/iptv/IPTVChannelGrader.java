package com.ifreetv.datacenter.grab.grader.iptv;

import com.ifreetv.datacenter.entity.IPTVChannel;
import com.ifreetv.datacenter.entity.IPTVChannelContent;
import com.ifreetv.datacenter.exception.HtmlParseException;
import com.ifreetv.datacenter.grab.framework.AbstractGraber;
import com.ifreetv.datacenter.grab.parser.Parser;
import com.ifreetv.datacenter.grab.parser.iptv.IPTVChannelParser;
import com.ifreetv.proxyvisitor.PlatformUtil;

import java.util.List;

/*******************************
 * @Title IPTVChannelGrader
 * @package com.ifreetv.datacenter.grab.grader.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 14:24
 * @version
 ********************************/
public class IPTVChannelGrader extends AbstractGraber<IPTVChannelContent>{
    private String url;

    private IPTVChannelParser iptvChannelParser = new IPTVChannelParser();

    @Override
    public void setUrlAddress(String url) {
        this.url = url;
    }

    @Override
    public String getUrlAddress() {
        return url;
    }

    @Override
    public Parser<IPTVChannelContent> getParse() throws HtmlParseException {
        return iptvChannelParser;
    }

    @Override
    public List<IPTVChannelContent> getCacheList() {
        return null;
    }

    @Override
    public String getDesc() {
        return "抓取IPTV节目内容！";
    }

    @Override
    public PlatformUtil chosePlatform() {
        return PlatformUtil.IPHONE;
    }
}
