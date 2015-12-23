package com.need.integration.dao.jdbc.bops.store;

import org.apache.ibatis.annotations.Param;

import com.need.integration.dao.jdbc.bops.store.po.BopsInventoryPO;

public interface BopsInventoryDAO {
	int deleteByPrimaryKey(Long id);

	int insert(BopsInventoryPO record);

	int insertSelective(BopsInventoryPO record);

	BopsInventoryPO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(BopsInventoryPO record);

	int updateByPrimaryKey(BopsInventoryPO record);

	int updateStore(@Param("goodsId") String goodsId, @Param("buyCount") int buyCount);

	BopsInventoryPO selectByGoodsId(String goodsId);
}