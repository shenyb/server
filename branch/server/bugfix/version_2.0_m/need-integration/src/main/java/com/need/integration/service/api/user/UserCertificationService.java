package com.need.integration.service.api.user;

import java.io.UnsupportedEncodingException;

public interface UserCertificationService {
	
	 public void checkCertification(String username,String idCard,String userId,String certificationChannel)throws UnsupportedEncodingException;
	
	
	
}
