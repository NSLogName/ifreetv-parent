package com.ifreetv.webcenter.vo.request;

public class AppStandardReq<T> extends BaseRequestVO implements IBaseReq{
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "AppStandardReq{" +
				"data=" + data +
				'}';
	}
}
