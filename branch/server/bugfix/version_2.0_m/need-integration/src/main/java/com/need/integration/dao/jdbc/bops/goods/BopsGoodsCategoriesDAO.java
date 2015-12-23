package com.need.integration.dao.jdbc.bops.goods;

import java.util.List;

import com.need.integration.dao.jdbc.bops.goods.po.BopsGoodsCategoriesPO;

public interface BopsGoodsCategoriesDAO {
	int deleteByPrimaryKey(Integer categoryId);

	int insertSelective(BopsGoodsCategoriesPO record);

	BopsGoodsCategoriesPO selectByPrimaryKey(Integer categoryId);

	int updateByPrimaryKeySelective(BopsGoodsCategoriesPO record);

	int updateByPrimaryKey(BopsGoodsCategoriesPO record);

	List<BopsGoodsCategoriesPO> selectCategoryLevel(BopsGoodsCategoriesPO bopsGoodsCategoriesPO);

	List<BopsGoodsCategoriesPO> selectCategoryLevelByParent(BopsGoodsCategoriesPO bopsGoodsCategoriesPO);

	List<BopsGoodsCategoriesPO> selectCategoryLevelByLevel(BopsGoodsCategoriesPO bopsGoodsCategoriesPO);

}