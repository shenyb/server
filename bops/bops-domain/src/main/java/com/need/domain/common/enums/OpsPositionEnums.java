package com.need.domain.common.enums;

public enum OpsPositionEnums {

	START_BANNER("START_BANNER", "欢迎页运营位"),
	HOME_BANNER("HOME_BANNER", "首页运营位"),
	SHIJIAN_BANNER("SHIJIAN_BANNER", "商店_世间_首个运营位"),
	XINHUAN("XINHUAN", "商店_新欢_运营位"),
	SHIJIAN_SCROLL("SHIJIAN_SCROLL", "商店_世间_SCROLL运营位"),
	XINHUAN_BANNER("XINHUAN_BANNER", "新欢_BANNER运营位"),
	XINHUAN_SCROLL("XINHUAN_SCROLL", "新欢_SCROLL运营位"),
	XINHUAN_PREFECTURE("XINHUAN_PREFECTURE", "新欢_专区运营位");
	
	public String code;
	
	public String desc;
	
	private OpsPositionEnums(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
}
