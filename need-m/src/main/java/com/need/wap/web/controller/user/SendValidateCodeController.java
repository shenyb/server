package com.need.wap.web.controller.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
import com.need.utils.StringUtil;
import com.need.wap.constant.ControllerMappings;




/**
 * 
 * <p>
 * </p>
 * 
 * @author peiboli 2015年7月27日 下午5:26:05
 * @ClassName SendValidateCodeController
 * @Description M站登录时发送验证码
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年7月27日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.SEND_VALIDATION_CODE)
public class SendValidateCodeController {

	private static final Logger logger = Logger.getLogger(SendValidateCodeController.class);
    @Autowired 
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/{mobile}", method = RequestMethod.POST)
	public Message sendValidateCode(@PathVariable(value="mobile") String mobile) throws Exception {
		logger.info("sendValidateCode in ..mobile:"+mobile);
		Message message = Message.success();
		if(StringUtil.isBlank(mobile)){
			   return Message.error(BizReturnCode.MOBILE_NOT_NOT_EXIST);
			}
			int result = userService.sendRegistValidateCode(mobile);
			if (0 == result) {
			   return message;
			} else {
				return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
			}
			
		
	}

}
