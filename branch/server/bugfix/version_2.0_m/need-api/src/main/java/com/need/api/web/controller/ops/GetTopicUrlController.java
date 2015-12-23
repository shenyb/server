package com.need.api.web.controller.ops;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.ops.TopicTemplateService;
import com.need.common.domain.po.api.ops.OpsTopicTemplatePO;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = ControllerMappings.TOPIC_URL)
public class GetTopicUrlController {
	private static final Logger logger = Logger.getLogger(GetTopicUrlController.class);
	@Autowired
	private TopicTemplateService topicTemplateService;
	
	/**
	 * 
	 * @author LXD 2015年9月15日 下午2:51:32
	 * @Method: getTopicUrl 
	 * @Description: TODO 根据ID获取TopicURL
	 * @param topicId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getTopicUrl(@RequestParam(required = true)int topicId){
		logger.info(String.format("getTopicUrl: topicId: %s", topicId) );
		Message success =Message.success();
		OpsTopicTemplatePO TopicTemplatePO= topicTemplateService.getTopicTempl(topicId);
		if(TopicTemplatePO!=null){
			success.addData("topicUrl", TopicTemplatePO.getVisitUrl());
			success.addData("topicName", TopicTemplatePO.getTopicTitle());	
		}
		return success;
		
		
	}
	
	
}
