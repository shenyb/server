package com.need.api.web.controller.user;

import com.need.api.constant.Constants;
import com.need.api.constant.ControllerMappings;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.user.UserDeviceRelDAO;
import com.need.common.core.pub.ConstantsProConfig;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.enums.UserTypeEnum;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.po.api.user.UserDeviceRel;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.UserInfoVO;
import com.need.jedis.JedisSentinelClient;
import com.need.utils.StringUtil;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
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
 * @ClassName RegisterController
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_REGISTER)
public class RegisterController {
	
	private static final Logger logger = Logger.getLogger(RegisterController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	@Autowired
	private UserDeviceRelDAO userDeviceRelDAO;
	
	//modify by peiboli 20150917  修改原始1.0 版本 为支持国际短信
	@ParamsValidate(value={
			@ParamValidate(name="nickName",notNull=true,code=BizReturnCode.NICKNAME_NOT_EXIST),
			@ParamValidate(name="mobile",notNull=true,code=BizReturnCode.MOBILE_NOT_NOT_EXIST),
//			@ParamValidate(name="mobile",regex="^1+[3|5|7|8]+\\d{9}$",code=BizReturnCode.PLEASE_ENTER_VALID_MOBILE),
			@ParamValidate(name="profilePicKey",notNull=true,code=BizReturnCode.UPLOAD_PROFILE_PIC_KEY),
			@ParamValidate(name="nickName",minLen=2,code=BizReturnCode.NICKNAME_LENGTH_TIPS),
			@ParamValidate(name="nickName",maxLen=16,code=BizReturnCode.NICKNAME_LENGTH_TIPS)
			})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message userRegister(
	        @RequestParam(required = true) String nickName,
	        @RequestParam(required = true) String mobile,
	        @RequestParam(required = true) String loginPwd,
	        @RequestParam(required = true) String profilePicKey) {
		logger.info(String.format("userRegister....in... params:%s %s %s %s", nickName,mobile,loginPwd,profilePicKey));
		Message success = Message.success();
		if(StringUtil.isBlank(loginPwd)){
			return Message.error(BizReturnCode.PASSWORD_NOT_EXIST);
		}
		if(StringUtil.isBlank(mobile)){
			return Message.error(BizReturnCode.MOBILE_NOT_NOT_EXIST);
		}
		if(mobile.length()!=Constants.MOBILE_LENGTH){
			return Message.error(BizReturnCode.PLEASE_ENTER_VALID_MOBILE);
		}
		if (userService.checkNickNameExists(nickName)) {//校验昵称是否已经存在
			return Message.error(BizReturnCode.NICKNAME_ALREADY_EXISTS);		}
		if(userService.checkMobileExists(mobile)){//校验手机号是否存在
			return Message.error(BizReturnCode.MOBILE_ALREADY_EXISTS);
		}
		Boolean isSuccess = userService.insert(nickName,mobile,loginPwd,profilePicKey);
        if(isSuccess){
        	logger.info(String.format("userRegister....out.... params:%s %s %s %s", nickName,mobile,loginPwd,profilePicKey));
        	return success;
        }else{
        	return Message.error(BizReturnCode.USER_REGISTRATION_FAILED);
        }
		
      
	}
	
	/**
	 * @author Rylan 2015年10月22日 下午11:07:04
	 * @Method: userRegister_1_3 
	 * @Description: 注册1.3接口
	 * @param mobile
	 * @param loginPwd
	 * @param valideCode
	 * @return 
	 * @throws
	 */
	@ParamsValidate(value={
			@ParamValidate(name="mobile",notNull=true,code=BizReturnCode.TRADE_MOBILE_EXIST),
			@ParamValidate(name="loginPwd",notNull=true,code=BizReturnCode.PASSWORD_NOT_EXIST),
			@ParamValidate(name="validateCode",notNull=true,code=BizReturnCode.CODE_ERROR)
			})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.POST)
	public Message userRegister_1_3(String mobile,String loginPwd,String validateCode,HttpServletRequest request) {
		logger.info(String.format("userRegister_1_3....in... params:%s %s %s",mobile,loginPwd,validateCode));
		
		Message success = Message.success();
				
		if(StringUtil.isBlank(loginPwd)){
			return Message.error(BizReturnCode.PASSWORD_NOT_EXIST);
		}
		if(StringUtil.isBlank(mobile)){
			return Message.error(BizReturnCode.MOBILE_NOT_NOT_EXIST);
		}
		if(StringUtil.isBlank(validateCode)){
			return Message.error(BizReturnCode.CODE_ERROR);
		}
		if(mobile.length()!=Constants.MOBILE_LENGTH){
			return Message.error(BizReturnCode.PLEASE_ENTER_VALID_MOBILE);
		}
		
		String code=JedisSentinelClient.getString(mobile);
		//验证码校验不通过 
		if(code==null||!code.equals(validateCode)){
			return Message.error(BizReturnCode.CODE_ERROR);
		}
		if(userService.checkMobileExists(mobile)){//校验手机号是否存在
			return Message.error(BizReturnCode.MOBILE_ALREADY_EXISTS);
		}
		
		UserBasePO user=new UserBasePO();		
		user.setMobile(mobile);
		user.setLoginPwd(loginPwd);
		//设置默认昵称和头像
		user.setNickName(BizCodeUtil.generateUserNickName(mobile));
		user.setProfilePicKey(constantsProConfig.getDefaultProfilePicKey());
	
		Boolean isSuccess = userService.insert_1_3(user);      		
		if(isSuccess){
			//新增登陆流程			
			String userToken = BizCodeUtil.generateUserToken(user.getUserId());
			UserInfoVO result = new UserInfoVO();
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
        	return success;
        }else{
        	return Message.error(BizReturnCode.USER_REGISTRATION_FAILED);
        }
		
      
	}
	
