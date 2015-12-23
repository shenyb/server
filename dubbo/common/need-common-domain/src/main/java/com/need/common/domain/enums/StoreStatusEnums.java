package com.need.common.domain.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 库存状态
 * @author zhangmengbin
 *
 */
public enum StoreStatusEnums {
	IN_STORE("IN_STORE", "现货"),OUT_STORE("OUT_STORE","缺货");
	public String code;
	public String desc;

	private StoreStatusEnums(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDesc(String code){
		String result = "";
		if(StringUtils.isBlank(code))
			return result;
		switch(code){
		case "IN_STORE":
			result = IN_STORE.desc;
			break;
		case "OUT_STORE":
			result = OUT_STORE.desc;
			break;
		default:
			break;
		}
		
		return result;
	}
}
