package com.ifreetv.datacenter.grab.parser.iptv;

import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.datacenter.constant.IPTVConstants;
import com.ifreetv.datacenter.dao.entity.Program;
import com.ifreetv.datacenter.entity.IPTVChannel;
import com.ifreetv.datacenter.entity.IPTVChannelContent;
import com.ifreetv.datacenter.entity.IPTVChannelList;
import com.ifreetv.datacenter.entity.IPTVClassify;
import com.ifreetv.datacenter.grab.parser.Parser;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/*******************************
 * @Title IPTVChannelParser
 * @package com.ifreetv.datacenter.grab.parser.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 13:45
 * @version
 ********************************/
public class IPTVChannelParser implements Parser<IPTVChannelContent>{
    @Override
    public List<IPTVChannelContent> parseData(String html) {
        List<IPTVChannelContent> iptvClassifyList = new ArrayList<IPTVChannelContent>();
        if (StringUtils.isNotBlank(html)) {
            iptvClassifyList = resolveHtmlDate(html);
        } else {
            LoggerUtils.getLogger().info("节目类型网页内容为空！");
        }
        return iptvClassifyList;
    }

    private List<IPTVChannelContent> resolveHtmlDate(String html) {
        List<IPTVChannelContent> iptvClassifyList = new ArrayList<IPTVChannelContent>();
        IPTVChannelContent iptvChannelContent = new IPTVChannelContent();

        Document doc = Jsoup.parse(html);
        Elements elementsOfOptions = doc.getElementsByTag("option");
        StringBuilder lineSb = new StringBuilder();
        for (Element elementsOfOption : elementsOfOptions) {
            lineSb.append(elementsOfOption.text())
                    .append("~")
                    .append(elementsOfOption.attr("value"))
                    .append("^");
        }

        Elements elementsOfLis = doc.getElementsByTag("li");
        StringBuilder menuSb = new StringBuilder();
        if (elementsOfLis.size() > 1) {
            List<Element> temp = elementsOfLis.subList(1, elementsOfLis.size());
            for (Element elementsOfLi : temp) {
                String menu = elementsOfLi.text();
                if (StringUtils.isNotBlank(menu)) {
                    menuSb.append(menu)
                            .append("^");
                }
            }
        }

        iptvChannelContent.setChannelLineUrl(lineSb.toString());
        iptvChannelContent.setChannelMenu(menuSb.toString());

        iptvClassifyList.add(iptvChannelContent);

        return iptvClassifyList;
    }
}
