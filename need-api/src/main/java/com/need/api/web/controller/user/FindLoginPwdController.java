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
 * <p>
 * </p>
 * 
 * @author xiehao 2015年7月27日 下午1:44:37
 * @ClassName findLoginPwdController
 * @Description TODO 用户找回密码设置新密码
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年7月27日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_FIND_LOGIN_PWD)
public class FindLoginPwdController {

    private static final Logger logger = Logger.getLogger(FindLoginPwdController.class);

	@Autowired
	private UserService userService;
	@ParamsValidate(value={
			@ParamValidate(name="mobile",notNull=true,code=BizReturnCode.MOBILE_NOT_NOT_EXIST),
			@ParamValidate(name="mobile",regex="^1+[3|5|7|8]+\\d{9}$",code=BizReturnCode.PLEASE_ENTER_VALID_MOBILE),
//			@ParamValidate(name="loginPwd",notNull=true,code=BizReturnCode.PASSWORD_NOT_NULL)
			})

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message findLoginPwd(
			@RequestParam(required = true) String loginPwd,
			@RequestParam(required = true) String mobile,
			@RequestParam(required = true) String validateCode) {
		logger.info(String.format("findLoginPwd...in....params:%s %s %s", loginPwd,mobile,validateCode));
		if(StringUtil.isBlank(loginPwd)){
			return Message.error(BizReturnCode.PASSWORD_NOT_EXIST);
		}
		int result = userService.findLoginPwdToUpdateLoginPwd(mobile, loginPwd, validateCode);
		logger.info(String.format("findLoginPwd...out....params:%s %s %s", loginPwd,mobile,validateCode));
		return result == Message.SUCCESS ? Message.success() : Message.error(result);
	}

}
