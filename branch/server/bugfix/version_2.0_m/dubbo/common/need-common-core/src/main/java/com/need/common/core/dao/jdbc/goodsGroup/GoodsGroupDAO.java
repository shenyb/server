package com.need.common.core.dao.jdbc.goodsGroup;


import com.need.common.domain.po.api.goodsGroup.GoodsGroup;
import com.need.common.domain.po.api.goodsGroup.GoodsGroupKey;



public interface GoodsGroupDAO {
    int deleteByPrimaryKey(GoodsGroupKey key);

    int insert(GoodsGroup record);

    int insertSelective(GoodsGroup record);

    GoodsGroup selectByPrimaryKey(GoodsGroupKey key);

    int updateByPrimaryKeySelective(GoodsGroup record);

    int updateByPrimaryKey(GoodsGroup record);


	
}