package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.pub.Message;
import com.need.utils.StringUtil;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年7月25日 上午10:48:19
 * @ClassName UpdateNickNameController
 * @Description 修改昵称
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_UPDATE_NICK_NAME)
public class UpdateNickNameController {
	
	private static final Logger logger = Logger.getLogger(UpdateNickNameController.class);
	
	@Autowired
	private UserService userService;
	@ParamsValidate(value={
	//		@ParamValidate(name="nickName",notNull=true,code=BizReturnCode.NICKNAME_NOT_EXIST),
	//		@ParamValidate(name="nickName",minLen=2,code=BizReturnCode.NICKNAME_LENGTH_TIPS),
	//		@ParamValidate(name="nickName",maxLen=16,code=BizReturnCode.NICKNAME_LENGTH_TIPS)
			})
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message userUpdateNickName(
	        @RequestParam(required = true) String userId,
	        @RequestParam(required = true) String nickName) {
		logger.info(String.format("userUpdateNickName...params:%s %s", userId,nickName));
		Message success = Message.success();

		if(StringUtil.isBlank(nickName)){
			return Message.error(BizReturnCode.NICKNAME_NOT_EXIST);
		}
		try {
			if(nickName.toString().getBytes("gbk").length < 2){
			return Message.error(BizReturnCode.NICKNAME_LENGTH_TIPS);
			}
			if(nickName.toString().getBytes("gbk").length > 16){
				return Message.error(BizReturnCode.NICKNAME_LENGTH_TIPS);
				}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!userService.checkUserIdExists(userId)) {
			return Message.error(BizReturnCode.USER_NOT_EXIST);
		}
		if (userService.checkNickNameExists(nickName)) {

			return Message.error(BizReturnCode.NICKNAME_ALREADY_EXISTS);
		}
		userService.updateNickName(userId, nickName);
		logger.info("userUpdateNickName...out");
		return success;
	}
}
