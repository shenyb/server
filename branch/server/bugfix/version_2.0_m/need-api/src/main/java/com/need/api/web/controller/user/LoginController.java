package com.need.api.web.controller.user;

import com.need.api.constant.Constants;
import com.need.api.constant.ControllerMappings;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.user.UserDeviceRelDAO;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.enums.UserStatusEnum;
import com.need.common.domain.enums.UserTypeEnum;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.po.api.user.UserDeviceRel;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.UserInfoVO;
import com.need.jedis.JedisSentinelClient;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * </p>
 * 
 * @author shenyb 2015年7月25日 上午12:08:49
 * @ClassName LoginController
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_LOGIN)
public class LoginController {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDeviceRelDAO userDeviceRelDAO;
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message userLogin(@RequestParam(required = true) String mobile,
			@RequestParam(required = true) String loginPwd) {
		logger.info("userLogin...in..params:"+mobile+","+loginPwd);
		Message success = Message.success();
		if (StringUtil.isBlank(mobile)) {
			return Message.error(BizReturnCode.MOBILE_NOT_NOT_EXIST);
		}
		if (StringUtil.isBlank(loginPwd)) {
			return Message.error(BizReturnCode.PASSWORD_NOT_EXIST);
		}
		int loginResult = userService.login(mobile, loginPwd);
		if (loginResult == Constants.USER_SUCCESS) {//modify by liyongran 20150812 
			UserBasePO user = userService.getUserByMobile(mobile).get(0);
			if(UserStatusEnum.FREEZE.code.equals(user.getUserStatus())){
				 return Message.error(BizReturnCode.USER_STATUS_FREEZE) ; 
			   }
			String userToken = BizCodeUtil.generateUserToken(user.getUserId());
			UserInfoVO result = new UserInfoVO();
			result.setUserId(user.getUserId());
			result.setUserType(UserTypeEnum.getCode(user.getUserType()));
			result.setUserToken(userToken);
			success.addData("user", result);
			JedisSentinelClient.setString(user.getUserId(),userToken,Constants.APP_USER_TOKEN_DAYS );
		} else {
			return Message.error(loginResult);
		}
		logger.info("userLogin...out..params:"+mobile+","+loginPwd);
		return success;
	}
	
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.POST)
	public Message userLogin_1_3(@RequestParam(required = true) String mobile,@RequestParam(required = true) String loginPwd,HttpServletRequest request) {
		logger.info("userLogin_1.3 ...in..params:"+mobile+","+loginPwd);
		Message success = Message.success();
		if (StringUtil.isBlank(mobile)) {
			return Message.error(BizReturnCode.MOBILE_NOT_NOT_EXIST);
		}
		if (StringUtil.isBlank(loginPwd)) {
			return Message.error(BizReturnCode.PASSWORD_NOT_EXIST);
		}
		int loginResult = userService.login(mobile, loginPwd);
		if (loginResult == Constants.USER_SUCCESS) {//modify by liyongran 20150812 
			UserBasePO user = userService.getUserByMobile(mobile).get(0);
			if(UserStatusEnum.FREEZE.code.equals(user.getUserStatus())){
				 return Message.error(BizReturnCode.USER_STATUS_FREEZE) ; 
			   }
			String userToken = BizCodeUtil.generateUserToken(user.getUserId());
			UserInfoVO result = new UserInfoVO();
			result.setMobile(mobile);
			result.setUserId(user.getUserId());
			result.setUserType(UserTypeEnum.getCode(user.getUserType()));
			result.setUserToken(userToken);
			success.addData("user", result);
			JedisSentinelClient.setString(user.getUserId(),userToken,Constants.APP_USER_TOKEN_DAYS );
			
			String protocolVerStr = request.getHeader("apiVersion");
			float protocolVer = Float.parseFloat(protocolVerStr);// 获取请求版本号
			//只处理1.3版本之后
			if(protocolVer < 1.3f ){
				logger.debug("protocolVer < 1.3f continue. ");
				return success;
			}
			//获取need版本号和设备id信息
			String deviceId = request.getHeader("deviceId");
			String appVersion = request.getHeader("appVersion");
			
			UserDeviceRel userDeviceRel= userDeviceRelDAO.selectByUserId(user.getUserId());
			//没有新增.有则更新
			if(userDeviceRel==null){			
				userDeviceRel=new UserDeviceRel();	
				userDeviceRel.setAppVersion(appVersion);
				userDeviceRel.setDeviceId(deviceId);
				userDeviceRel.setUserId(user.getUserId());
				userDeviceRel.setDeviceChannle((String)request.getAttribute(Constants.APP_REQUEST_DEVICE_CHANNEL));
				logger.debug("begin insert userDeviceRel "+userDeviceRel);
				userDeviceRelDAO.insert(userDeviceRel);
			}else{
				if(!userDeviceRel.getAppVersion().equals(appVersion)||!userDeviceRel.getDeviceId().equals(deviceId)){
					userDeviceRel.setAppVersion(appVersion);
					userDeviceRel.setDeviceId(deviceId);
					userDeviceRel.setDeviceChannle((String)request.getAttribute(Constants.APP_REQUEST_DEVICE_CHANNEL));				
					logger.debug("begin update userDeviceRel "+userDeviceRel);
					userDeviceRelDAO.updateByUserId(userDeviceRel);	
				}
						
			}				
			
		} else {
			return Message.error(loginResult);
		}
		logger.info("userLogin...out..params:"+mobile+","+loginPwd);
		return success;
	}
	
}
