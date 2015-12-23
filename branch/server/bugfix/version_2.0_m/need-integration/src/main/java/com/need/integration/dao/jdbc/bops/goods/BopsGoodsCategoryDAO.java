package com.need.integration.dao.jdbc.bops.goods;

import java.util.List;

import com.need.integration.dao.jdbc.bops.goods.po.BopsGoodsCategory;

public interface BopsGoodsCategoryDAO {
	int deleteByPrimaryKey(Integer categoryId);

	int insert(BopsGoodsCategory record);

	int insertSelective(BopsGoodsCategory record);

	BopsGoodsCategory selectByPrimaryKey(Integer categoryId);

	int updateByPrimaryKeySelective(BopsGoodsCategory record);

	int updateByPrimaryKey(BopsGoodsCategory record);
	
	List<BopsGoodsCategory> queryGoodsCategory();
}