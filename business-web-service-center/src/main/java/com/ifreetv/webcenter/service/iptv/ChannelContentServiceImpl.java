package com.ifreetv.webcenter.service.iptv;

import com.ifreetv.baseutils.utils.DatetimeUtil;
import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.webcenter.constant.Constants;
import com.ifreetv.webcenter.dao.IPTVDao;
import com.ifreetv.webcenter.entity.ChannelContent;
import com.ifreetv.webcenter.entity.ChannelLine;
import com.ifreetv.webcenter.service.AbstractService;
import com.ifreetv.webcenter.service.IBaseService;
import com.ifreetv.webcenter.vo.request.AppStandardReq;
import com.ifreetv.webcenter.vo.request.iptv.ChannelContentRequestVO;
import com.ifreetv.webcenter.vo.response.AppStandardResp;
import com.ifreetv.webcenter.vo.response.iptv.ChannelContentResponseVO;
import com.ifreetv.webcenter.vo.response.iptv.ChannelResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*******************************
 * @Title ChannelContentService
 * @package com.ifreetv.webcenter.service.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 15:56
 * @version
 ********************************/
@Service
public class ChannelContentServiceImpl extends AbstractService implements IBaseService<ChannelContentRequestVO> {
    @Autowired
    IPTVDao iptvDao;

    @Override
    public AppStandardResp<ChannelContentResponseVO> execute(AppStandardReq<ChannelContentRequestVO> reqVo) {
        AppStandardResp<ChannelContentResponseVO> respObj = new AppStandardResp<ChannelContentResponseVO>();
        respObj.setCode(Constants.RESULT_CODE_FAILED);
        respObj.setResult(Constants.RESULT_FAILED);

        try {
            String channelName = reqVo.getData().getChannelName();
            respObj.setData(selectChannelContent(channelName));
            respObj.setCode(Constants.RESULT_CODE_SUCCESS);
            respObj.setResult(Constants.RESULT_SUCCESS);
        } catch (Exception e) {
            LoggerUtils.getLogger().error("获取IPTV的节目频道内容失败！ " + e.getMessage(), e);
        }
        return respObj;
    }

    private ChannelContentResponseVO selectChannelContent(String channelName) {
        ChannelContentResponseVO channelContentResponseVO = new ChannelContentResponseVO();
        String parseDate = StringUtils.split(DatetimeUtil.getNowStr(), " ")[0];
        ChannelContent channelContent = iptvDao.selcetChannelContent(parseDate, channelName);
        if (channelContent == null) {
            parseDate = StringUtils.split(DatetimeUtil.getNextDay(DatetimeUtil.getNow(), -1), " ")[0];
            channelContent = iptvDao.selcetChannelContent(parseDate, channelName);
        }

        if (channelContent != null) {
            channelContentResponseVO.setChannelContentList(resolveChannelUrl(channelContent.getChannelUrl()));
            channelContentResponseVO.setChannelMenuList(resolveChannelMenu(channelContent.getChannelMenu()));
        }

        return channelContentResponseVO;
    }

    private List<ChannelLine> resolveChannelUrl(String channelUrl) {
        List<ChannelLine> channelLineList = new ArrayList<ChannelLine>();
        List<String> url = resolveChannelMenu(channelUrl);
        for (String s : url) {
            String[] arr = StringUtils.split(s, "~");
            ChannelLine channelLine = new ChannelLine();
            channelLine.setLineName(arr[0]);
            channelLine.setLineUrl(arr[arr.length - 1]);

            channelLineList.add(channelLine);
        }

        return channelLineList;
    }

    private List<String> resolveChannelMenu(String channelMenu) {
        String[] arr = StringUtils.split(channelMenu, "^");
        return Arrays.asList(arr);
    }
}
