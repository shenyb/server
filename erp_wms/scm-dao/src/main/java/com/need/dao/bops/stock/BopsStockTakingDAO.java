package com.need.dao.bops.stock;

import com.need.domain.po.bops.stock.BopsStockTakingPO;

public interface BopsStockTakingDAO {
    int deleteByPrimaryKey(Long id);

    int insert(BopsStockTakingPO record);

    int insertSelective(BopsStockTakingPO record);

    BopsStockTakingPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BopsStockTakingPO record);

    int updateByPrimaryKey(BopsStockTakingPO record);
    
    BopsStockTakingPO selectByStockNo(String stockTakingNo);
    
}