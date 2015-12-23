package com.need.domain.common.enums;

import com.need.utils.StringUtil;

/**
 * <p>
 * </p>
 * 
 * @author shenyb 2015年7月24日 下午11:51:05
 * @ClassName UserType
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年7月24日
 * @modify by reason:{方法名}:{原因}<option value="1">百世汇通</option>
 *         <option value="2">圆通</option> <option value="3">笨鸟物流</option>
 */
public enum ServiceProviderEnum {

	HUI_TONG("1", "百世汇通"), YUAN_TONG("2", "圆通"), BIRD("3", "笨鸟物流");
	public String code;
	public String desc;

	private ServiceProviderEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(String code) {
		String result = "";
		if (StringUtil.isBlank(code)) {
			return result;
		}
		switch (code) {
		case "1":
			result = HUI_TONG.desc;
			break;
		case "2":
			result = YUAN_TONG.desc;
			break;
		case "3":
			result = BIRD.desc;
			break;
		default:
		}
		return result;
	}
	public static String getCode(String desc) {
		String result = "";
		if (StringUtil.isBlank(desc)) {
			return result;
		}
		switch (desc) {
		case "百世汇通":
			result = HUI_TONG.code;
			break;
		case "圆通":
			result = YUAN_TONG.code;
			break;
		case "笨鸟物流":
			result = BIRD.code;
			break;
		default:
		}
		return result;
	}
}
