package com.need.task.dao.jdbc.bops.goods;

import com.need.task.dao.jdbc.bops.goods.po.BopsPriceChangeGoodsPO;

public interface BopsPriceChangeGoodsDAO {
    int deleteByPrimaryKey(Integer pricechangeGoodsId);

    int insert(BopsPriceChangeGoodsPO record);

    int insertSelective(BopsPriceChangeGoodsPO record);

    BopsPriceChangeGoodsPO selectById(Integer pricechangeGoodsId);

    int updateById(BopsPriceChangeGoodsPO record);
    
    int updateStatusById(Integer id ,String status);
    
}