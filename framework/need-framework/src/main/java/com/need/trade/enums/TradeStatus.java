package com.need.trade.enums;

import org.apache.commons.lang.StringUtils;


/**
 * Need1.0交易状态枚举
 * 
 * @author david
 *
 */
public enum TradeStatus {
	
	/**超时时间   半小时  对应交易关闭  */
	 WAIT_PAY("WAIT_PAY", "待付款"),
	 
	 /** 平台发货后，前台商品减库存,注意购物车是多个商品的减库存操作   */
	 WAIT_PLATFORM_SEND("WAIT_PLATFORM_SEND","待发货"),
	 
	 /**超时时间，10天   对应交易成功 */
	 WAIT_RECEIVE("WAIT_RECEIVE", "待收货"),
	 
	TRADE_SUCCESS("TRADE_SUCCESS", "交易成功"),
	TRADE_CLOSE("TRADE_CLOSE", "交易关闭"),
	
	/**支付宝notify退款成功后，变更交易状态为退款成功  */
	REFUND_SUCCESS("REFUND_SUCCESS", "退款成功"),
	;

	public String code;
	public String message;

	
	private TradeStatus(String code,String message){
		this.code=code;
		this.message=message;
	}
	
	

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static TradeStatus getByCode(String code){
		for(TradeStatus tradeStatus:values()){
		   if(StringUtils.equals(code, tradeStatus.code))
			   
			   return tradeStatus;
			
		}
		
		return null;
	}
	
	public static String getDesc(String tradeStatus){
		String result = "";
		
		if(StringUtils.isBlank(tradeStatus))
			return result;
		
		switch(tradeStatus){
		case "WAIT_PAY":
			result = WAIT_PAY.message;
			break;
		case "WAIT_PLATFORM_SEND":
			result = WAIT_PLATFORM_SEND.message;
			break;
		case "WAIT_RECEIVE":
			result = WAIT_RECEIVE.message;
			break;
		case "TRADE_SUCCESS":
			result = TRADE_SUCCESS.message;
			break;
		case "TRADE_CLOSE":
			result = TRADE_CLOSE.message;
			break;
		case "REFUND_SUCCESS":
			result = REFUND_SUCCESS.message;
			break;
		}
		return result;
	}
	
	
	
	
}
