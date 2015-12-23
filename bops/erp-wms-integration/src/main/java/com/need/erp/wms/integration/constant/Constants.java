package com.need.erp.wms.integration.constant;

/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年7月22日 下午7:13:12
 * @ClassName Constants
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月22日
 * @modify by reason:{方法名}:{原因}
 */
public class Constants {

	/**
	 * @Fields APP_USER_INFO : TODO 每页的数量默认是10个
	 */
	public static final Integer EVERY_PAGE_SIZE = 10;

	/**
	 * @Fields APP_USER_INFO : TODO 每页的数量默认是10个
	 */
	public static final String PIC_DOMAIN_KEY = "/properties/constants.properties";
	/**
	 * 包装箱的重量，kg
	 */

	public static final float BOX_WEIGHT = 0.18f;

	/**
	 * 圆通在国检的备案号
	 */
	public static final String YUANTONG_LOGISTICS_CIQ_NO = "4100300005";
	public static final String YUANTONG_TRANSPORT_ID="230084";
	public static final String CHINA="中国";
	public static final String TRUE=Boolean.TRUE.toString().toUpperCase();
	public static final String FALSE=Boolean.FALSE.toString().toUpperCase();
	/**
	 * 报关行
	 */
	public static final String CUSTOM_PLACE_ZHENGZHOU = "ZHENGZHOU";
	/**
	 * 报关行广州
	 */
	public static final String CUSTOM_PLACE_GUANGZHOU = "GUANGZHOU";
	
	/**
	 * 深圳 报关
	 */
	public static final String CUSTOM_PLACE_SHENZHEN = "SHENZHEN";
	
	/**
	 * 30分钟，e贸易推送成功后每隔三十分钟，如果还拿不到运单，则重新推送 毫秒
	 */
	public static final long REPEAT_TO_SEND_TIME=30 * 1000 * 60;
}
