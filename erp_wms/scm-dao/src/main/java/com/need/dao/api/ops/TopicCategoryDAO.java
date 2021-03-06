package com.need.dao.api.ops;

import com.need.domain.po.api.ops.TopicCategory;

/**
 * 
 * <p></p>
 * @author LXD 2015年9月10日 上午10:49:23
 * @ClassName BopsTopicCategoryDAO
 * @Description TODO need1.1 专题和专题类型关联表
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
public interface TopicCategoryDAO {
	/***
	 * 
	 * @author LXD 2015年9月10日 下午12:03:13
	 * @Method: insert 
	 * @Description: TODO 插入专题和专题类型的关系
	 * @param topicCategory
	 * @return 
	 * @throws
	 */
	void insert(TopicCategory topicCategory);
	
	int update(TopicCategory topicCategory);

	TopicCategory getById(int relationId);

	void delete(int relationId);

	void deleteByTopicId(int topicId);
	
	

}
