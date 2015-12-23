package com.need.service.bops.ops;

import com.need.domain.vo.ops.BopsIndexCategoryParam;

public interface OpsCategoryService {

	void insertNewCategory(BopsIndexCategoryParam categoriesVO);

	BopsIndexCategoryParam getBycategoryId(String categoryId);

	BopsIndexCategoryParam toUpdateCategory(String categoryId);

	void updateCategory(BopsIndexCategoryParam categoriesVO);

}
