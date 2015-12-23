package com.need.dao.api.ops;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.ops.BopsOpsTopicCategory;

public interface OpsTopicCategoryDAO {

	void insert(BopsOpsTopicCategory topicCategory);

	int selectCategoryName(@Param("topicCategoryName")String topicCategoryName);

	void deleteById(@Param("topicCategoryId") int topicCategoryId);

	void update(BopsOpsTopicCategory topicCategory);

	int countById(int topicCategoryId);

}
