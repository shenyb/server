package com.need.api.controller.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.need.facade.user.UserService;

@Controller
@RequestMapping(value = "/user/getUserInfo")
public class GetUserInfoController {
	
	private static final Logger logger = Logger.getLogger(GetUserInfoController.class);
	
	@Autowired
	private UserService  userService;
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String getUserInfo(String userId) {
		
		System.out.println(userService.sendGeneralValidateCode(""));
		return "中文";
	}
	
	
	
	
}
