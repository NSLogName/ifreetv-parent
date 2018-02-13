package com.ifreetv.webcenter.service.iptv;

import com.ifreetv.baseutils.utils.DatetimeUtil;
import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.webcenter.constant.Constants;
import com.ifreetv.webcenter.dao.IPTVDao;
import com.ifreetv.webcenter.service.AbstractService;
import com.ifreetv.webcenter.service.IBaseService;
import com.ifreetv.webcenter.vo.request.AppStandardReq;
import com.ifreetv.webcenter.vo.request.BaseRequestVO;
import com.ifreetv.webcenter.vo.response.AppStandardResp;
import com.ifreetv.webcenter.vo.response.iptv.ClassifyResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*******************************
 * @Title ClassifyServiceImpl
 * @package com.ifreetv.webcenter.service.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 10:04
 * @version
 ********************************/
@Service
public class ClassifyServiceImpl extends AbstractService implements IBaseService<BaseRequestVO> {
    @Autowired
    IPTVDao iptvDao;

    @Override
    public AppStandardResp<ClassifyResponseVO> execute(AppStandardReq<BaseRequestVO> reqVo) {
        AppStandardResp<ClassifyResponseVO> respObj = new AppStandardResp<ClassifyResponseVO>();
        respObj.setCode(Constants.RESULT_CODE_FAILED);
        respObj.setResult(Constants.RESULT_FAILED);

        try {
            respObj.setData(selectClassify());
            respObj.setCode(Constants.RESULT_CODE_SUCCESS);
            respObj.setResult(Constants.RESULT_SUCCESS);
        } catch (Exception e) {
            LoggerUtils.getLogger().error("获取IPTV的节目分类列表失败！ " + e.getMessage(), e);
        }
        return respObj;
    }

    /**
     * 获取节目类别列表
     *
     * @return 节目类别列表
     */
    private ClassifyResponseVO selectClassify() {
        ClassifyResponseVO classifyResponseVO = new ClassifyResponseVO();
        String parseDate = StringUtils.split(DatetimeUtil.getNowStr(), " ")[0];
        List<String> classifyList = iptvDao.selectClassifyList(parseDate);
        if (classifyList == null || classifyList.size() < 1) {
            parseDate = StringUtils.split(DatetimeUtil.getNextDay(DatetimeUtil.getNow(), -1), " ")[0];
            classifyList = iptvDao.selectClassifyList(parseDate);
        }
        classifyResponseVO.setClassifyList(classifyList);
        return classifyResponseVO;
    }
}
