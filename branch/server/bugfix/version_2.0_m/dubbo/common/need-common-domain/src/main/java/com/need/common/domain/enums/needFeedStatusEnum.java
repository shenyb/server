package com.need.common.domain.enums;

/**
 * 
 * <p></p>
 * @author LXD 2015年8月28日 下午1:19:31
 * @ClassName needFeedStatusEnum
 * @Description TODO needFeed的状态
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月28日
 * @modify by reason:{方法名}:{原因}
 */
public enum needFeedStatusEnum {
	VALID("VALID", "有效"), INVALID("INVALID", "无效");
	public String code;
	public String desc;

	private needFeedStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	

}
