package com.need.dao.bops.trade;

import com.need.domain.po.bops.trade.BopsExchange;

public interface BopsExchangeDAO {
    int deleteByPrimaryKey(Long id);

    int insert(BopsExchange record);

    int insertSelective(BopsExchange record);

    BopsExchange selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BopsExchange record);

    int updateByPrimaryKey(BopsExchange record);
}