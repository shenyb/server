package com.need.erp.wms.integration.constant;

/**
 * <p></p>
 * @author Rylan 2015年10月14日 上午12:17:42
 * @ClassName UrlMappings
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年10月14日
 * @modify by reason:{方法名}:{原因}
 */
public class UrlMappings {
	/**常量------------------------------------------------------------------*/
	/**
	 * 跳转controller的关键字
	 */
	public final static String WEB_COMMON = "redirect:";
	
	/**
	 * 运营位审核列表
	 */
	public final static String WEB_ADMIN_INDEX = "/index";
	
	/**
	 * 跳转 专题分类controller
	 */
	public final static String TO_TOPIC_CATEGORY = WEB_COMMON+"/topic/category/page";
}
