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
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(value = ControllerMappings.USER_LOGIN_SNSLOGIN)
public class SNSLoginController {
	private static final Logger logger= Logger.getLogger(SNSLoginController.class);
	@Autowired
	UserService userService;
	/**
	 * 
	 * @author peiboli 2015年12月5日 下午3:39:57
	 * @Method: SNSLogin 
	 * @Description: TODO第三方登录
	 * @param snsId
	 * @param snsType
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message SNSLogin(String snsId,String snsType){
		logger.info("SNSlogin in .... params:"+snsId+"....."+snsType);
		if(StringUtil.isBlank(snsId)){
			return Message.error(BizReturnCode.SNSID_NOT_EXIST);
		}
		if(StringUtil.isBlank(snsType)){
			return Message.error(BizReturnCode.SNSTYPE_NOT_EXIST);
		}
		Message message= userService.getUserLoginInfo(snsId,snsType);
		return message;
	}

}
