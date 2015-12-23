package com.need.dao.api.trade;

import com.need.domain.po.api.trade.TradeRefundPO;
public interface TradeRefundDAO {
    int deleteByPrimaryKey(String tradeNo);

    int insert(TradeRefundPO record);

    int insertSelective(TradeRefundPO record);

    TradeRefundPO selectByPrimaryKey(String tradeNo);

    int updateByPrimaryKeySelective(TradeRefundPO record);

    int updateByPrimaryKey(TradeRefundPO record);
}