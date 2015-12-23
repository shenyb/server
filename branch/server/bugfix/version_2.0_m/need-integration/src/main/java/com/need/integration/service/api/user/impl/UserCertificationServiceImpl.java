package com.need.integration.service.api.user.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.biz.utils.BizCodeUtil;
import com.need.http.HttpClientProxy;
import com.need.integration.dao.jdbc.api.user.UserCertificationDAO;
import com.need.integration.dao.jdbc.api.user.po.UserCertificationPO;
import com.need.integration.pub.ConstantsProConfig;
import com.need.integration.service.api.user.UserCertificationService;

@Service
public class UserCertificationServiceImpl implements UserCertificationService {

	@Autowired
	private UserCertificationDAO userCertificationDAO;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	@Override
	public void checkCertification(String username, String idCard, String userId, String certificationChannel) throws UnsupportedEncodingException {
		/** TODO Auto-generated method stub*/
       
		 Map<String, String> params=new HashMap<String, String>();
		 params.put("corp", constantsProConfig.getCertificateAccount());
		 String pwd=constantsProConfig.getCertificatePwd();
		 params.put("password", URLEncoder.encode(pwd,"ISO-8859-1"));
		  
		 params.put("UserName", username);
		 params.put("IDCard", idCard);
		 
		 String result=HttpClientProxy.sendGetRequest(constantsProConfig.getCertificateUrl(), params, "UTF-8");
		 System.out.println("result :"+result);
		
		 int res=java.lang.Integer.valueOf(result.split("&")[0].split("=")[1]);
		 if(res==0){		  
			//校验成功插入记录
			UserCertificationPO	userCertification=new UserCertificationPO();
			userCertification.setId(BizCodeUtil.generateUserId(userId));
			userCertification.setUserId(userId);
			userCertification.setCertificationChannel(certificationChannel);
			userCertification.setCertificationSource("美意");
			userCertification.setIdCard(idCard);
			userCertification.setRealName(username);
			System.out.println(userCertification);
			System.out.println(userCertificationDAO);
			userCertificationDAO.insert(userCertification);
				
		 }
		 
		 
	}
	
}
