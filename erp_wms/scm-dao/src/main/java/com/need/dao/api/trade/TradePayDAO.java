package com.need.dao.api.trade;

import com.need.domain.po.api.trade.TradePayPO;

public interface TradePayDAO {
    int deleteByPrimaryKey(String payId);

    int insert(TradePayPO record);

    int insertSelective(TradePayPO record);

    TradePayPO selectByPrimaryKey(String payId);

    int updateByPrimaryKeySelective(TradePayPO record);

    int updateByPrimaryKey(TradePayPO record);
    
    TradePayPO selectByTradeNo(String tradeNo);
}