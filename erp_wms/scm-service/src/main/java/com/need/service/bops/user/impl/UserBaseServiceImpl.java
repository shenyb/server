package com.need.service.bops.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.dao.api.user.UserBaseDAO;
import com.need.domain.po.api.user.UserBase;
import com.need.domain.pub.Message;
import com.need.service.bops.user.UserBaseService;
import com.need.service.constant.BizReturnCode;

@Service
public class UserBaseServiceImpl implements UserBaseService {

	@Autowired
	private UserBaseDAO userBaseDAO;
	
	@Override
	public UserBase getUserBaseByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userBaseDAO.selectUserBaseByMobile(mobile);
	}

	@Override
	public Message changeUserStatus(String userId,String userStatus) {
		Message message= Message.success();
		int isSuccess= userBaseDAO.updateUserStatus(userId,userStatus);
		if(isSuccess!=0){
			return message;
		}else{
			return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
		}
		
	}

}
