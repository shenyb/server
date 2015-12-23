package com.need.service.constant;

/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年6月26日 下午10:53:23
 * @ClassName Constants
 * @Description TODO 针对不同的业务类型建立不同的过期时间
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年6月26日
 * @modify by reason:{方法名}:{原因}
 */
public class ConstantsTimeOut {

	/**
	 * @Fields BOPSUSER_EXPIRE_TIME : TODO 用户信息失效时间 60分钟
	 */
	public static final Integer BOPSUSER_EXPIRE_TIME = 60*60*24*7;

	/** 
	* @Fields USER_COOKIE_EXPIRE_TIME : TODO 用户Cookie失效时间 单位是秒
	*/ 
	public static final Integer USER_COOKIE_EXPIRE_TIME=60*60*24*7;
	
	/** 
	* @Fields WAIT_ORDER_EXPIRE_TIME : TODO 订单待付款的失效时间  30分钟单位是毫秒
	*/ 
	public static final Long WAIT_ORDER_EXPIRE_TIME=1000*60*30l;
	
	/** 
	* @Fields WAIT_RECEIVE_EXPIRE_TIME : TODO 订单待收货的失效时间  10天 单位是毫秒
	*/ 
	public static final Long WAIT_RECEIVE_EXPIRE_TIME=1000*60*60*24*10l;
	
	
	
	
	
}
