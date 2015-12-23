package com.need.dao.api.goods;

import java.util.List;

import com.need.domain.po.api.goods.GoodsItemsPO;

public interface GoodsItemsDAO {
    int deleteByPrimaryKey(Integer goodsItemsId);

    int insert(GoodsItemsPO record);

    int insertSelective(GoodsItemsPO record);

    GoodsItemsPO selectByPrimaryKey(Integer goodsItemsId);

    int updateByPrimaryKeySelective(GoodsItemsPO record);

    int updateByPrimaryKey(GoodsItemsPO record);
    
    int deleteByGroupId(String goodsGroupId);
    
}