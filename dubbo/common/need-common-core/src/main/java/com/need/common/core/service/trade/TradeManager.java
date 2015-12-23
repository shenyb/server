package com.need.common.core.service.trade;

import com.need.common.domain.po.api.trade.TradeJudgementPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.PayNotifyVO;
import com.need.common.domain.vo.trade.RefundNotifyVO;
import com.need.common.domain.vo.trade.TradeListVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * </p>
 * 
 * @author david.tan 2015年8月7日 下午7:13:15
 * @ClassName TradeManager
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public interface TradeManager {
	String paySuccess(String tradeNo, PayNotifyVO aliPayNotifyVO);

	void refundSuccess(String tradeNo, RefundNotifyVO vo);


	int confirm(String tradeNo);

	int refund(String tradeNo, Float money);

	public List<TradeListVO> getUserOrderlist(String buyerId, Integer pageNum, Integer pageSize, String orderStatus,Message m);

	void addtradeComment(TradeJudgementPO judgementPO);
	
	public int cancel(String tradeNo, String userId);

	String dealWechatNotify(Map<String,String> params);

	String dealAlipayNotify(Map<String,String> params);

	List<TradeListVO> getUserOrderlistV1_3(String buyerId, Integer pageNum, Integer pageSize, String orderStatus,Message m);

	String dealWechatNotifyForWap(Map<String,String> params);
}
