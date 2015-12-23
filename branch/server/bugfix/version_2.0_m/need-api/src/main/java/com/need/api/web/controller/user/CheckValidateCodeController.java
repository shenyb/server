package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.domain.pub.Message;
import com.need.jedis.JedisSentinelClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p>
 * </p>
 * @author shenyb 2015年7月25日 上午10:22:12
 * @ClassName RegisterValidateCodeController
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_CHECK_VALIDATE_CODE)
public class CheckValidateCodeController {
	
	private static final Logger logger = Logger.getLogger(CheckValidateCodeController.class);
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message CheckvalidateCode(
	        @RequestParam(required = true) String mobile,
	        @RequestParam(required = true) String validateCode) {
		logger.info(String.format("CheckvalidateCode...in...params:%s %s", mobile,validateCode));
		Message success = Message.success();
		Object code = JedisSentinelClient.getString(mobile);
		if (code != null && code.equals(validateCode)) {//验证码正确
			JedisSentinelClient.del(mobile);	
			success.addData("isRight", "true");
			return success;
		} 		
		logger.info(String.format("CheckvalidateCode...out...params:%s %s", mobile,validateCode));
		return Message.error(BizReturnCode.CODE_ERROR);
	}
}
