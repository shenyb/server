package com.need.api.web.controller.follow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.follow.FollowService;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;

@Controller
@RequestMapping("follow/synchronization")
public class FollowsynchronizationController {

private static final Logger LOGGER = LoggerFactory.getLogger(AddFollowController.class);
    
    @Autowired
    private UserService userService;
    
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message addFollow(){
		LOGGER.info("addFollow ");
        Message message = userService.followSynchronization();
        return message;
    }
}
