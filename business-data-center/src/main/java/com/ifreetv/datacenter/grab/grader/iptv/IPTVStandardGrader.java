package com.ifreetv.datacenter.grab.grader.iptv;

import com.ifreetv.baseutils.utils.DatetimeUtil;
import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.datacenter.entity.IPTVChannel;
import com.ifreetv.datacenter.entity.IPTVChannelContent;
import com.ifreetv.datacenter.entity.IPTVClassify;
import com.ifreetv.datacenter.grab.framework.AbstractStandardGraber;
import com.ifreetv.datacenter.grab.framework.IStandardGraber;
import com.ifreetv.datacenter.grab.result.GrabResult;
import com.ifreetv.datacenter.grab.result.GroupGrabResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*******************************
 * @Title IPTVStandardGrader
 * @package com.ifreetv.datacenter.grab.grader.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 14:36
 * @version
 ********************************/
@Component("IPTVStandardGrader")
public class IPTVStandardGrader extends AbstractStandardGraber<IPTVChannel> implements IStandardGraber<IPTVChannel> {
    private IPTVClassifyGrader iptvClassifyGrader = new IPTVClassifyGrader();

    private IPTVChannelListGrader iptvChannelListGrader = new IPTVChannelListGrader();

    private IPTVChannelGrader iptvChannelGrader = new IPTVChannelGrader();

    @Override
    public GroupGrabResult<IPTVChannel> execute() {
        GroupGrabResult<IPTVChannel> iptvChannelGroupGrabResult = new GroupGrabResult<IPTVChannel>();
        List<IPTVChannel> iptvChannelList = new ArrayList<IPTVChannel>();
        GrabResult<IPTVChannel> allIptvChannelGrabResult = new GrabResult<IPTVChannel>();

        LoggerUtils.getLogger().info("***************************** 开始抓取节目类别*****************************");
        GrabResult<IPTVClassify> classifyGrabResult = iptvClassifyGrader.execute();
        List<IPTVClassify> iptvClassifyList = classifyGrabResult.getGrabList();
        for (IPTVClassify iptvClassify : iptvClassifyList) {
            iptvChannelListGrader.setUrlAddress(iptvClassify.getClassifyUrl());
            LoggerUtils.getLogger().info("############################### 开始抓取节目列表(" +  iptvClassify.getClassifyName() + ") ###############################");
            GrabResult<IPTVClassify> channelListGrabResult = iptvChannelListGrader.execute();
            List<IPTVClassify> iptvChannelContentList = channelListGrabResult.getGrabList();
            for (IPTVClassify classify : iptvChannelContentList) {
                iptvChannelGrader.setUrlAddress(classify.getClassifyUrl());
                LoggerUtils.getLogger().info("================================ 开始抓取节目内容(" +  classify.getClassifyName() + ") ================================");
                GrabResult<IPTVChannelContent> iptvChannelGrabResult = iptvChannelGrader.execute();
                IPTVChannelContent iptvChannelContent = iptvChannelGrabResult.getGrabList().get(0);

                String channelLineUrl = iptvChannelContent.getChannelLineUrl();
                if (StringUtils.isNotBlank(channelLineUrl)) {
                    IPTVChannel iptvChannel = new IPTVChannel();
                    iptvChannel.setClassifyName(iptvClassify.getClassifyName());
                    iptvChannel.setChannelName(classify.getClassifyName());
                    iptvChannel.setChannelLineUrl(channelLineUrl);
                    iptvChannel.setChannelMenu(iptvChannelContent.getChannelMenu());
                    iptvChannelList.add(iptvChannel);
                }
            }
        }
        allIptvChannelGrabResult.setGrabList(iptvChannelList);
        iptvChannelGroupGrabResult.setResult(true);
        iptvChannelGroupGrabResult.setGrabResult(allIptvChannelGrabResult);

        return iptvChannelGroupGrabResult;
    }
}
