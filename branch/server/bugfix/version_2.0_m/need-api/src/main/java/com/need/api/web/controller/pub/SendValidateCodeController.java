package com.need.api.web.controller.pub;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
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
 * @author peiboli 2015年7月27日 下午5:26:05
 * @ClassName SendValidateCodeController
 * @Description 注册时发送验证码
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
	private UserService userService;

	@ParamsValidate(value={
			@ParamValidate(name="mobile",notNull=true,code=BizReturnCode.MOBILE_NOT_NOT_EXIST),
			@ParamValidate(name="mobile",regex="^1+[3|5|7|8]+\\d{9}$",code=BizReturnCode.PLEASE_ENTER_VALID_MOBILE)
			})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message sendValidateCode(@RequestParam(required = true) String mobile) throws Exception {
		logger.info("sendValidateCode in ..mobile:"+mobile);
		Message message = Message.success();
		int mobileCount = userService.checkMobileIsExist(mobile);
		if(mobileCount == 1){
			return Message.error(BizReturnCode.MOBILE_ALREADY_EXISTS);
		
		}else{	
			int result = userService.sendRegistValidateCode(mobile);
			if (0 == result) {
			   return message;
			} else {
				return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
			}
		}	
		
	}

}
