package com.need.domain.common.enums;

public enum ReportTypeEnum {

	PURCHASE("PURCHASE","采购单"),
	REJECTION("REJECTION","拒收"),
	STOCKTAKING("STOCKTAKING","盘点");
	
	public String code;
	public String desc;
	
	private ReportTypeEnum(String code ,String desc){
		this.code=code;
		this.desc=desc;
	};
	
}
