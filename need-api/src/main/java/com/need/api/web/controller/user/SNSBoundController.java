/**
 * @ProjectName: need-api
 * @Copyright: 2015 by Beijin Weplanter Technology co.,ltd.
 * @address: http://www.weplanter.com
 * @Description: 本内容仅限于稻田(北京)科技有限公司内部使用，禁止转发.
 * @author peiboli
 * @date: 2015年11月26日 下午2:57:36
 * @Title: BoundOpenAuthController.java
 * @Package com.need.api.web.controller.user
 * @Description: TODO
 */
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
 * <p></p>
 * @author peiboli 2015年11月26日 下午2:57:36
 * @ClassName BoundOpenAuthController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年11月26日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_SNS_BINDING)
public class SNSBoundController {
	private static final Logger logger= Logger.getLogger(SNSRegisterController.class);
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @author peiboli 2015年11月26日 下午3:07:39
	 * @Method: SNSRegister 
	 * @Description: TODO用户登录后，在app内进行绑定第三方账号。
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
	public Message SNSRegister(String snsId,String snsType,String userId){
		logger.info("SNSRegister...in..params:"+snsId+";"+snsType+";"+userId);
		Message message=userService.boundOpenAuth(snsId,snsType,userId);
		logger.info("SNSRegister...out..params:"+snsId+";"+snsType+";"+userId);
		return message;
	};
}
