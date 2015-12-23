package com.need.domain.common.enums;

import com.need.utils.StringUtil;

public enum RoleStatusEnums {

	NORMAL("NORMAL", "正常"), FREEZE("FREEZE", "冻结");
	
	public String code;
	
	public String desc;
	
	private RoleStatusEnums(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String getCode(String roleType){
		String result = "";
		
		if(StringUtil.isBlank(result))
			return result;
		
		switch(roleType){
		case "OPS":
			result = NORMAL.code;
			break;
		case "MORE":
			result = FREEZE.code;
			break;
		}
		return result;
	}
	
	
}
