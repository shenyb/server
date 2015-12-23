package com.need.domain.common.enums;

public enum RecordStatusEnums {
	
	VALID(1,"有效"),
	NOTVALID(0,"无效");
	
	
	public int code;
	public String message;
	
	
	private RecordStatusEnums(int code,String message){
		this.code=code;
		this.message=message;
	}
	
	public static RecordStatusEnums getByCode(int code){
		for(RecordStatusEnums auditStatus:values()){
		   if(code == auditStatus.code)		   
			   return auditStatus;		
		}	
		return null;
	}
}
