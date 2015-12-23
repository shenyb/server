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
 * @author peiboli 2015年11月26日 下午3:52:01
 * @ClassName SnsUnbindController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年11月26日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_SNS_UNBIND)
public class SNSUnbindController {
	private static final Logger logger= Logger.getLogger(SNSRegisterController.class);
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message unBound(String snsType,String userId){
		logger.info("unBound...in..params:"+snsType+";"+userId);
		Message message=userService.unBound(snsType,userId);
		logger.info("unBound...out..params:"+snsType+";"+userId);
		return message;
	};
}
