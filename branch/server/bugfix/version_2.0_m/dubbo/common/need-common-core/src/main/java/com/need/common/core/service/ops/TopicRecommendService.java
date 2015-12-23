package com.need.common.core.service.ops;

import com.need.common.domain.pub.Message;

public interface TopicRecommendService {

	Message getCategoryTopic_v1_1(Integer pageNum, Integer pageSize);

	Message getMoreTopic_v1_1( Integer categoryId,Integer pageNum, Integer pageSize);

}
