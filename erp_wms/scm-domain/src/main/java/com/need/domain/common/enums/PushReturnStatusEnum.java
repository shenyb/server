package com.need.domain.common.enums;

import com.need.utils.StringUtil;

public enum PushReturnStatusEnum {
  PASS("TRUE","成功"),FAIL("FALSE","失败");
	
	public String code;
	
	public String desc;
	
	private PushReturnStatusEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDesc(String pushReturnStatus){
		String result = "";
		
		if(StringUtil.isBlank(pushReturnStatus))
			return result;
		
		switch(pushReturnStatus){
		case "TRUE":
			result = PASS.desc;
			break;
		case "FALSE":
			result = FAIL.desc;
			break;
		}
		return result;
	}
}
