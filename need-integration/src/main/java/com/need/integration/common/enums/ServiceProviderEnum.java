package com.need.integration.common.enums;

import com.need.integration.util.StringUtil;

public enum ServiceProviderEnum {

	HUI_TONG("1", "百世汇通"), YUAN_TONG("2", "圆通"), BIRD("3", "笨鸟物流");
	public String code;
	public String desc;

	private ServiceProviderEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(String code) {
		String result = "";
		if (StringUtil.isBlank(code)) {
			return result;
		}
		switch (code) {
		case "1":
			result = HUI_TONG.desc;
			break;
		case "2":
			result = YUAN_TONG.desc;
			break;
		case "3":
			result = BIRD.desc;
			break;
		default:
		}
		return result;
	}
}