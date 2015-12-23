package com.need.common.core.service.need;

import com.need.common.domain.pub.Message;

public interface NeedFeedService {

	Message getHomeFeeds(String userId, Integer pageNum, Integer pageSize);

}
