package com.need.domain.common.enums;

import com.need.utils.StringUtil;

/**
 * 
 * <p></p>
 * @author LXD 2015年8月12日 下午8:03:28
 * @ClassName NeedStatusEnum
 * @Description TODO 运营位类型： 1：专题；2：商品；3：活动；4：行家;5:团便宜
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月12日
 * @modify by reason:{方法名}:{原因}
 */
public enum OpsPositionTypeEnum {
	TOPIC("TOPIC", "专题"), 
	GOODS("GOODS", "商品"),
	ACTIVITY("ACTIVITY", "活动"),
	CHEAP("CHEAP","团便宜"),
	DISCOUNT("DISCOUNT","促销"),
	COUPON("COUPON", "优惠券"),
	KOL("KOL","行家");
	
	public String code;
	public String desc;

	private OpsPositionTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getCode(String opsType) {
		String result = "";
		if(StringUtil.isBlank(opsType)){
			return result;
		}
		switch (opsType) {
		case "专题":
			result = TOPIC.code;
			break;
		case "商品":
			result = GOODS.code;
			break;
		case "活动":
			result = ACTIVITY.code;
			break;
		case "行家":
			result = KOL.code;
		default:
			break;
		}
		return result;
	}

}
