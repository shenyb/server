package com.need.domain.common.enums;

import org.apache.commons.lang.StringUtils;

public enum PurchaseEnums {
	
	WAIT_HARVEST("WAIT_HARVEST","待收货"),
	HAVEST_ALREADY("HAVEST_ALREADY", "已收货"),
	HAVEST_HALF("HAVEST_HALF", "部分收货");
	
	public String code;
	public String message;
	
	private PurchaseEnums(String code,String message){
		this.code=code;
		this.message=message;
	}
	
	public static PurchaseEnums getByCode(String code){
		for(PurchaseEnums purchaseStatus:values()){
		   if(StringUtils.equals(code, purchaseStatus.code))		   
			   return purchaseStatus;		
		}	
		return null;
	}

	
}
