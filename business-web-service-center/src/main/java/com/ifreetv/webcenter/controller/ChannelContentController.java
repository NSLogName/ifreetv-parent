package com.ifreetv.webcenter.controller;

import com.alibaba.fastjson.TypeReference;
import com.ifreetv.baseutils.utils.JSONUtil;
import com.ifreetv.webcenter.service.IBaseService;
import com.ifreetv.webcenter.service.iptv.ChannelContentServiceImpl;
import com.ifreetv.webcenter.service.iptv.ChannelServiceImpl;
import com.ifreetv.webcenter.vo.request.AppStandardReq;
import com.ifreetv.webcenter.vo.request.iptv.ChannelContentRequestVO;
import com.ifreetv.webcenter.vo.request.iptv.ChannelRequestVO;
import com.ifreetv.webcenter.vo.response.AppStandardResp;
import com.ifreetv.webcenter.vo.response.iptv.ChannelContentResponseVO;
import com.ifreetv.webcenter.vo.response.iptv.ChannelResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*******************************
 * @Title ChannelContentController
 * @package com.ifreetv.webcenter.controller
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 15:59
 * @version
 ********************************/
@Controller
@RequestMapping("/iptv")
public class ChannelContentController extends AbstractController<ChannelContentRequestVO> {
    @Autowired
    ChannelContentServiceImpl channelContentService;

    @Override
    @RequestMapping("/getChannelContent.do")
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 读取request请求
        String reqJson = getRequestStr(request);
        AppStandardReq<ChannelContentRequestVO> reqVo = requestHandle(reqJson);
        // 调用service层
        AppStandardResp<ChannelContentResponseVO> respObj = channelContentService.execute(reqVo);
        // 发送返回值
        writeJson(response, respObj, null);
    }
    @Override
    public AppStandardReq<ChannelContentRequestVO> requestHandle(String reqJson) {
        return JSONUtil.parseObject(reqJson, new TypeReference<AppStandardReq<ChannelContentRequestVO>>() {
        });
    }

    @Override
    public IBaseService<ChannelContentRequestVO> getService() {
        return null;
    }
}
