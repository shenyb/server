package com.need.dao.bops.trade;

import com.need.domain.po.bops.trade.WmsOrderToErp;

public interface WmsOrderToErpDAO {
    int deleteByPrimaryKey(Long id);

    int insert(WmsOrderToErp record);

    int insertSelective(WmsOrderToErp record);

    WmsOrderToErp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WmsOrderToErp record);

    int updateByPrimaryKey(WmsOrderToErp record);
}