package com.need.service.bops.safeCenter;

import com.need.domain.pub.Message;
import com.need.domain.vo.safeCenter.NeedFeedParamsVO;

public interface FeedCommentService {

	Message queryFeedCommentPage(NeedFeedParamsVO needFeedParamsVO);

	Message deleteFeedComment(String commentId);

}
