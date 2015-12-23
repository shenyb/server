package com.need.operation.constant;

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
	/**
	 * 跳转团便宜编辑页面
	 */
	public final static String TO_CHEAP_EDIT_PAGE ="/toEditPage/{cheapNo}";
	/**
	 * 团便宜统计
	 */
	public final static String  CHEAP_STAT="/cheapStat";
	
	
	/**
	 * 商品组合列表
	 */
	public final static String  GOODS_GROUP_LIST=WEB_COMMON+"/goodsGroup/page";
	
	
}
