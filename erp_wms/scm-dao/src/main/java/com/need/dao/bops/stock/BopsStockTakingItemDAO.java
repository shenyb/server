package com.need.dao.bops.stock;

import java.util.List;

import com.need.domain.po.bops.stock.BopsStockTakingItemPO;

public interface BopsStockTakingItemDAO {
    int deleteByPrimaryKey(Long id);

    int insert(BopsStockTakingItemPO record);

    int insertSelective(BopsStockTakingItemPO record);

    BopsStockTakingItemPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BopsStockTakingItemPO record);

    int updateByPrimaryKey(BopsStockTakingItemPO record);
    
    List<BopsStockTakingItemPO> selectGoodsList(Long stockTakingNo);
    
    List<BopsStockTakingItemPO> selectGoodsListByTaking(String stockTakingNo);
}