package com.ifreetv.webcenter.vo.request;

/**
 * 
* <p>Title: BaseRequestVO</p>
* <p>Description: 接口公共请求参数</p>
* @author sunxb
* @date 2016-8-31 下午2:14:01
*
 */
public class BaseRequestVO implements IBaseReq{
	/**
	 * 登录标识
	 */
	private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public String toString() {
        return "BaseRequestVO{" +
                "deviceToken='" + deviceToken + '\'' +
                '}';
    }
}
