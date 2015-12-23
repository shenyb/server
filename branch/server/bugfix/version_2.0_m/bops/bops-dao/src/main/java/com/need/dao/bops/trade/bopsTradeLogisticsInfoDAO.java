package com.need.dao.bops.trade;

import com.need.domain.po.bops.trade.bopsTradeLogisticsInfo;

public interface bopsTradeLogisticsInfoDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(bopsTradeLogisticsInfo record);

    int insertSelective(bopsTradeLogisticsInfo record);

    bopsTradeLogisticsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(bopsTradeLogisticsInfo record);

    int updateByPrimaryKey(bopsTradeLogisticsInfo record);
}