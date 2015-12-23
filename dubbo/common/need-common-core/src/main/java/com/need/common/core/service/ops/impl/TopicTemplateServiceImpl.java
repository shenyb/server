package com.need.common.core.service.ops.impl;

import com.need.common.core.dao.jdbc.ops.OpsTopicTemplateDAO;
import com.need.common.core.service.ops.TopicTemplateService;
import com.need.common.domain.po.api.ops.OpsTopicTemplatePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicTemplateServiceImpl implements TopicTemplateService   {
    
	@Autowired
	private OpsTopicTemplateDAO OpsTopicTemplateDAO;
	
	@Override
	public OpsTopicTemplatePO getTopicTempl(int topicId) {
	
		return OpsTopicTemplateDAO.selectByPrimaryKey(topicId);
	}
	
}
