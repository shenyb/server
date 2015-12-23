package com.need.dao.bops.ops;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.ops.BopsOpsTopicCategory;
import com.need.domain.po.bops.ops.BopsTopicCategory;
import com.need.domain.vo.ops.TopicCategroyVO;

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
public interface BopsTopicCategoryDAO {
     /**
      * 
      * @author LXD 2015年9月10日 上午11:44:20
      * @Method: insert 
      * @Description: TODO need1.1 插入专题和专题分类的关联
      * @param bopsTopicCategory 
      * @throws
      */
	 void insert(BopsTopicCategory bopsTopicCategory);

	BopsTopicCategory getByParams(@Param("topicId")int topicId, @Param("topicCategoryId")int topicCategoryId);

	BopsTopicCategory getById(int relationId);

	Integer getCountByparams(TopicCategroyVO topicCategroyVO);

	List<BopsTopicCategory> queryByPage(TopicCategroyVO topicCategroyVO);

	void update(BopsTopicCategory bopsTopicCategory);

	void delete(int relationId);

	BopsOpsTopicCategory getByTopicId(Integer topicId);

	BopsTopicCategory getRelationByTopicId(int topicId);

	void deleteByTopicId(int id);
	
	int getCountByCategoryId(int topicCategoryId);

}
