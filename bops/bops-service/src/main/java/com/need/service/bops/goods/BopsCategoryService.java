package com.need.service.bops.goods;



import java.util.List;

import com.need.domain.vo.goods.BopsGoodsCategoriesVO;

public interface BopsCategoryService {
	public BopsGoodsCategoriesVO insertNewCategory(BopsGoodsCategoriesVO bopsGoodsCategoriesVO);
	
	public BopsGoodsCategoriesVO getCategoryDetail(String categoryId);
	
	public BopsGoodsCategoriesVO toUpdateCategory(String categoryId);
	
	public BopsGoodsCategoriesVO updateCategory(BopsGoodsCategoriesVO bopsGoodsCategoriesVO);
	
	List<BopsGoodsCategoriesVO> selectCategoryList(BopsGoodsCategoriesVO bopsGoodsCategoriesVO);


}
