package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
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
 * <p></p>
 * @author xiehao 2015年7月27日 下午3:41:56
 * @ClassName OperateUserTokenController
 * @Description TODO 用户注销成功之后删除缓存里的UserToken
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年7月27日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.USER_OPERATE_USER_TOKEN)
public class OperateUserTokenController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message operateUserToken(
			@RequestParam(required = true)String userId){
		Message message = Message.success();
		JedisSentinelClient.del(userId);	
		return message;
		
	}
	
}
