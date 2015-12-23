package com.need.common.domain.enums;

public enum ProvinceEnum {

	PROVINCE(0, "省直辖市"), 
	FIRST(1, "一级行政区"),
	SECOND(2, "二级行政区");
	
    public Integer code;	
	public String desc;
	private ProvinceEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	
	
	
}
