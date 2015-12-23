package com.need.common.core.dao.jdbc.trade;

import com.need.common.domain.po.api.trade.TradePushPullPO;

import java.util.List;

public interface TradePushPullDAO {

	int insert(TradePushPullPO record);

	TradePushPullPO getByOrderNo(String orderNo);

	TradePushPullPO getByTradeNo(String tradeNo);

	List<TradePushPullPO> queryListToPull();

	List<TradePushPullPO> queryListToRetrieve();

	List<TradePushPullPO> queryListToDelievr();

	int updateByOrderNo(TradePushPullPO record);

	Integer getMaxBatchCount(String batchNo);

}