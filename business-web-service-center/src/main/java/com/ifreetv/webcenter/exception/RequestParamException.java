package com.ifreetv.webcenter.exception;

/*******************************
 * @Title RequestParamException
 * @package com.ifreetv.webcenter.controller
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 10:43
 * @version
 ********************************/
public class RequestParamException extends Exception {
	private static final long serialVersionUID = -6620621034969974512L;

	public RequestParamException() {
		super();
	}

	public RequestParamException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RequestParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestParamException(String message) {
		super(message);
	}

	public RequestParamException(Throwable cause) {
		super(cause);
	}
}
