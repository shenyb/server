package com.need.dao.bops.ops;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.ops.BopsOpsTopicCategory;
import com.need.domain.pub.Page;

public interface BopsOpsTopicCategoryDAO {
    /**
     * 
     * @author peiboli 2015年9月10日 上午10:58:50
     * @Method: insert 
     * @Description: TODO新增专题分类
     * @param topicCategory 
     * @throws
     */
	void insert(BopsOpsTopicCategory topicCategory);
    /**
     * 
     * @author peiboli 2015年9月10日 上午10:58:35
     * @Method: selectCategoryName 
     * @Description: TODO验证专题名称是否存在
     * @param topicCategoryName
     * @return 
     * @throws
     */
	int selectCategoryName(String topicCategoryName);
    /**
     * 
     * @author peiboli 2015年9月10日 上午10:58:16
     * @Method: queryByPage 
     * @Description: TODO获取专题分类列表
     * @param categoryPage
     * @return 
     * @throws
     */
	List<BopsOpsTopicCategory> queryByPage(Page categoryPage);
    /**
     * 
     * @author peiboli 2015年9月10日 上午10:58:03
     * @Method: count 
     * @Description: TODO获得总的条数
     * @return 
     * @throws
     */
	int count();
    
	/**
	 * 
	 * @author peiboli 2015年9月10日 上午10:59:11
	 * @Method: deleteById 
	 * @Description: TODO根据id删除分类
	 * @param topicCategoryId
	 * @return 
	 * @throws
	 */
	int deleteById(@Param("topicCategoryId") int topicCategoryId);
	/**
	 * 
	 * @author peiboli 2015年9月10日 下午12:15:32
	 * @Method: update 
	 * @Description: TODO更新
	 * @param topicCategory 
	 * @throws
	 */
	void update(BopsOpsTopicCategory topicCategory);
	/***
	 * 
	 * @author LXD 2015年9月16日 下午5:03:30
	 * @Method: getById 
	 * @Description: TODO 根据ID查询专题分类
	 * @param topicCategoryId
	 * @return 
	 * @throws
	 */
	BopsOpsTopicCategory getById(Integer topicCategoryId);

	
}
