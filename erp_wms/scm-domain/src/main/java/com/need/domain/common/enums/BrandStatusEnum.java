package com.need.domain.common.enums;

public enum BrandStatusEnum {

	DISABLE("DISABLE","停用"),
	NORMAL("NORMAL","启用");
	
	public String code;
	public String desc;
	
	private BrandStatusEnum(String code ,String desc){
		this.code=code;
		this.desc=desc;
	};
	
}
