package com.need.common.domain.enums;

public enum SnsTypeEnum {

	WEIBO("WEIBO", "微博"),
	WEIXIN("WEIXIN","微信"), 
	QQ("QQ","腾讯QQ");
	public String code;
	public String desc;

	private SnsTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
}
