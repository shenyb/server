package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(value = ControllerMappings.USERINFO_GET_DISTRIBUTION)
public class GetDistributionUserInfoController {

	private static final Logger logger = Logger.getLogger(GetDistributionUserInfoController.class);

	@Autowired
	private UserService userService;

    

    @ResponseBody
    @RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
    public Message getUserInfo(@RequestParam(required = false) String userId) {
        logger.info(String.format("getUserInfo...in...param:userId %s",userId));
        if(StringUtil.isBlank(userId)) {
            return Message.error(BizReturnCode.USERID_NOT_EXIST);
        }
        Message success  = userService.updateUserSut(userId);
        
        
        
    	return success;
    }

}
