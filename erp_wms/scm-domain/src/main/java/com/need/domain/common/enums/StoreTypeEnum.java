package com.need.domain.common.enums;

public enum StoreTypeEnum {

	ORDER("ORDER", "客户订单"),
	PURCHASE("PURCHASE", "采购订单"),
	STOCKTAKING("STOCKTAKING", "盘点库存"),
	REJECTION("REJECTION", "拒收");
	
	public String code;
	public String desc;
	
	private StoreTypeEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDesc(String code){
		String result = "";
		switch(code){
		case "ORDER":
			result = ORDER.desc;
			break;
		case "PURCHASE":
			result = PURCHASE.desc;
			break;
		case "STOCKTAKING":
			result = STOCKTAKING.desc;
			break;
		case "REJECTION":
			result = REJECTION.desc;
			break;
		default:
			result = "";
			break;
		}
		return result;
	}
	
}
