package com.need.domain.common.enums;

public enum DistributionStatusEnums {

	FREEZE("FREEZE","冻结"),
	WAIT_TO_AUDIT("WAIT_TO_AUDIT", "待审核"),
	ALLOW_USE("ALLOW_USE", "生效"),
	REJECT("REJECT","驳回");
	
	public String code;
	public String message;
	
	private DistributionStatusEnums(String code,String message){
		this.code=code;
		this.message=message;
	}
}
