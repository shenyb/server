package com.need.integration.dao.jdbc.api.trade;

import com.need.integration.dao.jdbc.api.trade.po.TradePayPO;

public interface TradePayDAO {

    TradePayPO getByPayId(String payId);
    TradePayPO getByTradeNo(String tradeNo);
}