package com.need.integration.common.haimeiSdk.response;

public class AbstractResponse<T> {
	String errorCode;
	String subMessage;
	String errorText;
	String regLogGuid;
	T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	 
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getSubMessage() {
		return subMessage;
	}

	public void setSubMessage(String subMessage) {
		this.subMessage = subMessage;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public String getRegLogGuid() {
		return regLogGuid;
	}

	public void setRegLogGuid(String regLogGuid) {
		this.regLogGuid = regLogGuid;
	}
}
