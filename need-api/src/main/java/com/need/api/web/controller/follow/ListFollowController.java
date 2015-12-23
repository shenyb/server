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
@RequestMapping(ControllerMappings.USER_FOLLOW_LIST)
public class ListFollowController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(ListFollowController.class);
    
    @Autowired
    private FollowService followService;
    
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
	public Message listFollow(
            @RequestParam(required = false) String userId, 
            @RequestParam(required = true) String targetId, 
            @RequestParam(required = true) String followType, 
            @RequestParam(required = true) String refreshType, 
            @RequestParam(required = false) String followerId, 
            @RequestParam(required = false) Integer pageSize){
		LOGGER.info("listFollow userId : {}, targetId : {}, followType : {}, refreshType : {}, followerId : {}, pageSize : {}", 
                userId, targetId, followType, refreshType, followerId, pageSize);
        Message message = followService.listFollow(userId, targetId, followType, refreshType, followerId, pageSize);
        return message;
    }
}
