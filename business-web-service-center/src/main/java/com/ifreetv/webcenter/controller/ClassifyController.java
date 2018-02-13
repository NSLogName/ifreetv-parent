package com.ifreetv.webcenter.controller;

import com.alibaba.fastjson.TypeReference;
import com.ifreetv.baseutils.utils.JSONUtil;
import com.ifreetv.webcenter.service.IBaseService;
import com.ifreetv.webcenter.service.iptv.ClassifyServiceImpl;
import com.ifreetv.webcenter.vo.request.AppStandardReq;
import com.ifreetv.webcenter.vo.request.BaseRequestVO;
import com.ifreetv.webcenter.vo.response.AppStandardResp;
import com.ifreetv.webcenter.vo.response.iptv.ClassifyResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*******************************
 * @Title ClassifyController
 * @package com.ifreetv.webcenter.controller
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 10:41
 * @version
 ********************************/
@Controller
@RequestMapping("/iptv")
public class ClassifyController extends AbstractController<BaseRequestVO> {
    @Autowired
    ClassifyServiceImpl classifyService;

    @Override
    @RequestMapping("/getClassify.do")
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 读取request请求
        String reqJson = getRequestStr(request);
        AppStandardReq<BaseRequestVO> reqVo = requestHandle(reqJson);
        // 调用service层
        AppStandardResp<ClassifyResponseVO> respObj = classifyService.execute(reqVo);
        // 发送返回值
        writeJson(response, respObj, null);
    }

    @Override
    public AppStandardReq<BaseRequestVO> requestHandle(String reqJson) {
        return JSONUtil.parseObject(reqJson, new TypeReference<AppStandardReq<BaseRequestVO>>() {
        });
    }

    @Override
    public IBaseService<BaseRequestVO> getService() {
        return null;
    }
}
