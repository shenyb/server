package com.need.service.user.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.need.facade.user.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public int sendGeneralValidateCode(String mobile) {
		/** TODO Auto-generated method stub*/
		logger.debug("sendGeneralValidateCode in ..");		
		System.out.println(1111);
		
		
		return -1;
	}
	
}
