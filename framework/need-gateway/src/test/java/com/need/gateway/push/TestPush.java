package com.need.gateway.push;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.need.gateway.cerfication.TestCerfication;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

//@RunWith(JUnit4ClassRunner.class) 
//@ContextConfiguration(locations={"classpath*:applicationContext.xml"}) 
public class TestPush {
	
	private static final Logger logger = Logger.getLogger(TestPush.class);
	
	 @Autowired
	private ApplicationContext context;

	@Test
	public void testPush(){
				
//		JPushService  jPushService=(JPushService) context.getBean("JPushServiceImpl");		
//		jPushService.sendMessageToAndroids("", "dsds");
//		System.out.println(22323);
		
		JPushClient jpushClient=new JPushClient("c2177bca1d9e5bd898163c83","b882a764fcd5fc794436f18a",3);
		 PushPayload onePushPayload= PushPayload.newBuilder()
				 .setPlatform(Platform.all())
				 .setAudience(Audience.all())
				// .setMessage(Message.content("测试！"))
				 //.setNotification(Notification.android("来need的吧", "need", null))
				 .setNotification(Notification.alert("来吧,大黄!"))
				 .build();
		 
		 PushResult result=null;
		try {
			result = jpushClient.sendPush(onePushPayload);
		} catch (APIConnectionException | APIRequestException e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
		}
		 System.out.println(result);
		
		
	}
	
	
}
