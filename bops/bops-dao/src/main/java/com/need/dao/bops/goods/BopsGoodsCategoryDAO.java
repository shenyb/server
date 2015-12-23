package com.need.dao.bops.goods;

import java.util.List;

import com.need.domain.po.bops.goods.BopsGoodsCategory;

public interface BopsGoodsCategoryDAO {
	int deleteByPrimaryKey(Integer categoryId);

	int insert(BopsGoodsCategory record);

	int insertSelective(BopsGoodsCategory record);

	BopsGoodsCategory selectByPrimaryKey(Integer categoryId);

	int updateByPrimaryKeySelective(BopsGoodsCategory record);

	int updateByPrimaryKey(BopsGoodsCategory record);
	
	List<BopsGoodsCategory> queryGoodsCategory();
}