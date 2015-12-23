package com.need.common.domain.enums;

import com.need.utils.StringUtil;

/**
 * 
 * <p></p>
 * @author LXD 2015年8月12日 下午8:03:28
 * @ClassName NeedStatusEnum
 * @Description TODO 用户need商品的状态： 1：need过；0：没有need过
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月12日
 * @modify by reason:{方法名}:{原因}
 */
public enum NeedStatusEnum {
	NEED("TRUE", "该用户need过该商品"), CANCEL("FALSE", "该用户没有need过该商品");
	public String code;
	public String desc;

	private NeedStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getCode(String userStatus) {
		String result = "";
		if(StringUtil.isBlank(userStatus)){
			return result;
		}
		switch (userStatus) {
		case "NEED":
			result = NEED.code;
			break;
		case "CANCEL":
			result = CANCEL.code;
		default:
			break;
		}
		return result;
	}

}
