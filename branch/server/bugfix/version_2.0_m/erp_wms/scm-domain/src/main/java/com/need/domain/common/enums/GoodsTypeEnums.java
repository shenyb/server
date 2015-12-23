package com.need.domain.common.enums;

import org.apache.commons.lang.StringUtils;

public enum GoodsTypeEnums {
	
	SINGLE("SINGLE","单品"),
	MORE("MORE", "套装");

	
	public String code;
	public String message;
	
	private GoodsTypeEnums(String code,String message){
		this.code=code;
		this.message=message;
	}
	
	public static GoodsTypeEnums getByCode(String code){
		for(GoodsTypeEnums auditStatus:values()){
		   if(StringUtils.equals(code, auditStatus.code))		   
			   return auditStatus;		
		}	
		return null;
	}

	
}
