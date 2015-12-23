package com.need.api.web.controller.ops;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.ops.TopicRecommendService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = ControllerMappings.SHIJIAN_MORE_TOPIC)
public class GetMoreTopicListController {
	private static final Logger logger = Logger.getLogger(GetMoreTopicListController.class);
	@Autowired
	private TopicRecommendService  topicRecommendService;
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getMoreTopic_v1_1(@RequestParam(required = true) Integer categoryId,@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize){
		logger.info(String.format("getMoreTopic_v1_1 in : GetMoreTopicListCont categoryId&&&pageNum&&&pageSize: %s", categoryId+"&&&"+pageNum+"&&&"+pageSize) );
		return topicRecommendService.getMoreTopic_v1_1(categoryId,pageNum,pageSize);
		
		
	}
	
}
