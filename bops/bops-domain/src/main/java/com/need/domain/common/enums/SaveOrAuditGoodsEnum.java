package com.need.domain.common.enums;

public enum SaveOrAuditGoodsEnum {

	SAVE_GOODS("SAVE_GOODS", "新增商品"),
	AUDIT_GOODS("AUDIT_GOODS", "审核商品商品");
	
	public String code;
	public String desc;
	
	private SaveOrAuditGoodsEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
}
