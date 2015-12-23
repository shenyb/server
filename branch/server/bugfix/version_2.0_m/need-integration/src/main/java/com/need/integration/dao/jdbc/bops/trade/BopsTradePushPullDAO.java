package com.need.integration.dao.jdbc.bops.trade;

import com.need.integration.dao.jdbc.bops.trade.po.BopsTradePushPullPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BopsTradePushPullDAO {

	int insert(BopsTradePushPullPO record);

	BopsTradePushPullPO getByOrderNo(String orderNo);

	BopsTradePushPullPO getByTradeNo(String tradeNo);

	int updateByOrderNo(BopsTradePushPullPO record);
	int updateByTradeNo(BopsTradePushPullPO record);
	int updateLogisticsNo(@Param("userTradeNo")String userTradeNo, @Param("logisticsNo")String logisticsNo);
	
	/**
	 * 
	 * @author xiehao 2015年10月27日 下午3:17:16
	 * @Method: updatePushStatus 
	 * @Description: TODO 更新笨鸟返回的推送信息
	 * @param userTradeNo
	 */
	int updatePushStatus(@Param("userTradeNo")String userTradeNo, @Param("result")String result);
	
	int updateBirdexResult(@Param("userTradeNo") String userTradeNo, @Param("result") String result);

	List<BopsTradePushPullPO> queryListToPull();

	List<BopsTradePushPullPO> queryListToRetrieve();

	List<BopsTradePushPullPO> queryListToDeliver();

	Integer getMaxBatchCount(String batchNo);

	List<BopsTradePushPullPO> getDeliverTradeListToPullStatus();

}