package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
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
/**
 * 
 * <p></p>
 * @author xiehao 2015年7月26日 上午10:29:18
 * @ClassName UpdateLoginPwd
 * @Description TODO 用户更新个人的登录密码
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年7月26日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_UPDATE_LOGIN_PWD)
public class UpdateLoginPwdController {

	private static final Logger logger = Logger.getLogger(GetTradeGoodsController.class);
	
	@Autowired
	private UserService userService;
	
	@ParamsValidate(value={
			@ParamValidate(name="loginPwd",notNull=true,code=BizReturnCode.ORIGINAL_PASSWORD_NOT_EXIST)
			})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message updatePwd(
			@RequestParam(required = true)String userId,
			@RequestParam(required = true)String loginPwd,
			@RequestParam(required = true)String newLoginPwd
			){
		logger.info(String.format("updatePwd....in.. params:%s %s %s",userId,loginPwd,newLoginPwd));
		Message message = Message.success();
		if(StringUtil.isBlank(newLoginPwd)){
			return Message.error(BizReturnCode.NEW_PASSWORD_NOT_EXIST);
		}
		boolean flag=userService.checkLoginPwd(userId, loginPwd);//校验用户信息
		logger.info(flag);
		if(flag){
			userService.changeLoginPwd(userId, newLoginPwd);
			logger.info(String.format("updatePwd....out.. params:%s %s %s",userId,loginPwd,newLoginPwd));
			return message;
		}
		else{	//原密码错误	
			return Message.error(BizReturnCode.ORIGINAL_PASSWORD_ERROR);
		}
	}
}
