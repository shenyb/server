package com.need.common.domain.enums;

public enum RegisterChannelEnum {
	WAP("WAP", "WAP快捷注册"),
	ANDROID("ANDROID","安卓注册"), 
	IOS("IOS","苹果注册");
	public String code;
	public String desc;

	private RegisterChannelEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
