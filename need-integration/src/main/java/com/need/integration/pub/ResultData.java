package com.need.integration.pub;

public class ResultData {
	public static final int SUCCESS=200;
	public static final int ERROR=500;
	public int code = SUCCESS;
	public String msg ="";
	public Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
