package com.need.integration.dao.jdbc.api.user;

import com.need.integration.dao.jdbc.api.user.po.UserBase;

public interface UserBaseDAO {
	
	UserBase getByUserId(String userId);
	
}