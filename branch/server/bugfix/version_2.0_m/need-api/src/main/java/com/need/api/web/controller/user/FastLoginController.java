package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.enums.RegisterTypeEnum;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.LoginVO;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = ControllerMappings.USER_FAST_LOGIN)
public class FastLoginController {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message fastLogin(LoginVO params) {
           logger.info("fastLogin ...in..params:"+params.toString());
		
		if (StringUtil.isBlank(params.getMobile())) {
			return Message.error(BizReturnCode.MOBILE_NOT_NOT_EXIST);
		} 
		if(StringUtil.isBlank(params.getValidateCode())){
			return Message.error(BizReturnCode.CODE_NOT_EXIST);
		}
		if(StringUtil.isBlank(params.getChannel())){
			return Message.error(BizReturnCode.REG_CHANNEL_NOT_EXIST);
		}
		params.setType(RegisterTypeEnum.APPFAST.code);
		Message success = userService.insertAndGetUserInfoByCode(params);
		return success;
		
	}
	
}
