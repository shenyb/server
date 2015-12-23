package com.need.integration.common.enums;

public enum LogisticsCompanyEnum {

	BAISHIHUITONG("1", "百世汇通"),
	
	HENANYUANTONG("2", "河南圆通"),
	
	BENNIAOWULIU("3", "笨鸟物流");
	
	public String code;
	
	public String message;
	
	private LogisticsCompanyEnum(String code, String message){
		this.code = code;
		this.message = message;
	}
}
