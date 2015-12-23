package com.need.integration.dao.jdbc.api.trade;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.integration.dao.jdbc.api.trade.po.TradeBasePO;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeBaseDAO {

	TradeBasePO getByOrderNo(String orderNo);

	List<TradeBasePO> queryByTradeNo(String tradeNo);

	List<TradeBasePO> queryGlobalTradeToPush();

	int updateByOrderNo(@Param("orderNo") String orderNo, @Param("pushStatus") String pushStatus);

	int updateByPO(TradeBasePO po);
	
	/**
	 * 
	 * @author xiehao 2015年10月27日 下午3:15:47
	 * @Method: updatePushStatus 
	 * @Description: TODO  更新笨鸟返回的订单信息
	 * @param userTradeNo
	 */
	int updatePushStatus(String userTradeNo);

	List<TradeBasePO> queryListToPushToAlipay();
	
	/**
	 * @author xiehao 2015年11月30日 下午3:43:15
	 * @Method: queryListToPushToWechat 
	 * @Description: TODO 获取微信的支付单推送给郑州海关
	 */
	List<TradeBasePO> queryListToPushToWechat();

	int updateAlipayRetrieveStatusByOrderNo(@Param("orderNo") String orderNo,
			@Param("alipayRetrieveStatus") String alipayRetrieveStatus,
			@Param("memoAlipayRetrieve") String memoAlipayRetrieve);
	int updateWechatRetrieveStatusByOrderNo(@Param("tradeNo")String tradeNo, 
			@Param("wechatRetrieveStatus")String wechatRetrieveStatus,
			@Param("memoWechatRetrieve")String memoWechatRetrieve);

	TradeBasePO getByUserOrderNo(String userOrderNo);

	List<TradeBasePO> queryGlobalTradeToPush_HONGKONG();

	List<TradeBasePO> getByUserTradeNo(String userTradeNo);

}