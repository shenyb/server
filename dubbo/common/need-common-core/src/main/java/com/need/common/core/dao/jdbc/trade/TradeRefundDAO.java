package com.need.common.core.dao.jdbc.trade;

import com.need.common.domain.po.api.trade.TradeRefundPO;

public interface TradeRefundDAO {
    int deleteByPrimaryKey(String tradeNo);

    int insert(TradeRefundPO record);

    int insertSelective(TradeRefundPO record);

    TradeRefundPO selectByPrimaryKey(String tradeNo);

    int updateByPrimaryKeySelective(TradeRefundPO record);

    int updateByPrimaryKey(TradeRefundPO record);
}