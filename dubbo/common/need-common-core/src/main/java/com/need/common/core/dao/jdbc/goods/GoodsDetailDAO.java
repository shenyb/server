package com.need.common.core.dao.jdbc.goods;

import com.need.common.domain.po.api.goods.GoodsDetailPO;

public interface GoodsDetailDAO {
    int deleteByPrimaryKey(String goodsId);

    int insert(GoodsDetailPO record);

    int insertSelective(GoodsDetailPO record);

    GoodsDetailPO selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(GoodsDetailPO record);

    int updateByPrimaryKey(GoodsDetailPO record);
    
}