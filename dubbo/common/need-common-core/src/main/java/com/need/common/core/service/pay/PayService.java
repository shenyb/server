package com.need.common.core.service.pay;

import com.need.common.domain.enums.PayChannelEnum;
import com.need.common.domain.enums.PayTypeEnum;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.pub.Message;

/**
 * 
 * @author david.tan
 *
 */
public interface PayService {

	/**
	 * 
	 * @param tradeNo
	 * @param outPayNo
	 * @param payAmount
	 * @param payChannel
	 * @param payType
	 * @return
	 */
	Message savePayRecord(String tradeNo, String outPayNo, int payAmount, PayChannelEnum payChannel,
			PayTypeEnum payType, String memo);

	/**
	 * 
	 * @author shenyb 2015年8月25日 上午12:10:54 @Method: getAlipaySign @Description:
	 * 获取支付宝签名 @param success @param userId @param tradeNo @return @throws
	 */
	Message getAlipaySign(Message success, String userId, String tradeNo);

	Message getAlipaySignV1_1(Message success, String userId, String tradeNo, String couponNo);

	Message saveAndGetAlipaySignV1_2(Message success, String userId, String tradeNo, String couponNo);

	Message saveAndGetAlipaySignForWap(Message success, String userId, String tradeNo, String couponNo,String returnUrl);
	Message checkUserBalance(TradeBasePO trade);
}
