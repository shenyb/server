package com.need.common.core.dao.jdbc.goods;

import com.need.common.domain.po.api.goods.GoodsIndexCategoryPO;
import com.need.common.domain.vo.goods.CategoryVO;
import com.need.common.domain.vo.goods.GoodsCategoryVO;

import java.util.List;


public interface GoodsIndexCategoryDAO {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(GoodsIndexCategoryPO record);

    int insertSelective(GoodsIndexCategoryPO record);

    GoodsIndexCategoryPO selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(GoodsIndexCategoryPO record);

    int updateByPrimaryKey(GoodsIndexCategoryPO record);

	List<CategoryVO> getLevelOne(String categoryLevel);

	List<GoodsCategoryVO> getSonCategory(Integer parentId);
}