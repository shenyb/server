package com.need.integration.dao.jdbc.api.trade;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.integration.dao.jdbc.api.trade.po.TradePushPullPO;

public interface TradePushPullDAO {

	int insert(TradePushPullPO record);

	TradePushPullPO getByOrderNo(String orderNo);
	
	int updateLogisticsNo(@Param("userTradeNo")String userTradeNo, @Param("logisticsNo")String logisticsNo);
	
	int updatePushStatus(@Param("userTradeNo")String userTradeNo, @Param("result")String result);
	
	int updateBirdexResult(@Param("userTradeNo") String userTradeNo, @Param("result") String result);

	TradePushPullPO getByTradeNo(String tradeNo);

	List<TradePushPullPO> queryListToPull();

	List<TradePushPullPO> queryListToRetrieve();

	List<TradePushPullPO> queryListToDelievr();

	int updateByOrderNo(TradePushPullPO record);

	Integer getMaxBatchCount(String batchNo);

}