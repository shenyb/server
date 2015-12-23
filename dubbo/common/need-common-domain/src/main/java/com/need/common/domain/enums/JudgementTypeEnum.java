package com.need.common.domain.enums;

import com.need.utils.StringUtil;

public enum JudgementTypeEnum {

	OPS("OPS", "运营"), MORE("MORE", "更多");
	
	public String code;
	
	public String desc;
	
	private JudgementTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getCode(String judgementType){
		String result = "";
		
		if(StringUtil.isBlank(result))
			return result;
		
		switch(judgementType){
		case "OPS":
			result = OPS.code;
			break;
		case "MORE":
			result = MORE.code;
			break;
		}
		return result;
	}
}
