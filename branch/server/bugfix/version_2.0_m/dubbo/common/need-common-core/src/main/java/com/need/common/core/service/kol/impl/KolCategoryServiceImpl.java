package com.need.common.core.service.kol.impl;

import com.need.common.core.dao.jdbc.kol.KolCategoryPODAO;
import com.need.common.core.service.kol.KolCategoryService;
import com.need.common.domain.po.api.kol.KolCategoryPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KolCategoryServiceImpl implements KolCategoryService{
	
	@Autowired
	KolCategoryPODAO kolCategoryPODAO;

	@Override
	public List<KolCategoryPO> kolCategoryList() {
		// TODO Auto-generated method stub
		List<KolCategoryPO> kolCategoryList = kolCategoryPODAO.queryKolcategory();
		return kolCategoryList;
	}

}
