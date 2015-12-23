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
@RequestMapping(value = ControllerMappings.SHIJIAN_TOPIC_RECOMMEND)
public class GetShijianRecommendController {
	private static final Logger logger = Logger.getLogger(GetShijianRecommendController.class);
	@Autowired
	private TopicRecommendService  topicRecommendService;
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getCategoryTopic_v1_1(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize){
		logger.info(String.format("getCategoryTopic_v1_1 in : getCategoryTopic_v1_1 pageNum&&&pageSize: %s", pageNum+"&&&"+pageSize) ); 
		return topicRecommendService.getCategoryTopic_v1_1(pageNum,pageSize);
		
		
	}
	
}
