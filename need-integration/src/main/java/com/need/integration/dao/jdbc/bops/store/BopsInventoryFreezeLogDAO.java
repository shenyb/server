package com.need.integration.dao.jdbc.bops.store;

import org.apache.ibatis.annotations.Param;

import com.need.integration.dao.jdbc.bops.store.po.BopsInventoryFreezeLogPO;

public interface BopsInventoryFreezeLogDAO {
	int deleteByPrimaryKey(Long id);

	int insert(BopsInventoryFreezeLogPO record);

	int insertSelective(BopsInventoryFreezeLogPO record);

	int saveStoreLog(@Param("bopsInventoryId") long bopsInventoryId, @Param("buyCount") int buyCount,
			@Param("orderNo") String orderNo, @Param("remark") String remark);

	BopsInventoryFreezeLogPO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(BopsInventoryFreezeLogPO record);

	int updateByPrimaryKey(BopsInventoryFreezeLogPO record);

}