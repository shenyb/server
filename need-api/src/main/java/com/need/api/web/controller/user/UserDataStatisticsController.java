package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.UserDataStatisticsVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = ControllerMappings.USER_DATA_STATISTICS)
public class UserDataStatisticsController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message userDataStatistics(
			@RequestParam(required = true)String userId
			){
		Message message = Message.success();
		UserDataStatisticsVO userVO = new UserDataStatisticsVO();
		userVO.setUserFans(1);
		userVO.setUserFocus(1);
		userVO.setUserOrder(1);
		message.addData("user_extend_info", userVO);
		
		return message;
	}
	
}
