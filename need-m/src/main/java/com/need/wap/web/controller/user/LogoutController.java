package com.need.wap.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.domain.pub.Message;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.utils.StringUtil;
import com.need.wap.constant.ControllerMappings;

/**
 * 
 * <p></p>
 * @author peiboli 2015年12月17日 下午7:03:58
 * @ClassName LogoutController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年12月17日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
public class LogoutController {

	private static final Logger logger = Logger.getLogger(LogoutController.class);

	@ResponseBody
	@RequestMapping(value=ControllerMappings.USER_OPERATE_USER_TOKEN,method=RequestMethod.POST)
	public Message logout(HttpServletRequest request){
		logger.info("logout.....in");
		
		Message message=Message.success();
		String userToken = request.getHeader("User-Token");
		String userAreaId= request.getHeader("User-AreaId");
		if(!StringUtil.isBlank(userToken)){
			String userId=null;
			try {
				userId = BizCodeUtil.getUserIdByToken(userToken);
			} catch (Exception e) {
				return message;
			}
		JedisSentinelClient.del(RedisKeyConstant.WAP_TOKEN_OF_USER.concat(userId+"_"+userAreaId));
		return message;
	}
		return message;
	}
}
