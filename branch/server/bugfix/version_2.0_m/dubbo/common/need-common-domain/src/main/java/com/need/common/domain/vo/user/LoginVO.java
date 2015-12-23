package com.need.common.domain.vo.user;

import java.io.Serializable;

public class LoginVO implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5051411843333536312L;
	private String mobile;
	private String validateCode;
	private String channel;
	private String type;
	private String userAreaId;
	private String openId;
	
	

	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUserAreaId() {
		return userAreaId;
	}
	public void setUserAreaId(String userAreaId) {
		this.userAreaId = userAreaId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	@Override
	public String toString() {
		return "LoginVO [mobile=" + mobile + ", validateCode=" + validateCode + "]";
	}
	
}
