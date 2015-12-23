package com.need.dao.bops.goods;

import java.util.List;

import com.need.domain.po.bops.goods.BopsGoodsCategoriesPO;
import com.need.domain.vo.goods.BopsGoodsCategoriesVO;

public interface BopsGoodsCategoriesDAO {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(BopsGoodsCategoriesVO record);

    int insertSelective(BopsGoodsCategoriesPO record);

    BopsGoodsCategoriesPO selectByPrimaryKey(Integer categoryId);
    
    List<BopsGoodsCategoriesPO> queryAll();

    int updateByPrimaryKeySelective(BopsGoodsCategoriesPO record);

    int updateByPrimaryKey(BopsGoodsCategoriesPO record);
    
    BopsGoodsCategoriesPO selectBycategoryName(BopsGoodsCategoriesVO categoriesVO);
    
    List<BopsGoodsCategoriesPO> selectCategoryLevel(BopsGoodsCategoriesPO bopsGoodsCategoriesPO);
    
    List<BopsGoodsCategoriesPO> selectCategoryLevelByParent(BopsGoodsCategoriesPO bopsGoodsCategoriesPO);
    
    List<BopsGoodsCategoriesPO> selectCategoryLevelByLevel(BopsGoodsCategoriesPO bopsGoodsCategoriesPO);
    
    List<BopsGoodsCategoriesVO> selectCategoryList(BopsGoodsCategoriesVO bopsGoodsCategoriesVO);
    
    int selectCategoryListCount(BopsGoodsCategoriesVO bopsGoodsCategoriesVO);
    
    List<BopsGoodsCategoriesVO> selectCategoryLevel(String categoryLevel);
    
    void updateCategory(BopsGoodsCategoriesVO bopsGoodsCategoriesVO);
    
    List<BopsGoodsCategoriesVO> selectPreLevel(String parentId);
}