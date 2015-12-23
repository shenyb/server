package com.need.gateway.push.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;

import com.need.gateway.common.enums.PushEnum;
import com.need.gateway.pub.ConstantsProConfig;
import com.need.gateway.push.JPushRunnable;
import com.need.gateway.push.JPushService;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

@Service
public class JPushServiceImpl implements JPushService{
	
	private static final Logger logger = Logger.getLogger(JPushServiceImpl.class);
	
	@Autowired
	private  JPushClient jpushClient;
	
	@Autowired
	private ConcurrentTaskScheduler concurrentTaskScheduler;

	@Override
	public int sendMessageToRegistrationIds(String title, String msgContent,String... registrationIds) {
		/** TODO Auto-generated method stub*/
		logger.info("sendMessageToRegistrationIds in..");
	    Runnable task= new JPushRunnable(PushEnum.PUSHREGISTRATIONIDS, jpushClient,"" ,msgContent,registrationIds);		
	    concurrentTaskScheduler.execute(task);	
		return 0;
	}

	@Override
	public int sendMessageToAll(String title, String msgContent) {
		/** TODO Auto-generated method stub*/
		 logger.info("sendMessageToAll in..");
		 Runnable task= new JPushRunnable(PushEnum.PUSHALL, jpushClient,msgContent);		
		 concurrentTaskScheduler.execute(task);	
		return 0;
	}

	@Override
	public int sendMessageToAndroids(String title, String msgContent) {
		/** TODO Auto-generated method stub*/
		 logger.info("sendMessageToAndroids in..");
		 Runnable task= new JPushRunnable(PushEnum.PUSHANDROIDS, jpushClient,msgContent);		
		 concurrentTaskScheduler.execute(task);	
		
		return 0;
	}

	@Override
	public int sendMessageToIOS(String title, String msgContent) {
		/** TODO Auto-generated method stub*/
		 logger.info("sendMessageToIOS in..");
		 Runnable task= new JPushRunnable(PushEnum.PUSHIOS, jpushClient,msgContent);		
		 concurrentTaskScheduler.execute(task);		 
		return 0;
	}

	@Override
	public int sendMessageWithTagToAll( String title,String msgContent,String... tags) {
		/** TODO Auto-generated method stub*/
		 logger.info("sendMessageWithTagToAll in..");
		 Runnable task= new JPushRunnable(PushEnum.PUSHALLWITHTAG, jpushClient,msgContent,tags);		
		 concurrentTaskScheduler.execute(task);	
		return 0;
	}


	public static void main(String[] args) {
		
		JPushClient jpushClient=new JPushClient("437e509627d5e32530e5ccf9","75a2c669ba2ed84692579376",3);
//		 PushPayload onePushPayload= PushPayload.newBuilder()
//				 .setPlatform(Platform.all())
//				 .setAudience(Audience.registrationId("000c5646624"))
//				// .setMessage(Message.content("测试！"))
//				 .setNotification(Notification.android("来need的吧", "need", null))
//				 //.setNotification(Notification.alert("来吧"))
//				 .build();
		 
		 PushPayload onePushPayload= PushPayload.newBuilder()
					.setPlatform(Platform.android())//设置接收平台
					.setNotification(Notification.alert("伯利啊"))//设置通知内容
					.setAudience(Audience.all())//设置听众
					.build();	
		 
		 try {
			 //PushResult result =jpushClient.sendPush(PushPayload.alertAll("大黄啊，大黄。你给点力把。。"));
			 PushResult result =jpushClient.sendPush(onePushPayload);
			 System.out.println(result);
		} catch (APIConnectionException | APIRequestException e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
		}
	
		
	}
	
	
	
	
}
