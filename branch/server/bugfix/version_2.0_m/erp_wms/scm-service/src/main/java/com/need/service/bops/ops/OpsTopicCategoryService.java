package com.need.service.bops.ops;

import com.need.domain.po.bops.ops.BopsOpsTopicCategory;
import com.need.domain.pub.Message;

public interface OpsTopicCategoryService {

	BopsOpsTopicCategory addTopicCategory(BopsOpsTopicCategory topicCategory);

	Boolean removeById(int topicCategoryId);

	void update(BopsOpsTopicCategory topicCategory);

	Message auditOpsTopic(BopsOpsTopicCategory topicCategory);

}
