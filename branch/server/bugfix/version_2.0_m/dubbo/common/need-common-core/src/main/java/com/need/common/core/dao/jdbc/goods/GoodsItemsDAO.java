package com.need.common.core.dao.jdbc.goods;

import com.need.common.domain.po.api.goods.GoodsItemsPO;

import java.util.List;

public interface GoodsItemsDAO {
    int deleteByPrimaryKey(Integer goodsItemsId);

    int insert(GoodsItemsPO record);

    int insertSelective(GoodsItemsPO record);

    GoodsItemsPO selectByPrimaryKey(Integer goodsItemsId);
    
    List<GoodsItemsPO> queryByGoodsGroupId(String goodsGroupId);

    int updateByPrimaryKeySelective(GoodsItemsPO record);

    int updateByPrimaryKey(GoodsItemsPO record);
}