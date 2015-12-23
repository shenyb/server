package com.need.trade;

import com.need.trade.enums.TradeStatus;

/**
 * 交易状态的推进
 * 
 * @author david
 *
 */
public class TradeStatusProcess {

	/**
	 * 正常的交易状态推进
	 * 
	 * @param tradeStatus
	 *            当前的交易状态
	 * @return TradeStatus 推进后的交易状态
	 * 
	 */
	public static TradeStatus tradeProcess(TradeStatus tradeStatus) {

		switch (tradeStatus) {
		//用户付款
		case WAIT_PAY:
			return TradeStatus.WAIT_PLATFORM_SEND;
		//平台发货	
		case WAIT_PLATFORM_SEND:
			return TradeStatus.WAIT_RECEIVE;	
       //用户收货
		case WAIT_RECEIVE:
			return TradeStatus.TRADE_SUCCESS;
		default:
			return null;
		}

	}
	
	
	/**
	 * 退款的交易状态推进，只有待收货的状态可以退款
	 * 
	 * @param tradeStatus
	 *            当前的交易状态
	 * @return TradeStatus 推进后的交易状态
	 * 
	 */
	public static TradeStatus refundProcess(TradeStatus tradeStatus) {

		switch (tradeStatus) {
		case WAIT_RECEIVE:
			return TradeStatus.REFUND_SUCCESS;

		default:
			return null;
		}

	}
	
	
	/**
	 * 取消订单的交易状态推进，只有待付款的交易状态可以取消
	 * 
	 * @param tradeStatus
	 *            当前的交易状态
	 * @return TradeStatus 推进后的交易状态
	 * 
	 */
	public static TradeStatus cancelTradeProcess(TradeStatus tradeStatus) {

		switch (tradeStatus) {
		case WAIT_PAY:
			return TradeStatus.TRADE_CLOSE;

		default:
			return null;
		}

	}	

}
