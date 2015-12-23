package com.need.customer.web.controller.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.customer.constant.ControllerMappings;
import com.need.domain.po.api.user.UserBase;
import com.need.domain.pub.Message;
import com.need.service.bops.user.UserBaseService;

/**
 * <p></p>
 * @author Rylan 2015年8月8日 下午4:08:58
 * @ClassName UserBaseController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.API_USER_BASE)
public class UserBaseController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserBaseService userBaseService;
	
//	@SuppressWarnings("static-access")
//	@ResponseBody
//	@RequestMapping(value="{mobile}",method = RequestMethod.GET)
//	public Message getUserBaseByMobile(@PathVariable(value = "mobile") String mobile){
//		logger.info("getUserBaseByMobile in ..");
//		Message message = Message.success();	
//		if(mobile==null||mobile.equals("")){
//			return message;
//		}
//		UserBase userBase = userBaseService.getUserBaseByMobile(mobile);
//		if(userBase==null){
//			return  message.error(2001, "没有此用户");
//		}
//		//userBase.setProfilePicKey(Constants.QINIU_DOMAIN+userBase.getProfilePicKey());
//		//userBase.setProfilePicKey(Constants.QINIU_DOMAIN+userBase.getProfilePicKey());
//		userBase.setProfilePicKey(userBase.getProfilePicKey());//modify by  liyongran 20150812 去掉域名前缀  
//		message.addData("object", userBase);		
//		return message;
//	}
	
	

	@RequestMapping(value="{mobile}",method = RequestMethod.GET)
	public Message getUserBaseByMobile(@PathVariable(value = "mobile") String mobile,Model model){
		logger.info("getUserBaseByMobile in ..param:"+mobile);
		Message message = Message.success();	
		if(mobile==null||mobile.equals("")){
			return message;
		}
		UserBase userBase = userBaseService.getUserBaseByMobile(mobile);
		if(userBase==null){
			return  message.error(2001, "没有此用户");
		}
		//userBase.setProfilePicKey(Constants.QINIU_DOMAIN+userBase.getProfilePicKey());
		//userBase.setProfilePicKey(Constants.QINIU_DOMAIN+userBase.getProfilePicKey());
		userBase.setProfilePicKey(userBase.getProfilePicKey());//modify by  liyongran 20150812 去掉域名前缀  
		message.addData("object", userBase);		
		return message;
	}
	
	
}
