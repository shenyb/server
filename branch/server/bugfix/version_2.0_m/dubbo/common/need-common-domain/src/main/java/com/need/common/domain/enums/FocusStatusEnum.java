package com.need.common.domain.enums;

/**
 * 
 * <p></p>
 * @author LXD 2015年8月12日 下午8:03:28
 * @ClassName NeedStatusEnum
 * @Description TODO 用户关注行家：FOCUS ：取消关注：CANCEL
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月12日
 * @modify by reason:{方法名}:{原因}
 */
public enum FocusStatusEnum {
	FOCUS("FOCUS", "关注状态"), CANCEL("CANCEL", "取消状态");
	public String code;
	public String desc;

	private FocusStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	
}
