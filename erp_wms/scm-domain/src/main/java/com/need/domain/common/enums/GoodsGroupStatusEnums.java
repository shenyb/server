package com.need.domain.common.enums;


public enum GoodsGroupStatusEnums {

	VALID("VALID","通过"),INVALID("INVALID","待审核"),FROZEN("FROZEN","冻结");
	
	public String code;
	
	public String desc;
	
	private GoodsGroupStatusEnums(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	

	
	
}
