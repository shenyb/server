package com.need.domain.common.enums;

import org.apache.commons.lang.StringUtils;

public enum PayChannelEnum {

	ALIPAY("ALIPAY", "支付宝"), WECHAT("WECHAT", "微信");

	public String code;
	public String message;

	private PayChannelEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static PayChannelEnum getByCode(String code) {
		for (PayChannelEnum auditStatus : values()) {
			if (StringUtils.equals(code, auditStatus.code))
				return auditStatus;
		}
		return null;
	}

	public static String getMessage(String code) {
		String result = "";
		if (StringUtils.isBlank(code)) {
			return result;
		}
		switch (code) {
		case "ALIPAY":
			result = ALIPAY.message;
			break;
		case "WECHAT":
			result = WECHAT.message;
		default:
			break;
		}
		return result;
	}
}
