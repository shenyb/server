/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.api.web.controller.follow;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.follow.FollowService;
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
@RequestMapping(ControllerMappings.USER_FOLLOW_DEL)
public class DelFollowController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(DelFollowController.class);
    
    @Autowired
    private FollowService followService;
    
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message delFollow(@RequestParam(required = true) String userId, @RequestParam(required = true) String followerId){
		LOGGER.info("delFollow userId : {} and followerId : {}", userId, followerId);
        Message message = followService.delFollow(userId, followerId);
        return message;
    }
}
