package com.need.domain.common.enums;

import com.need.utils.StringUtil;

public enum CurrencyEnums {

	CNY("CNY","人民币");

	
	public String code;
	public String message;
	
	private CurrencyEnums(String code,String message){
		this.code=code;
		this.message=message;
	}
	
	public static String getDesc(String code) {
		String result = "";
		if (StringUtil.isBlank(code)) {
			return result;
		}
		switch (code) {
		case "CNY":
			result = CNY.message;
			break;
		default:
		}
		return result;
	}
}
