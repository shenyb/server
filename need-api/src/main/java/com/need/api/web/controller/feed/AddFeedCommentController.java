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
@RequestMapping(ControllerMappings.FEED_COMMENT_ADD)
public class AddFeedCommentController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(AddFeedCommentController.class);
    
    @Autowired
    private FeedCommentService feedCommentService;
    
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message addFeedComment(@RequestParam(required = true) String userId, @RequestParam(required = true) String feedId, 
            @RequestParam(required = true) String commentContent, @RequestParam(required = false) String replyId){
		LOGGER.info("addFeedComment userId : {} feedId : {} commentContent : {} replyId : {}", userId, feedId, commentContent, replyId);
        Message message = feedCommentService.addFeedComment(userId, feedId, commentContent, replyId);
        return message;
    }
}
