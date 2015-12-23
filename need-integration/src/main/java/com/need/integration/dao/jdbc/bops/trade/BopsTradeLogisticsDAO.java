package com.need.integration.dao.jdbc.bops.trade;

import com.need.integration.dao.jdbc.bops.trade.po.BopsTradeLogisticsPO;

public interface BopsTradeLogisticsDAO {
	int deleteByPrimaryKey(String tradeNo);

	int insert(BopsTradeLogisticsPO record);

	BopsTradeLogisticsPO selectByPrimaryKey(String tradeNo);

}