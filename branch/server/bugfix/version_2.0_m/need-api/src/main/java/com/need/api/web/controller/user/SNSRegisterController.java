package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p></p>
 * @author peiboli 2015年11月26日 上午10:33:21
 * @ClassName SNSRegisterController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年11月26日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_LOGIN_BOUND_MOBILE)
public class SNSRegisterController {
	private static final Logger logger= Logger.getLogger(SNSRegisterController.class);
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @author peiboli 2015年11月26日 下午3:06:48
	 * @Method: SNSRegister 
	 * @Description: TODO用户第一次三方登录，绑定手机号
	 * @param snsId
	 * @param snsType
	 * @param mobile
	 * @param channel
	 * @param validateCode
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message SNSRegister(String snsId,String snsType,String mobile,String channel,String validateCode){
		logger.info("SNSRegister...in..params:"+snsId+";"+snsType+";"+mobile+";"+channel+";"+validateCode);
		Message message=userService.boundMobileAndGetLoginInfo(snsId,snsType,mobile,channel,validateCode);
		logger.info("SNSRegister...out..params:"+snsId+";"+snsType+";"+mobile+";"+channel+";"+validateCode);
		return message;
	}

}
