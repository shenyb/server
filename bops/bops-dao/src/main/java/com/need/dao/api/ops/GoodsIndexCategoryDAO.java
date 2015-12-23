package com.need.dao.api.ops;

import com.need.domain.po.api.ops.GoodsIndexCategoryPO;
import com.need.domain.vo.ops.BopsIndexCategoryParam;

public interface GoodsIndexCategoryDAO {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(GoodsIndexCategoryPO record);

    int insertSelective(GoodsIndexCategoryPO record);

    GoodsIndexCategoryPO selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(GoodsIndexCategoryPO record);

    int updateByPrimaryKey(GoodsIndexCategoryPO record);

	void update(BopsIndexCategoryParam categoriesVO);

	void deleteByParentId(String parentId);
}