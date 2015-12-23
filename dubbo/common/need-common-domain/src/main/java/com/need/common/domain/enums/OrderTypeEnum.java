package com.need.common.domain.enums;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年11月20日 下午5:10:48
 * @ClassName OrderTypeEnum
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年11月20日
 * @modify by reason:{方法名}:{原因}
 */
public enum OrderTypeEnum {

	COMMON("COMMON", "普通"), CHEAP("CHEAP", "团便宜"),COMBINATION("COMBINATION", "组合"),DISTRIBUTION("DISTRIBUTION", "分销");
	public String code;
	public String desc;

	private OrderTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}
