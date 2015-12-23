package com.need.domain.common.enums;

import org.apache.commons.lang.StringUtils;

public enum PriceChangeTypeEnum {

	EXCUTE("EXCUTE", "立即执行"),
	SCHEDULE("SCHEDULE", "定时执行");
	
	public String code;
	public String desc;
	
	private PriceChangeTypeEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public String getDesc(String code){
		String result = "";
		if(StringUtils.isBlank(code)){
			return result;
		}
		switch(code){
		case "EXCUTE":
			result = EXCUTE.desc;
			break;
		case "SCHEDULE":
			result = SCHEDULE.desc;
			break;
		}
		
		return result;
	}
}
