package com.need.dao.bops.store;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.store.BopsInventoryPO;
import com.need.domain.vo.wms.StoreChangeVO;

public interface BopsInventoryDAO {
	int deleteByPrimaryKey(Long id);

	int insert(BopsInventoryPO record);
	
	int addNewStore(BopsInventoryPO record);

	int insertSelective(BopsInventoryPO record);

	BopsInventoryPO selectByPrimaryKey(Long id);
	
	BopsInventoryPO getByGoodsId(String goodsId);
	
	int updateNormalStore(BopsInventoryPO record);
	
	int updateDemageStore(BopsInventoryPO record);
	
	int updateByPrimaryKeySelective(BopsInventoryPO record);

	int updateByPrimaryKey(BopsInventoryPO record);
	
	int updateStore(@Param("goodsId")String goodsId, @Param("buyCount")int buyCount);
	
	int addNormalStore(StoreChangeVO record);
	
	int updateFreezeStore(@Param("goodsId")String goodsId, @Param("buyCount")int buyCount);
    
    BopsInventoryPO selectByGoodsId(String goodsId);
    
    BopsInventoryPO selectByGoodsCode(String goodsCode);
}