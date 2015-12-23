package com.need.service.bops.kolcategory.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.kol.KolCategoryDAO;
import com.need.dao.bops.basedata.BopsKolCategoryDAO;
import com.need.domain.po.bops.basedata.BopsKolCategory;
import com.need.domain.po.bops.xinhuan.BopsOpsMain;
import com.need.domain.pub.Page;
import com.need.service.bops.kolcategory.BopsKolCategoryService;
@Service
public class BopsKolCategoryServiceImpl  implements BopsKolCategoryService{
	@Autowired
	private BopsKolCategoryDAO bopsKolCategoryDAO;
	
	@Autowired
	private KolCategoryDAO kolCategoryDAO;

	/**
	 * 
	 * @author peiboli 2015年9月24日 下午3:43:48
	 * @Method: addBopsKolCategory 
	 * @Description: TODO添加行家
	 * @param bopsKolCategory
	 * @return 
	 * @see com.need.bops.service.kolcategory.BopsKolCategoryService#addBopsKolCategory(com.need.bops.dao.jdbc.bops.basedata.po.BopsKolCategory)
	 */
	@Override
	@Transactional
	public BopsKolCategory addBopsKolCategory(BopsKolCategory bopsKolCategory) {
		// TODO Auto-generated method stub
		bopsKolCategoryDAO.insert(bopsKolCategory);
		kolCategoryDAO.insert(bopsKolCategory);//同步到api
		bopsKolCategory.setCreateTime(Calendar.getInstance().getTime());
		return bopsKolCategory;
	}

	/**
	 * 
	 * @author peiboli 2015年8月12日 下午11:32:59
	 * @Method: getKolCategoryList 
	 * @Description: TODO获得行家列表
	 * @param search
	 * @param kolcatPage
	 * @return 
	 * @see com.need.bops.service.kolcategory.BopsKolCategoryService#getKolCategoryList(java.lang.String, com.need.bops.pub.Page)
	 */
	@Override
	public List<BopsKolCategory> getKolCategoryList(String search,Page kolcatPage) {
		// TODO Auto-generated method stub
		kolcatPage.setTotal(bopsKolCategoryDAO.selectCount(search));
		Map<String,Object> paramMap = new HashMap<String,Object>(); 
		paramMap.put("search", search);
		paramMap.put("start", kolcatPage.getStart());
		paramMap.put("pagesize", kolcatPage.getPageSize());
		List<BopsKolCategory> kolCategoryList = bopsKolCategoryDAO.queryByPage(paramMap);

		return kolCategoryList;
	}
    /**
     * 
     * @author peiboli 2015年9月24日 下午3:44:08
     * @Method: deleteBopsKolCategory 
     * @Description: TODO删除行家
     * @param categoryId
     * @return 
     * @see com.need.bops.service.kolcategory.BopsKolCategoryService#deleteBopsKolCategory(java.lang.String)
     */
	@Override
	@Transactional
	public int deleteBopsKolCategory(String categoryId) {
		// TODO Auto-generated method stub
//		Integer catId =Integer.valueOf(categoryId);
		int code = bopsKolCategoryDAO.removeByCategoryId(categoryId);
		kolCategoryDAO.removeByCategoryId(categoryId);
		return code;
	}

	/**
	 * 
	 * @author peiboli 2015年9月24日 下午3:52:20
	 * @Method: updateBopsKolCategory 
	 * @Description: TODO更新行家信息
	 * @param bopsKolCategory
	 * @return 
	 * @see com.need.bops.service.kolcategory.BopsKolCategoryService#updateBopsKolCategory(com.need.bops.dao.jdbc.bops.basedata.po.BopsKolCategory)
	 */
	@Override
	@Transactional
	public int updateBopsKolCategory(BopsKolCategory bopsKolCategory) {
		// TODO Auto-generated method stub
		
		int code = bopsKolCategoryDAO.updateByCategoryId(bopsKolCategory);
		    kolCategoryDAO.updateByCategoryId(bopsKolCategory);
		
		return code;
	}

	/**
	 * 
	 * @author peiboli 2015年9月24日 下午3:52:33
	 * @Method: getKolCategory 
	 * @Description: TODO查看行家信息
	 * @param categoryName
	 * @return 
	 * @see com.need.bops.service.kolcategory.BopsKolCategoryService#getKolCategory(java.lang.String)
	 */
	@Override
	public BopsKolCategory getKolCategory(String categoryName) {
		// TODO Auto-generated method stub
		BopsKolCategory bopsKolCategory = bopsKolCategoryDAO.selectByCatgoryName(categoryName);
		return bopsKolCategory;
	}


	/**
	 * 
	 * @author peiboli 2015年9月24日 下午3:52:55
	 * @Method: selectByIds 
	 * @Description: TODO根据id获得行家列表
	 * @param categoryIds
	 * @return 
	 * @see com.need.bops.service.kolcategory.BopsKolCategoryService#selectByIds(java.lang.String)
	 */
	@Override
	public List<BopsKolCategory> selectByIds(String categoryIds) {
		// TODO Auto-generated method stub
		return bopsKolCategoryDAO.selectByIds(categoryIds);
	}

	@Override
	public List<BopsKolCategory> getKolCategoryList() {
		// TODO Auto-generated method stub
		List<BopsKolCategory> KolCategoryList=bopsKolCategoryDAO.queryKolCategory();
		return KolCategoryList;
	}


}
