/**
 * InvokeParamAndResponse.java
 * @author huangwei
 * @since 2017-9-28
 *  描述：
 */
package com.ifreetv.webcenter.filter;

/*******************************
 * @Title InvokeParamAndResponse
 * @package com.ifreetv.webcenter.controller
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 10:43
 * @version
 ********************************/
public class InvokeParamAndResponse {

	private static ThreadLocal<String> threadParam = new ThreadLocal<String>();
	
	private static ThreadLocal<String> responeValue = new ThreadLocal<String>();
	
	public static void setParams(String params){
		threadParam.set(params);
	}
	
	public static String getParams(){
		return threadParam.get();
	}
	
	
	public static void setResponse(String responseValue){
		responeValue.set(responseValue);
	}
	
	public static String getResponse(){
		return responeValue.get();
	}
}
