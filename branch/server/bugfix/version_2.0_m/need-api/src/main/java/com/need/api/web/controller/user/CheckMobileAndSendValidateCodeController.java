package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
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
 * @author xiehao 2015年7月29日 下午2:17:40
 * @ClassName CheckMobileAndSendValidateCodeController
 * @Description TODO 用户找回密码时发送验证码
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年7月29日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.USER_CHECK_MOBILE_AND_SEND_VALIDATE_CODE)
public class CheckMobileAndSendValidateCodeController {

	private static final Logger logger = Logger.getLogger(CheckMobileAndSendValidateCodeController.class);
	
	@Autowired
	private UserService userService;
	
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message CheckMobileAndSendValidateCode(
			@RequestParam String mobile
			) throws Exception{
		logger.info(String.format("CheckMobileAndSendValidateCode...in....param:mobile:%s", mobile));
		Message message = Message.success();
		int mobileCount = userService.checkMobileIsExist(mobile);
		if(mobileCount != 1){	//首先看这个手机存不存在
			return Message.error(BizReturnCode.MOBILE_NOT_EXIST);
		}
		else{	//如果手机存在
			int result = userService.sendGeneralValidateCode(mobile);
			
			if(result == 0){
				logger.info(String.format("CheckMobileAndSendValidateCode...out....param:mobile:%s", mobile));
				return message;
			}
			else{
				return Message.error(BizReturnCode.SYSTEM_SEND_VALIDATE_CODE_ERR);
			}
		}
		
	}
}