	/**
	 * 
	 * @author peiboli 2015年12月7日 下午2:23:24
	 * @Method: userRegister_2_0 
	 * @Description: TODO普通注册，参数增加channel
	 * @param mobile
	 * @param loginPwd
	 * @param validateCode
	 * @param request
	 * @return 
	 * @throws
	 */
	@ParamsValidate(value={
			@ParamValidate(name="mobile",notNull=true,code=BizReturnCode.TRADE_MOBILE_EXIST),
			@ParamValidate(name="loginPwd",notNull=true,code=BizReturnCode.PASSWORD_NOT_EXIST),
			@ParamValidate(name="validateCode",notNull=true,code=BizReturnCode.CODE_ERROR)
			})
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message userRegister_2_0(String mobile,String loginPwd,String validateCode,String channel,HttpServletRequest request) {
		logger.info(String.format("userRegister_2_0....in... params:%s %s %s",mobile,loginPwd,validateCode));
		
		Message success = Message.success();
				
		if(StringUtil.isBlank(loginPwd)){
			return Message.error(BizReturnCode.PASSWORD_NOT_EXIST);
		}
		if(StringUtil.isBlank(mobile)){
			return Message.error(BizReturnCode.MOBILE_NOT_NOT_EXIST);
		}
		if(StringUtil.isBlank(validateCode)){
			return Message.error(BizReturnCode.CODE_ERROR);
		}
		if(StringUtil.isBlank(channel)){
			return Message.error(BizReturnCode.CHANNEL_ID_NOT_NULL);
		}
		if(mobile.length()!=Constants.MOBILE_LENGTH){
			return Message.error(BizReturnCode.PLEASE_ENTER_VALID_MOBILE);
		}
		
		String code=JedisSentinelClient.getString(mobile);
		//验证码校验不通过 
		if(code==null||!code.equals(validateCode)){
			return Message.error(BizReturnCode.CODE_ERROR);
		}
		if(userService.checkMobileExists(mobile)){//校验手机号是否存在
			return Message.error(BizReturnCode.MOBILE_ALREADY_EXISTS);
		}
		
		UserBasePO user=new UserBasePO();		
		user.setMobile(mobile);
		user.setLoginPwd(loginPwd);
		//设置默认昵称和头像
		user.setNickName("Need"+BizCodeUtil.generateUserNickName(mobile));
		user.setProfilePicKey(constantsProConfig.getDefaultProfilePicKey());
	
		Boolean isSuccess = userService.insert_2_0(user,channel);      		
		if(isSuccess){
			//新增登陆流程			
			String userToken = BizCodeUtil.generateUserToken(user.getUserId());
			UserInfoVO result = new UserInfoVO();
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
        	return success;
        }else{
        	return Message.error(BizReturnCode.USER_REGISTRATION_FAILED);
        }
		
      
	}
	
	
	
	
	
}
