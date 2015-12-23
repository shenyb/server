package com.need.domain.common.enums;

import org.apache.commons.lang.StringUtils;

public enum AuditStatusEnums {
	
	SAVE("SAVE","保存"),
	WAIT_AUDIT("WAIT_AUDIT", "等待审核"),
	SUCCESS("SUCCESS", "成功"),
	FAIL("FAIL","失败"),;
	
	public String code;
	public String message;
	
	private AuditStatusEnums(String code,String message){
		this.code=code;
		this.message=message;
	}
	
	public static AuditStatusEnums getByCode(String code){
		for(AuditStatusEnums auditStatus:values()){
		   if(StringUtils.equals(code, auditStatus.code))		   
			   return auditStatus;		
		}	
		return null;
	}

	
}
