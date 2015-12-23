package com.need.service.bops.ops;

import java.util.List;

import com.need.domain.po.bops.ops.BopsOpsTopic;
import com.need.domain.vo.ops.PageTopicVO;

public interface OpsTopicService {


	BopsOpsTopic addOpsTopic(BopsOpsTopic bopsOpsTopic);

	void deleteOpsTopic(String topicId);

	void updateOpsTopic(BopsOpsTopic bopsOpsTopic);

	List<BopsOpsTopic> getOpsTopicList(PageTopicVO opsTopicPage);

	void deleteApiOpsTopic(String topicId);
	
	void updateApiOpsTopic(BopsOpsTopic bopsOpsTopic);

	BopsOpsTopic getTopic(String topicId);

	void auditOpsTopic(BopsOpsTopic bopsOpsTopic);

	
}
