package com.need.dao.api.kol;

import com.need.domain.po.bops.basedata.BopsKolCategory;

public interface KolCategoryDAO {

	void insert(BopsKolCategory kolCategory);

	void removeByCategoryId(String parseInt);

	void updateByCategoryId(BopsKolCategory bopsKolCategory);
	
	//添加時對行家名称的检测
	int selectCategoryName(String categoryName);
	
	//更新行家分類是对名称的检验
	String selectCategoryNameByCategoryId(Integer categoryId);
   
}