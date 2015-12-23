package com.need.common.domain.enums;

import org.apache.commons.lang.StringUtils;

public enum GroupRuleTypeEnums {
	
	DISCOUNT("DISCOUNT","折扣"),
	FIXEDPRICE("FIXEDPRICE", "一口价");

	
	public String code;
	public String message;
	
	private GroupRuleTypeEnums(String code,String message){
		this.code=code;
		this.message=message;
	}
	
	public static GroupRuleTypeEnums getByCode(String code){
		for(GroupRuleTypeEnums auditStatus:values()){
		   if(StringUtils.equals(code, auditStatus.code))		   
			   return auditStatus;		
		}	
		return null;
	}

	
}
