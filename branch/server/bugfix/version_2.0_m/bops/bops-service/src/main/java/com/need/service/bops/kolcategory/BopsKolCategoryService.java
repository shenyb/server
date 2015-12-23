package com.need.service.bops.kolcategory;

import java.util.List;

import com.need.domain.po.bops.basedata.BopsKolCategory;
import com.need.domain.pub.Page;

public interface BopsKolCategoryService {

	/**
	 * 
	 * @author peiboli 2015年8月1日 上午11:12:07
	 * @Method: addBopsKolCategory 
	 * @Description: TODO
	 * @param bopsKolCategory
	 * @return 
	 * @throws
	 */
	public BopsKolCategory addBopsKolCategory(BopsKolCategory bopsKolCategory);

	/**
	 * 
	 * @author peiboli 2015年9月24日 下午3:42:09
	 * @Method: getKolCategoryList 
	 * @Description: TODO获得行家列表
	 * @param search
	 * @param kolcatPage
	 * @return 
	 * @throws
	 */
	public List<BopsKolCategory> getKolCategoryList(String search,Page kolcatPage);
	
	/**
	 * 
	 * @author peiboli 2015年10月15日 下午3:06:11
	 * @Method: getKolCategoryList 
	 * @Description: TODO查询所有行家
	 * @return 
	 * @throws
	 */
	public List<BopsKolCategory> getKolCategoryList();
    /**
     * 
     * @author peiboli 2015年9月24日 下午3:42:33
     * @Method: deleteBopsKolCategory 
     * @Description: TODO删除
     * @param categoryId
     * @return 
     * @throws
     */
	public int deleteBopsKolCategory(String categoryId);
    /**
     * 
     * @author peiboli 2015年9月24日 下午3:42:41
     * @Method: updateBopsKolCategory 
     * @Description: TODO更新
     * @param bopsKolCategory
     * @return 
     * @throws
     */
	public int updateBopsKolCategory(BopsKolCategory bopsKolCategory);
    /**
     * 
     * @author peiboli 2015年9月24日 下午3:42:54
     * @Method: getKolCategory 
     * @Description: TODO根据名称查询
     * @param categoryName
     * @return 
     * @throws
     */
	public BopsKolCategory getKolCategory(String categoryName);

//	public BopsKolCategory selectBopsKolCategoryByCatgoryId(String categoryId);
	
	/**
	 * @author Rylan 2015年8月9日 上午12:21:16
	 * @Method: selectByIds 
	 * @Description: TODO 查询多个纪录
	 * @param categoryIds
	 * @return 
	 * @throws
	 */
	public List<BopsKolCategory>  selectByIds( String categoryIds);

}
