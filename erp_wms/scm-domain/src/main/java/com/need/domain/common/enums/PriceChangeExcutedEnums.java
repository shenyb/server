package com.need.domain.common.enums;

import org.apache.commons.lang.StringUtils;

public enum PriceChangeExcutedEnums {
	
	NEITHER("NEITHER", "没有执行"),
	STARTED("STARTED", "变价开始以执行"),
	ENDED("ENDED", "变价结束以执行"),
	FROZEN("FROZEN", "作废");
	
	public String code;
	public String desc;
    
	private PriceChangeExcutedEnums(String code, String desc) {
		/** TODO Auto-generated constructor stub */
		this.code = code;
		this.desc = desc;
	}
    
	public String getDesc(String code){
		String result = "";
		if(StringUtils.isBlank(code)){
			return result;
		}
		switch(code){
		case "NEITHER":
			result = NEITHER.desc;
			break;
		case "STARTED":
			result = STARTED.desc;
			break;
		case "ENDED":
			result = ENDED.desc;
			break;
		case "FROZEN":
			result = FROZEN.desc;
			break;
		}
		
		return result;
	}
    
    
	
	
}
