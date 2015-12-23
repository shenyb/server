/**
 * @ProjectName: need-api
 * @Copyright:
 * @address:
 * @Description:
 * @author shenyb
 * @date: 2015年7月25日 上午12:08:49
 * @Title: UserRegisterController.java
 * @Package com.need.api.web.controller.user
 * @Description: TODO
 */
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

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年7月25日 上午10:48:19
 * @ClassName RegisterCheckNickNameController
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_CHECK_NICK_NAME_EXISTS)
public class CheckNickNameController {
	
	private static final Logger logger = Logger.getLogger(CheckNickNameController.class);
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message userCheckNickNameExists(@RequestParam(required = true) String nickName) {
		logger.info("userCheckNickName...in..nickName:"+nickName);
		Message success = Message.success();
		if (StringUtil.isBlank(nickName)) {
			
			return Message.error(BizReturnCode.NICKNAME_NOT_EXIST);
		}
		if (userService.checkNickNameExists(nickName)) {
			
			return Message.error(BizReturnCode.NICKNAME_ALREADY_EXISTS);
		}
		logger.info("userCheckNickName...out..nickName:"+nickName);
		return success;
	}
}
