package com.ifreetv.webcenter.service;

import com.ifreetv.webcenter.vo.request.AppStandardReq;

/*******************************
 * @Title IBaseReq
 * @package com.ifreetv.webcenter.service
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 9:59
 * @version
 ********************************/
public interface IBaseService<T> {
    /**
     * 接口服务公共处理方法
     *
     * @param reqVo 请求对象
     * @return 响应对象
     */
    Object execute(AppStandardReq<T> reqVo);
}
