package com.need.domain.common.enums;

import com.need.utils.StringUtil;

/**
 * 仓库类型
 * @author liuhongyang
 *
 */
public enum WarehouseTypeEnum {
	TAX_WAREHOUSE("TAX_WAREHOUSE","保税仓"),SELF_WAREHOUSE("SELF_WAREHOUSE","自营仓"),OVERSEA_WAREHOUSE("OVERSEA_WAREHOUSE","海外直邮");
	
	public String code;
	
	public String desc;

	private WarehouseTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getCode(String cangKuType){
		String result = "";
		
		if(StringUtil.isBlank(cangKuType))
			return result;
		
		switch(cangKuType){
		case "TAX_WAREHOUSE":
			result = TAX_WAREHOUSE.code;
			break;
		case "SELF_WAREHOUSE":
			result = SELF_WAREHOUSE.code;
			break;
		case "OVERSEA_WAREHOUSE":
			result = OVERSEA_WAREHOUSE.code;
			break;
		}
		return result;
	}
	
	public static String getDesc(String cangKuType){
		String result = "";
		
		if(StringUtil.isBlank(cangKuType))
			return result;
		
		switch(cangKuType){
		case "TAX_WAREHOUSE":
			result = TAX_WAREHOUSE.desc;
			break;
		case "SELF_WAREHOUSE":
			result = SELF_WAREHOUSE.desc;
			break;
		case "OVERSEA_WAREHOUSE":
			result = OVERSEA_WAREHOUSE.desc;
			break;
		}
		return result;
	}
	
	
}
