package com.need.domain.common.enums;

import org.apache.commons.lang.StringUtils;

public enum PriceChangeAuditEnum {

	DRAFT("DRAFT", "草稿"),
	VALID("VALID", "生效"),
	FAIL("FAIL", "驳回");
	
	public String code;
	public String desc;
	
	private PriceChangeAuditEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public String getDesc(String code){
		String result = "";
		if(StringUtils.isBlank(code)){
			return result;
		}
		switch(code){
		case "DRAFT":
			result = DRAFT.desc;
			break;
		case "VALID":
			result = VALID.desc;
			break;
		case "FAIL":
			result = FAIL.desc;
			break;
		}
		
		return result;
	}
}
