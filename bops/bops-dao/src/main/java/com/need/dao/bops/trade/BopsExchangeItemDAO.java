package com.need.dao.bops.trade;

import com.need.domain.po.bops.trade.BopsExchangeItem;

public interface BopsExchangeItemDAO {
    int deleteByPrimaryKey(Long id);

    int insert(BopsExchangeItem record);

    int insertSelective(BopsExchangeItem record);

    BopsExchangeItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BopsExchangeItem record);

    int updateByPrimaryKey(BopsExchangeItem record);
}