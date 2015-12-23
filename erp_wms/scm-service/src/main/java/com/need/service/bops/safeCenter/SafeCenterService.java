package com.need.service.bops.safeCenter;



import com.need.domain.pub.Message;
import com.need.domain.vo.safeCenter.NeedFeedParamsVO;

public interface SafeCenterService {

	Message queryFeedsPage(NeedFeedParamsVO needFeedParamsVO);

	Message deleteFeed(String feedId);

}
