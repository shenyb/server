package com.need.wap.web.controller.pub;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.domain.po.api.cheap.CheapOpenUserPOKey;
import com.need.common.domain.pub.Message;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.utils.HttpUtils;
import com.need.wap.constant.Constants;
import com.need.wap.constant.ControllerMappings;

/**
 * 
 * <p></p>
 * @author peiboli 2015年12月8日 上午11:27:32
 * @ClassName CheckUserToken
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年12月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
public class CheckUserTokenController {
	
	private static final Logger logger= Logger.getLogger(CheckUserTokenController.class);
	@ResponseBody
	@RequestMapping(value=ControllerMappings.CHECK_WAP_TOKEN_ISEFFECTIVE,method = RequestMethod.POST)
	public Message checkUserToken(HttpServletRequest request){
		Message message= Message.success();
		String userToken = request.getHeader("token");
		
		if (userToken == null) {
			//logger.error("the user userToken is null，return false!"); 
			logger.warn("the user token is null，return false!");
			message.addData("isEffective", Boolean.FALSE.toString().toUpperCase());
			return message;
		}
		String userId=null;
		try {
			userId = BizCodeUtil.getUserIdByToken(userToken);
		} catch (Exception e) {
			/** TODO Auto-generated catch block */
			logger.info("token失效");
			message.addData("isEffective", Boolean.FALSE.toString().toUpperCase());
			return message;
            
		}
		// 从缓存中取得用户信息,并使用最新的token 
				String userTokenRedis = JedisSentinelClient.getString(RedisKeyConstant.WAP_TOKEN_OF_USER.concat(userId));
				if(userToken.equals(userTokenRedis)){
					message.addData("isEffective", Boolean.TRUE.toString().toUpperCase());
					return message;
				}else{
					message.addData("isEffective", Boolean.FALSE.toString().toUpperCase());
					return message;
					
				}
		
	}

}
