package com.need.share.web.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.need.share.constant.ControllerMappings;

/**
 * <p>微信授权</p>
 * @author Rylan 2015年9月6日 下午5:40:04
 * @ClassName WeixinAuthController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月6日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
public class WeixinAuthController {
	
	
	/**
	 * @author Rylan 2015年9月6日 下午5:48:33
	 * @Method: getTicket 
	 * @Description: 微信回调获取用户的code
	 * @param code
	 * @param state
	 * @return 
	 * @throws
	 */
	@RequestMapping(value=ControllerMappings.WEB_WECHAT_CALL_BACK)
    public String getTicket(String code,String state){
		
		
		
		
		
		return "";
	}
	
	
	
	
	
	
}
