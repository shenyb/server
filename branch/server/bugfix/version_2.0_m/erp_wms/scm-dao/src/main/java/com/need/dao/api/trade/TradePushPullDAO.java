package com.need.dao.api.trade;

import com.need.domain.po.api.trade.TradePushPullPO;

public interface TradePushPullDAO {
    int deleteByPrimaryKey(String orderNo);

    int insert(TradePushPullPO record);

    int insertSelective(TradePushPullPO record);

    TradePushPullPO selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(TradePushPullPO record);

    int updateByPrimaryKey(TradePushPullPO record);
}