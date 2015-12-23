package com.need.gateway.sms;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSMS {
	private ApplicationContext context;
	private SMSService  smsService;
	
	@Before
	public void before(){
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
		smsService=(SMSService) context.getBean("SMSServiceImpl");
		//System.out.println("smsservice :"+smsService);
	}
	
	@Test
	public void sendSMSByYunXin() throws Exception{
		int result=smsService.sendSMSByYunXin("您的手机注册码为：#（*分钟内有效）。受够了生活枯燥，来 Need 找到真正新欢所要。", "15822060068");
		System.out.println(result);
	}
	@Test
	public void distributeSMSService() throws Exception{
		int result=smsService.distributeSMSService("您的手机注册码为：#（*分钟内有效）。受够了生活枯燥，来 Need 找到真正新欢所要。", "15822060068");
		System.out.println(result);
	}
	
	
}
