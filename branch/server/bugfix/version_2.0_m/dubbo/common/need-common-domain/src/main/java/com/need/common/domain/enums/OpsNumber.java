package com.need.common.domain.enums;

/**
 * <p></p>
 * @author LXD 2015年8月13日 下午6:17:33
 * @ClassName OpsNumber
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月13日
 * @modify by reason:{方法名}:{原因}
 */
public enum OpsNumber {
	BANNER_HOME("BANNER_HOME", "首页运营位"), BANNER_SHOP("BANNER_SHOP", "商店运营位");
	
	public String code;
	public String desc;

	private OpsNumber(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}



}
