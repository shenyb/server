package com.need.domain.common.enums;

import com.need.utils.StringUtil;

public enum CheckStatusEnums {

	WAIT("WAIT_AUDIT","待审核"),SUCCESS("SUCCESS","通过"),FAIL("FAIL","不通过");
	
	public String code;
	
	public String desc;
	
	private CheckStatusEnums(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String getCode(String checkStatus){
		String result = "";
		
		if(StringUtil.isBlank(checkStatus))
			return result;
		
		switch(checkStatus){
		case "WAIT_AUDIT":
			result = WAIT.code;
			break;
		case "SUCCESS":
			result = SUCCESS.code;
			break;
		case "FAIL":
			result = FAIL.code;
			break;
		}
		return result;
	}
	
	
	public static String getDesc(String checkStatus){
		String result = "";
		
		if(StringUtil.isBlank(checkStatus))
			return result;
		
		switch(checkStatus){
		case "WAIT_AUDIT":
			result = WAIT.desc;
			break;
		case "SUCCESS":
			result = SUCCESS.desc;
			break;
		case "FAIL":
			result = FAIL.desc;
			break;
		}
		return result;
	}
	
	
}
