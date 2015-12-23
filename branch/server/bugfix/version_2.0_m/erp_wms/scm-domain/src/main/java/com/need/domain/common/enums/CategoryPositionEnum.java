package com.need.domain.common.enums;

public enum CategoryPositionEnum {
	SHIJIAN("SHIJIAN","世间"), XINHUAN("XINHUAN","新欢");
    public String code;
	
	public String desc;
	
	private CategoryPositionEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
}
