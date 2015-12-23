package com.need.dao.api.goods;

import com.need.domain.po.api.goods.GoodsCategoriesPO;
import com.need.domain.vo.goods.BopsGoodsCategoriesVO;

public interface GoodsCategoriesDAO {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(GoodsCategoriesPO record);

    int insertSelective(GoodsCategoriesPO record);

    GoodsCategoriesPO selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(GoodsCategoriesPO record);

    int updateByPrimaryKey(GoodsCategoriesPO record);
    
    void updateCategory(BopsGoodsCategoriesVO bopsGoodsCategoriesVO);
}