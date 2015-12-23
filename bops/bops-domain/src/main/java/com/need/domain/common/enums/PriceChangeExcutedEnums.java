package com.need.domain.common.enums;

public enum PriceChangeExcutedEnums {
	
	NEITHER("NEITHER","NEITHER"),STARTED("STARTED","STARTED"),ENDED("ENDED","ENDED");
	
	public String code;
	public String desc;
    
	private PriceChangeExcutedEnums(String code, String desc) {
		/** TODO Auto-generated constructor stub */
		this.code = code;
		this.desc = desc;
	}
    
    
    
	
	
}
