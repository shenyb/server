package com.need.wap.web.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.enums.RegisterChannelEnum;
import com.need.common.domain.enums.RegisterTypeEnum;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.LoginVO;
import com.need.utils.StringUtil;
import com.need.wap.constant.ControllerMappings;

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
	@Resource
	private UserBaseDAO userBaseDAO;
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message userLogin(@RequestBody LoginVO params,HttpServletRequest request) {
		logger.info("userLogin ...in..params:"+params.toString());
		String userAreaId= request.getHeader("User-AreaId");
		if (StringUtil.isBlank(params.getMobile())) {
			return Message.error(BizReturnCode.MOBILE_NOT_NOT_EXIST);
		} 
		if(StringUtil.isBlank(params.getValidateCode())){
			return Message.error(BizReturnCode.CODE_NOT_EXIST);
		}
		params.setUserAreaId(userAreaId);
		params.setType(RegisterTypeEnum.WAPFAST.code);
		params.setChannel(RegisterChannelEnum.WAP.code);
		Message success = userService.wapInsertAndGetUserInfoByCode(params);
		return success;
	}
}
