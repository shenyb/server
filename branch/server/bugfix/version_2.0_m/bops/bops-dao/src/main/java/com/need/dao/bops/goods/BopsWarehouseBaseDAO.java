package com.need.dao.bops.goods;

import com.need.domain.po.bops.goods.BopsWarehouseBase;

public interface BopsWarehouseBaseDAO {
    int deleteByPrimaryKey(Integer warehouseId);

    int insert(BopsWarehouseBase record);

    int insertSelective(BopsWarehouseBase record);

    BopsWarehouseBase selectByPrimaryKey(Integer warehouseId);

    int updateByPrimaryKeySelective(BopsWarehouseBase record);

    int updateByPrimaryKey(BopsWarehouseBase record);
}