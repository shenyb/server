package com.need.dao.api.goods;

import com.need.domain.po.api.goods.GoodsBrandPO;

public interface GoodsBrandDAO {
    int deleteByPrimaryKey(Integer brandId);

    int insert(GoodsBrandPO record);

    int insertSelective(GoodsBrandPO record);

    GoodsBrandPO selectByPrimaryKey(Integer brandId);

    int updateByPrimaryKeySelective(GoodsBrandPO record);

    int updateByPrimaryKey(GoodsBrandPO record);
}