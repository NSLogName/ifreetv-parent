package com.ifreetv.datacenter.grab.processor.iptv;

import com.ifreetv.baseutils.utils.DatetimeUtil;
import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.datacenter.dao.ProgramDao;
import com.ifreetv.datacenter.entity.IPTVChannel;
import com.ifreetv.datacenter.grab.framework.AbstractGrabProcessor;
import com.ifreetv.datacenter.grab.framework.IGrabProcessor;
import com.ifreetv.datacenter.grab.grader.iptv.IPTVStandardGrader;
import com.ifreetv.datacenter.grab.result.GrabFollowUpResult;
import com.ifreetv.datacenter.grab.result.GrabProcessorResult;
import com.ifreetv.datacenter.grab.result.GrabResult;
import com.ifreetv.datacenter.grab.result.GroupGrabResult;
import com.ifreetv.datacenter.task.TaskConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*******************************
 * @Title IPTVGrabProcessor
 * @package com.ifreetv.datacenter.grab.processor.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 15:16
 * @version
 ********************************/
@Component("IPTVGrabProcessor")
public class IPTVGrabProcessor extends AbstractGrabProcessor<IPTVChannel> implements IGrabProcessor<IPTVChannel> {
    {
        super.desc = "抓取IPTV内容";
    }

    @Autowired
    ProgramDao programDao;

    IPTVStandardGrader iptvStandardGrader = new IPTVStandardGrader();

    @Override
    public GrabProcessorResult<IPTVChannel> execute() {
        long startTime = System.currentTimeMillis();

        GrabProcessorResult<IPTVChannel> iptvChannelGrabProcessorResult = new GrabProcessorResult<IPTVChannel>();
        GroupGrabResult<IPTVChannel> iptvChannelGroupGrabResult = iptvStandardGrader.execute();
        List<IPTVChannel> iptvChannelList = iptvChannelGroupGrabResult.getGrabResult().getGrabList();
        try {
            if (iptvChannelList != null && iptvChannelList.size() > 0) {
                String parseDate = StringUtils.split(DatetimeUtil.getNowStr(), " ")[0];
                for (IPTVChannel iptvChannel : iptvChannelList) {
                    String classifyName = iptvChannel.getClassifyName();
                    String channelName = iptvChannel.getChannelName();

                    IPTVChannel tempIptvChannel = programDao.selectByDateAndClassifyNameAndChannelName(parseDate, classifyName, channelName);
                    if (tempIptvChannel == null) {
                        iptvChannel.setCreateTime(DatetimeUtil.getNowStr());
                        iptvChannel.setParseDate(parseDate);
                        boolean flag = programDao.insertSelectiveOfIPTVChannel(iptvChannel);
                        LoggerUtils.getLogger().info(flag ? "抓取数据入库成功！" : "抓取数据入库失败！" );
                    } else {
                        tempIptvChannel.setChannelLineUrl(iptvChannel.getChannelLineUrl());
                        tempIptvChannel.setChannelMenu(iptvChannel.getChannelMenu());
                        tempIptvChannel.setUpdateTime(DatetimeUtil.getNowStr());
                        boolean flag = programDao.updateByIPTVChannel(tempIptvChannel);
                        LoggerUtils.getLogger().info(flag ? "抓取数据更新成功！" : "抓取数据更新失败！" );
                    }
                }
            }
        } catch (Exception e) {
            LoggerUtils.getLogger().error("处理IPTV抓取结果失败！" + e.getMessage() ,e);
        }

        long endTime = System.currentTimeMillis();
        iptvChannelGrabProcessorResult.setGrabFollowUpResult(new GrabFollowUpResult((endTime - startTime), true));
        return iptvChannelGrabProcessorResult;
    }

    @Override
    public Long getExeIntervalTime() {
        return TaskConfig.getIptvIntervalTime();
    }

    @Override
    public Long getMaxExecTime() {
        return TaskConfig.getMaxIptvIntervalTime();
    }
}
