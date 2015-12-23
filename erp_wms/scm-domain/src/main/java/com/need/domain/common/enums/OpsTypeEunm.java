package com.need.domain.common.enums;

import com.need.utils.StringUtil;
/**
 * 运营方式
 * @author liuhongyang
 *
 */
public enum OpsTypeEunm {
	DISANFANGYUNYING("DISANFANGYUNYING","第三方代运营"),ZIYING("ZIYING","自营");
	
	public String code;
	
	public String desc;

	private OpsTypeEunm(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getCode(String opsType){
		String result = "";
		
		if(StringUtil.isBlank(opsType))
			return result;
		
		switch(opsType){
		case "DISANFANGYUNYING":
			result = DISANFANGYUNYING.code;
			break;
		case "ZIYING":
			result = ZIYING.code;
			break;
		}
		return result;
	}
	
	public static String getDesc(String opsType){
		String result = "";
		
		if(StringUtil.isBlank(opsType))
			return result;
		
		switch(opsType){
		case "DISANFANGYUNYING":
			result = DISANFANGYUNYING.desc;
			break;
		case "ZIYING":
			result = ZIYING.desc;
			break;
		}
		return result;
	}
}
