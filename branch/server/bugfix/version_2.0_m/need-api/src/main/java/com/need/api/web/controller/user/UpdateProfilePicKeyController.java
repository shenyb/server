package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p></p>
 * @author peiboli 2015年8月16日 下午4:26:18
 * @ClassName UpdateProfilePicKeyController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月16日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_UPDATE_PROFILE_KEY)
public class UpdateProfilePicKeyController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message userUpdateNickName(
	        @RequestParam(required = true) String userId,
	        @RequestParam(required = true) String newProfileKey) {
		logger.info("userUpdateNickName.....");
		Message success = Message.success();				
		userService.updateProfileKey(userId, newProfileKey);
		return success;
	}
	
}
