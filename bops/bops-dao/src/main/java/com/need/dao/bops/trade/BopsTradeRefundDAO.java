package com.need.dao.bops.trade;

import com.need.domain.po.bops.trade.BopsTradeRefund;

public interface BopsTradeRefundDAO {
    int deleteByPrimaryKey(String tradeNo);

	int insert(BopsTradeRefund record);

	int insertSelective(BopsTradeRefund record);

	BopsTradeRefund selectByPrimaryKey(String tradeNo);

	int updateByPrimaryKeySelective(BopsTradeRefund record);

	int updateByPrimaryKey(BopsTradeRefund record);

}