package com.need.integration.dao.jdbc.api.trade;

import com.need.integration.dao.jdbc.api.trade.po.TradeAddressPO;

public interface TradeAddressDAO {
    TradeAddressPO getByAddressId(String addressId);
}