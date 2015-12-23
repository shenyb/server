package com.need.dao.api.ops;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.goods.BopsGoodsScene;

public interface GoodsSceneRelationDAO {

	void insert(BopsGoodsScene bopsGoodsScene);

	void insertSelective(BopsGoodsScene bopsGoodsScene);

	void updateByPrimaryKeySelective(BopsGoodsScene bopsGoodsScene);

	void deleteByPrimaryKey(@Param("id") String id);

	void deleteBygoodsId(String goodsId);
   
    
}