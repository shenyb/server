package com.need.share.common.enums;

import com.need.utils.StringUtil;

public enum GoodsStatusEnums {

	ONLINE("ONLINE","上架"),OFFLINE("OFFLINE","下架"),FREEZE("FREEZE","冻结");
	
	public String code;
	
	public String desc;
	
	private GoodsStatusEnums(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String getCode(String goodsStatus){
		String result = "";
		
		if(StringUtil.isBlank(goodsStatus))
			return result;
		
		switch(goodsStatus){
		case "ONLINE":
			result = ONLINE.code;
			break;
		case "OFFLINE":
			result = OFFLINE.code;
			break;
		case "FREEZE":
			result = FREEZE.code;
			break;
		}
		return result;
	}
	
	public static String getDesc(String goodsStatus){
		String result = "";
		
		if(StringUtil.isBlank(goodsStatus))
			return result;
		
		switch(goodsStatus){
		case "ONLINE":
			result = ONLINE.desc;
			break;
		case "OFFLINE":
			result = OFFLINE.desc;
			break;
		case "FREEZE":
			result = FREEZE.desc;
			break;
		}
		return result;
	}
	
	
}
