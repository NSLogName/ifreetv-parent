package com.ifreetv.datacenter.grab.parser.iptv;

import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.datacenter.constant.IPTVConstants;
import com.ifreetv.datacenter.entity.IPTVClassify;
import com.ifreetv.datacenter.grab.parser.Parser;
import com.ifreetv.proxyvisitor.Config;
import com.ifreetv.proxyvisitor.PlatformUtil;
import com.ifreetv.proxyvisitor.Visitor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/*******************************
 * @Title IPTVClassifyParser
 * @package com.ifreetv.datacenter.grab.parser.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 13:13
 * @version
 ********************************/
public class IPTVClassifyParser implements Parser<IPTVClassify>{
    @Override
    public List<IPTVClassify> parseData(String html) {
        List<IPTVClassify> iptvClassifyList = new ArrayList<IPTVClassify>();
        if (StringUtils.isNotBlank(html)) {
            iptvClassifyList = resolveHtmlDate(html);
        } else {
            LoggerUtils.getLogger().info("节目类型网页内容为空！");
        }
        return iptvClassifyList;
    }

    private List<IPTVClassify> resolveHtmlDate(String html) {
        List<IPTVClassify> iptvClassifyList = new ArrayList<IPTVClassify>();
        Document doc = Jsoup.parse(html);
        Elements elementsOfLi = doc.getElementsByTag("li");
        for (Element element : elementsOfLi) {
            Elements elementsOfA = element.getElementsByTag("a");
            if (elementsOfA != null && elementsOfA.size() > 0) {
                Element tempElement = elementsOfA.get(0);
                IPTVClassify iptvClassify = new IPTVClassify();
                iptvClassify.setClassifyName(tempElement.text());
                iptvClassify.setClassifyUrl(IPTVConstants.IPTV_URL + tempElement.attr("href"));
                iptvClassifyList.add(iptvClassify);
            }
        }
        return iptvClassifyList;
    }
}
