package com.need.share.web.controller.jump;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.need.share.constant.ControllerMappings;

/**
 * <p></p>
 * @author Rylan 2015年8月27日 下午5:09:34
 * @ClassName JumpWeiXinController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月27日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
public class JumpWeiXinController {
	
	
	private static final Logger logger = Logger.getLogger(JumpWeiXinController.class);
	
	/**
	 * @author Rylan 2015年8月27日 下午5:09:47
	 * @Method: jumpWeiXin 
	 * @Description: 微信跳转
	 * @param url
	 * @param model
	 * @param request
	 * @param response
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value=ControllerMappings.WEB_JUMP_WEIXIN)
	public String jumpWeiXin(String url,Model model,HttpServletRequest request,HttpServletResponse response) {
		logger.debug("jumpWeiXin in.. url :"+url);  		
		
		return "redirect:"+url;
	}
	
	
}
