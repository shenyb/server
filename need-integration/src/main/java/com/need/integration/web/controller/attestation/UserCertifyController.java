package com.need.integration.web.controller.attestation;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.integration.constant.BizReturnCode;
import com.need.integration.constant.ControllerMappings;
import com.need.integration.dao.jdbc.api.user.UserCertificationDAO;
import com.need.integration.pub.ConstantsProConfig;
import com.need.integration.pub.Message;
import com.need.integration.service.api.attestation.CertificateCallable;


@Controller
@RequestMapping(value=ControllerMappings.USER_CETIFICATION)

public class UserCertifyController {
	
	private static final Logger logger = Logger.getLogger(UserCertifyController.class);
	
	@Autowired
	private ConcurrentTaskScheduler concurrentTaskScheduler;
	@Autowired
	private UserCertificationDAO userCertificationDAO;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	@ResponseBody
	@RequestMapping( method = RequestMethod.POST)
	public Message userCertify(String username,String idCard,String userId,String certificationChannel) throws Exception {
		logger.info("attestationId in ..username :"+username +"  idCard :"+idCard+"  userId :"+userId+"  certificationChannel :"+certificationChannel);
		Message success=Message.success();		
		//基础验证
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(idCard)||StringUtils.isEmpty(userId)){
			return Message.error(BizReturnCode.SYSTEM_ERR);
		}	
//		if(!(certificationChannel.equals(DeviceChannelEnum.ANDROID.code)||certificationChannel.equals(DeviceChannelEnum.IOS.code))){
//			return Message.error(BizReturnCode.SYSTEM_ERR);
//		}	
		//异步验证
		username=username.trim();
		idCard=idCard.trim();
		Integer result=afterDeal(username,idCard,userId,certificationChannel);	
		success.addData("result", result);		
		
		logger.info("attestationId out.. " );
		return success;
	}
		
	private Integer afterDeal(final String username,final String idCard,final String userId,final String certificationChannel){	
		//生成线程
		Future<Integer> future= concurrentTaskScheduler.submit(new 
			CertificateCallable<Integer>(username,idCard,userId,certificationChannel,constantsProConfig,userCertificationDAO));
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	
}
