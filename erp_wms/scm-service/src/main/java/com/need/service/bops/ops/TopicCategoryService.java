package com.need.service.bops.ops;
import java.util.List;

import com.need.domain.po.bops.ops.BopsTopicCategory;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.TopicCategroyVO;

public interface TopicCategoryService {

	Message addCategory(TopicCategroyVO bopsTopicCategory,Message message);

	BopsTopicCategory getBopsOpsById(int relationId);

	List<BopsTopicCategory> queryBopsTopicCategoryBypage(TopicCategroyVO topicCategroyVO);

	Message editTopicCategory(BopsTopicCategory bopsTopicCategory, Message message);

	void removeTopicCategory(int relationId);

	BopsTopicCategory getRelationByTopicId(int topicId);

}
