package com.need.gateway.push;

import org.apache.log4j.Logger;

import com.need.gateway.common.enums.PushEnum;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class JPushRunnable implements Runnable{

	private static final Logger logger = Logger.getLogger(JPushRunnable.class);
	
	private String tital;
	private String message;	 
	private String[] registrationIds;	
	private JPushClient jpushClient;
	private String operType;  
	private String[] tags;
	
	public JPushRunnable(PushEnum pushEnum ,JPushClient jpushClient,String tital, String message,String... registrationIds) {
		super();
		this.tital = tital;
		this.message = message;
		this.registrationIds = registrationIds;
		this.jpushClient=jpushClient;
		this.operType=pushEnum.code;
	}
	
	public JPushRunnable(PushEnum pushEnum ,JPushClient jpushClient,String tital, String message) {
		super();
		this.tital = tital;
		this.message = message;
		this.jpushClient=jpushClient;
		this.operType=pushEnum.code;
	}
	
	public JPushRunnable(PushEnum pushEnum ,JPushClient jpushClient,String message) {
		super();
		this.message = message;
		this.jpushClient=jpushClient;
		this.operType=pushEnum.code;
	}
	
	public JPushRunnable(PushEnum pushEnum ,JPushClient jpushClient, String message,String... tags) {
		super();
		this.tags = tags;
		this.message = message;
		this.jpushClient=jpushClient;
		this.operType=pushEnum.code;
		
	}
	
	
	

	@Override
	public void run() {
		/** TODO Auto-generated method stub*/
		//构造推送对象
		PushResult result =null;
		PushPayload pushPayload = null;
		
		logger.info("operType is :"+this.operType);
		try {
			if(this.operType.equals(PushEnum.PUSHALL.code)){
				result=jpushClient.sendPush(PushPayload.alertAll(message));		
			}
						
			if(this.operType.equals(PushEnum.PUSHANDROIDS.code)){
				pushPayload =PushPayload.newBuilder()
				.setPlatform(Platform.android())//设置接收平台
				.setNotification(Notification.alert(message))//设置通知内容
				.setAudience(Audience.all())//设置听众
				.build();
				result=jpushClient.sendPush(pushPayload);
				
			}
			
			if(this.operType.equals(PushEnum.PUSHIOS.code)){
				pushPayload= PushPayload.newBuilder()
				.setPlatform(Platform.ios())//设置接收平台
				.setNotification(Notification.alert(message))//设置通知内容
				.setAudience(Audience.all())//设置听众
				.build();
				result=jpushClient.sendPush(pushPayload);
			}
			
			if(this.operType.equals(PushEnum.PUSHALLWITHTAG.code)){
				pushPayload= PushPayload.newBuilder()
				.setPlatform(Platform.all())//设置接收平台
				.setNotification(Notification.alert(message))//设置通知内容
				.setAudience(Audience.tag(tags))//设置听众
				.build();	
				result=jpushClient.sendPush(pushPayload);
			}
			
			if(this.operType.equals(PushEnum.PUSHREGISTRATIONIDS.code)){
				pushPayload= PushPayload.newBuilder()
				.setPlatform(Platform.all())//设置接收平台						
				.setNotification(Notification.alert(message))//设置通知内容
				.setAudience(Audience.registrationId(registrationIds))//设置听众
				.build();
				result=jpushClient.sendPush(pushPayload);
			}		
			
			logger.info("result is :"+result);	
		} catch (APIConnectionException | APIRequestException e1) {
			/** TODO Auto-generated catch block */
			logger.error(e1.getMessage());
		}
		
		
	}

}
