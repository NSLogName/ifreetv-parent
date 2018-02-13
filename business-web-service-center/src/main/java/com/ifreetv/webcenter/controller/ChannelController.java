package com.ifreetv.webcenter.controller;

import com.alibaba.fastjson.TypeReference;
import com.ifreetv.baseutils.utils.JSONUtil;
import com.ifreetv.webcenter.service.IBaseService;
import com.ifreetv.webcenter.service.iptv.ChannelServiceImpl;
import com.ifreetv.webcenter.vo.request.AppStandardReq;
import com.ifreetv.webcenter.vo.request.iptv.ChannelRequestVO;
import com.ifreetv.webcenter.vo.response.AppStandardResp;
import com.ifreetv.webcenter.vo.response.iptv.ChannelResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*******************************
 * @Title ChannelController
 * @package com.ifreetv.webcenter.controller
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 15:33
 * @version
 ********************************/
@Controller
@RequestMapping("/iptv")
public class ChannelController extends AbstractController<ChannelRequestVO> {
    @Autowired
    ChannelServiceImpl channelService;

    @Override
    @RequestMapping("/getChannel.do")
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 读取request请求
        String reqJson = getRequestStr(request);
        AppStandardReq<ChannelRequestVO> reqVo = requestHandle(reqJson);
        // 调用service层
        AppStandardResp<ChannelResponseVO> respObj = channelService.execute(reqVo);
        // 发送返回值
        writeJson(response, respObj, null);
    }

    @Override
    public AppStandardReq<ChannelRequestVO> requestHandle(String reqJson) {
        return JSONUtil.parseObject(reqJson, new TypeReference<AppStandardReq<ChannelRequestVO>>() {
        });
    }

    @Override
    public IBaseService<ChannelRequestVO> getService() {
        return null;
    }

}
