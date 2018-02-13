package com.ifreetv.webcenter.vo.response;

public class AppStandardResp<T> extends BaseResponseVO {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "AppStandardResp{" +
				"data=" + data +
				'}';
	}
}
