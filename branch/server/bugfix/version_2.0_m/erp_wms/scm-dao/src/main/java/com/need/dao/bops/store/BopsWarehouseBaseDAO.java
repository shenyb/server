package com.need.dao.bops.store;

import com.need.domain.po.bops.store.BopsWarehouseBasePO;

public interface BopsWarehouseBaseDAO {
    int deleteByPrimaryKey(Integer warehouseId);

    int insert(BopsWarehouseBasePO record);

    int insertSelective(BopsWarehouseBasePO record);

    BopsWarehouseBasePO selectByPrimaryKey(Integer warehouseId);

    int updateByPrimaryKeySelective(BopsWarehouseBasePO record);

    int updateByPrimaryKey(BopsWarehouseBasePO record);
}