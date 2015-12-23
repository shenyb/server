package com.need.dao.bops.basedata;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.basedata.BopsKolCategory;

/**
 * 
 * <p></p>
 * @author peiboli 2015年8月3日 上午9:39:01
 * @ClassName BopsKolCategoryDAO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月3日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsKolCategoryDAO {
    int removeByCategoryId(String categoryId);
    /**
     * 
     * @author peiboli 2015年8月3日 上午9:38:37
     * @Method: insert 
     * @Description: TODO新增行家分类
     * @param cat
     * @return 
     * @throws
     */
    int insert( BopsKolCategory bopsKolCategory);

    /**
     * 
     * @author peiboli 2015年9月24日 下午3:55:08
     * @Method: updateByCategoryId 
     * @Description: TODO更新
     * @param record
     * @return 
     * @throws
     */
    int updateByCategoryId(BopsKolCategory record);

    /**
     * 
     * @author peiboli 2015年8月3日 上午9:39:07
     * @Method: select 
     * @Description: TODO获得行家列表
     * @return 
     * @throws
     */
	List<BopsKolCategory> queryByPage(Map<String,Object> paramMap);
	
	int selectCount(@Param("search") String search);
  
   
	/**
	 * 
	 * @author peiboli 2015年8月4日 上午10:50:56
	 * @Method: selectByCatgoryName 
	 * @Description: TODO查询一条数据
	 * @param categoryName
	 * @return 
	 * @throws
	 */
    BopsKolCategory selectByCatgoryName(String categoryName);
    
    /**
     * @author Rylan 2015年8月9日 上午12:19:43
     * @Method: selectByIds 
     * @Description: TODO 根据id列表查询到多个数据
     * @param categoryIds
     * @return 
     * @throws
     */
    List<BopsKolCategory>  selectByIds(@Param("categoryIds") String categoryIds);
    /**
     * 
     * @author peiboli 2015年10月15日 下午3:19:50
     * @Method: queryKolCategory 
     * @Description: TODO获得所有行家分类
     * @return 
     * @throws
     */
	List<BopsKolCategory> queryKolCategory();
    
    
}