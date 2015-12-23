package com.need.service.bops.trade;

import java.util.List;

import com.need.domain.po.bops.trade.BopsTradePushPull;


public interface BopsTradePushPullService {

	List<BopsTradePushPull> queryByBatchNo(String batchNo);
	void createDeliverTradeTest();
}
