package com.need.dao.bops.ops;

import java.util.List;

import com.need.domain.po.bops.ops.BopsIndexCategoryPO;
import com.need.domain.vo.ops.BopsIndexCategoryParam;

public interface BopsIndexCategoryDAO {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(BopsIndexCategoryPO record);

    int insertSelective(BopsIndexCategoryPO record);

    BopsIndexCategoryPO selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(BopsIndexCategoryPO record);

    int updateByPrimaryKey(BopsIndexCategoryPO record);

	List<BopsIndexCategoryParam> selectCategoryLevel(String categoryLevel);

	List<BopsIndexCategoryParam> selectPreLevel(String parentId);

	BopsIndexCategoryPO selectBycategoryName(BopsIndexCategoryParam categoriesVO);

	List<BopsIndexCategoryPO> selectCategoryLevelByLevel(BopsIndexCategoryPO bopsIndexCategoriesPO);

	BopsIndexCategoryParam getBycategoryId(String categoryId);

	void update(BopsIndexCategoryParam categoriesVO);
}