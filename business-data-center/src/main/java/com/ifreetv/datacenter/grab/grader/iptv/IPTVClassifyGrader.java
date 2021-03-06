package com.ifreetv.datacenter.grab.grader.iptv;

import com.ifreetv.datacenter.constant.IPTVConstants;
import com.ifreetv.datacenter.entity.IPTVClassify;
import com.ifreetv.datacenter.exception.HtmlParseException;
import com.ifreetv.datacenter.grab.framework.AbstractGraber;
import com.ifreetv.datacenter.grab.parser.Parser;
import com.ifreetv.datacenter.grab.parser.iptv.IPTVClassifyParser;
import com.ifreetv.proxyvisitor.PlatformUtil;

import java.util.List;

/*******************************
 * @Title IPTVClassifyGrader
 * @package com.ifreetv.datacenter.grab.grader.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 14:10
 * @version
 ********************************/
public class IPTVClassifyGrader extends AbstractGraber<IPTVClassify>{
    private IPTVClassifyParser iptvClassifyParser = new IPTVClassifyParser();

    @Override
    public void setUrlAddress(String url) {

    }

    @Override
    public String getUrlAddress() {
        return IPTVConstants.IPTV_PARSER_URL;
    }

    @Override
    public Parser<IPTVClassify> getParse() throws HtmlParseException {
        return iptvClassifyParser;
    }

    @Override
    public List<IPTVClassify> getCacheList() {
        return null;
    }

    @Override
    public String getDesc() {
        return "抓取IPTV类别列表！";
    }

    @Override
    public PlatformUtil chosePlatform() {
        return PlatformUtil.IPHONE;
    }
}
