package com.need.dao.api.goods;

import java.util.List;

import com.need.domain.po.api.goods.GoodsDetailPO;

public interface GoodsDetailDAO {
    int deleteByPrimaryKey(String goodsId);

    int insert(GoodsDetailPO goodsDetail);

    int insertSelective(GoodsDetailPO goodsDetail);

    GoodsDetailPO selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(GoodsDetailPO goodsDetail);

    int updateByPrimaryKey(GoodsDetailPO goodsDetail);
    
    
}