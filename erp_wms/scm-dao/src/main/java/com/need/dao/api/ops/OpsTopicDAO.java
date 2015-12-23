package com.need.dao.api.ops;

import com.need.domain.po.api.ops.OpsTopicPO;
import com.need.domain.po.bops.ops.BopsOpsTopic;

public interface OpsTopicDAO {

	void insertTopic(BopsOpsTopic topic);

	void deleteApiTopic(String topicId);

	void updateByPrimaryKeySelective(BopsOpsTopic bopsOpsTopic);
	
	OpsTopicPO getById(int topicId);
  
}