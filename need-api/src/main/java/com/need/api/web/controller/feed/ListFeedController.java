/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.api.web.controller.feed;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.feed.FeedService;
import com.need.common.domain.pub.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author 庆凯
 */
@Controller
@RequestMapping(ControllerMappings.FEED_LIST)
public class ListFeedController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(ListFeedController.class);
    
    @Autowired
    private FeedService feedService;
    
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
	public Message listFeed(@RequestParam(required = false) String userId, @RequestParam(required = false) String feedId, 
            @RequestParam(required = true) String refreshType, @RequestParam(required = false) Integer pageSize){
		LOGGER.info("listFeed userId : {} feedId : {} refreshType : {} pageSize : {}", userId, feedId, refreshType, pageSize);
        Message message = feedService.listFeed(userId, feedId, refreshType, pageSize);
        return message;
    }
}
