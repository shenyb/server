/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.api.web.controller.feed;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.feed.FeedCommentService;
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
@RequestMapping(ControllerMappings.FEED_COMMENT_DEL)
public class DelFeedCommentController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(DelFeedCommentController.class);
    
    @Autowired
    private FeedCommentService feedCommentService;
    
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message delFeedComment(@RequestParam(required = true) String userId,
            @RequestParam(required = true) String feedId, 
            @RequestParam(required = true) String feedCommentId){
		LOGGER.info("delFeedComment userId : {} feedId : {} feedCommentId : {}", userId, feedId, feedCommentId);
        Message message = feedCommentService.delFeedComment(userId, feedId, feedCommentId);
        return message;
    }
}
