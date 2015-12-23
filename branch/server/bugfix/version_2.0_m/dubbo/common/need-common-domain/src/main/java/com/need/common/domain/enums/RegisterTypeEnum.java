package com.need.common.domain.enums;

public enum RegisterTypeEnum {

	WAPFAST("WAPFAST", "WAP快捷注册"),
	APPFAST("APPFAST","APP快捷注册"), 
	NORMAL("NORMAL","普通注册"), 
	OAUTH("OAUTH", "第三方授权登录");
	public String code;
	public String desc;

	private RegisterTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
}
