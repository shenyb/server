package com.need.share.constant;

/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年7月22日 下午7:45:16
 * @ClassName ControllerMappings
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月22日
 * @modify by reason:{方法名}:{原因}
 */
public class ControllerMappings {

	/**
	 * @Fields GOODS : TODO 商品前缀
	 */
	public final static String GOODS = "goods/";

	/**
	 * @Fields GOODS : TODO 行家前缀
	 */
	public final static String KOL = "kol/";

	/**
	 * @Fields WEB_GOODS_DETAIL : TODO 商品详情
	 */
	public final static String WEB_GOODS_DETAIL = GOODS;
	/**
	 * @Fields WEB_GOODS_DETAIL : TODO 行家详情
	 */
	public final static String WEB_KOL_DETAIL = KOL;
	
	
	/**
	 * @Fields WEB_JUMP_WEIXIN : TODO 跳转
	 */
	public final static String WEB_JUMP_WEIXIN = "mp/redirect";
	

	/**
	 * @Fields WEB_JUMP_WEIXIN : TODO 
	 */
	public final static String WEB_TOPIC_OPERATE = "/topic/";
	
	/**
	 * @Fields WEB_WECHAT_CALL_BACK :微信回调获取code
	 */
	public final static String WEB_WECHAT_CALL_BACK = "wechat/callback";

	/**
	 * @Fields COUPON : TODO 优惠券前缀
	 */
	public final static String COUPON = "coupon";
	
	/***
	 * 商品组合管理
	 */
	public final static String BOPS_GOODS_GROUP = "goodsGroup";
	
	
	
}
