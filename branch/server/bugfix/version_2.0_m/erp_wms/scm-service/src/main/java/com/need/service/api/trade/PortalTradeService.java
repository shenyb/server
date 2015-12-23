package com.need.service.api.trade;

import com.need.domain.pub.Message;

/**
 * 
 * @author david.tan
 *
 */
public interface PortalTradeService {

	/**
	 * 发货推动前台的交易变更，必须在一个编程事务内完成，核心逻辑：
	 *<ul>
	 *<li>1.商品减库存，多商品时注意循环</li>
	 *<li>2.更改交易状态，变更为WAIT_RECEVIE</li>
	 *<li>3.返回执行结果</li>
	 *</ul>
	 * @param tradeNo
	 * @return
	 */
   Message sendTrade(String tradeNo);	
   
   /**
	 * 发货推动前台的交易变更，必须在一个编程事务内完成，核心逻辑：
	 *<ul>
	 *<li>1.遍历交易编号下的所有子订单,更改交易状态，变更为REFUD_SUCCESS</li>
	 *<li>2.生成一条交易关联的退款记录</li>
	 *<li>3.返回执行结果</li>
	 *</ul>
	 * @param tradeNo
	 * @param refundAmount
	 * @param memo
	 * @return
	 */
   Message  refund(String tradeNo,int refundAmount,String memo);
	
  // public Message  saveTest();
   
}
