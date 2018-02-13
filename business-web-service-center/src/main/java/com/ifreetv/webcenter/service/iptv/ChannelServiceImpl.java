package com.ifreetv.webcenter.service.iptv;

import com.ifreetv.baseutils.utils.DatetimeUtil;
import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.webcenter.constant.Constants;
import com.ifreetv.webcenter.dao.IPTVDao;
import com.ifreetv.webcenter.service.AbstractService;
import com.ifreetv.webcenter.service.IBaseService;
import com.ifreetv.webcenter.vo.request.AppStandardReq;
import com.ifreetv.webcenter.vo.request.iptv.ChannelRequestVO;
import com.ifreetv.webcenter.vo.response.AppStandardResp;
import com.ifreetv.webcenter.vo.response.iptv.ChannelResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*******************************
 * @Title ChannelServiceImpl
 * @package com.ifreetv.webcenter.service.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 15:24
 * @version
 ********************************/
@Service
public class ChannelServiceImpl extends AbstractService implements IBaseService<ChannelRequestVO> {
    @Autowired
    IPTVDao iptvDao;

    @Override
    public AppStandardResp<ChannelResponseVO> execute(AppStandardReq<ChannelRequestVO> reqVo) {
        AppStandardResp<ChannelResponseVO> respObj = new AppStandardResp<ChannelResponseVO>();
        respObj.setCode(Constants.RESULT_CODE_FAILED);
        respObj.setResult(Constants.RESULT_FAILED);

        try {
            String classifyName = reqVo.getData().getClassifyName();
            respObj.setData(selectChannel(classifyName));
            respObj.setCode(Constants.RESULT_CODE_SUCCESS);
            respObj.setResult(Constants.RESULT_SUCCESS);
        } catch (Exception e) {
            LoggerUtils.getLogger().error("获取IPTV的节目频道列表失败！ " + e.getMessage(), e);
        }
        return respObj;
    }

    /**
     * 获取频道列表
     *
     * @param classifyName 列别名
     * @return 频道列表
     */
    private ChannelResponseVO selectChannel(String classifyName) {
        ChannelResponseVO classifyResponseVO = new ChannelResponseVO();
        String parseDate = StringUtils.split(DatetimeUtil.getNowStr(), " ")[0];
        List<String> channelList = iptvDao.selectChannelList(parseDate, classifyName);
        if (channelList == null || channelList.size() < 1) {
            parseDate = StringUtils.split(DatetimeUtil.getNextDay(DatetimeUtil.getNow(), -1), " ")[0];
            channelList = iptvDao.selectChannelList(parseDate, classifyName);
        }
        classifyResponseVO.setChannelList(channelList);
        return classifyResponseVO;
    }
}
