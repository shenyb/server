package com.need.common.domain.enums;

import com.need.utils.StringUtil;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月13日 上午12:42:38
 * @ClassName PayTypeEnum
 * @Description 支付类型
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月13日
 * @modify by reason:{方法名}:{原因}
 */
public enum PayTypeEnum {
	TRADE_PAY("TRADE_PAY", "支付"), TRADE_REFUND("TRADE_REFUND", "退款");
	public String code;
	public String desc;

	private PayTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getCode(String payChennel) {
		String result = "";
		if (StringUtil.isBlank(payChennel)) {
			return result;
		}
		switch (payChennel) {
		case "TRADE_PAY":
			result = TRADE_PAY.code;
			break;
		case "TRADE_REFUND":
			result = TRADE_REFUND.code;
		default:
			break;
		}
		return result;
	}

}
