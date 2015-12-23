package com.need.common.domain.enums;

/**
 * <p></p>
 * @author Rylan 2015年12月3日 上午10:46:23
 * @ClassName OrderChannelEnum
 * @Description 支付类型枚举类
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年12月3日
 * @modify by reason:{方法名}:{原因}
 */
public enum OrderChannelEnum {

	ANDROID("ANDROID","安卓"),IOS("IOS","ios"),WAP("WAP","m站");
	
	public String code;
	public String desc;
	
	private OrderChannelEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	
	
}
