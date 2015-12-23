package com.need.service.test.user;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.need.facade.user.UserService;
import com.need.service.test.pub.JUnit4ClassRunner;


@RunWith(JUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath*:applicationContext.xml"}) 
public class TestUserServices {
	
	private static final Logger logger = Logger.getLogger(TestUserServices.class);
	
	 @Autowired
	private ApplicationContext context1;

	@Test
	public void test(){
		
		
		//UserService  userService =(UserService) = context1.getBean("userServiceImpl");	
		//userService.sendGeneralValidateCode("");
		//userService.sendGeneralValidateCode(""); 
		
		
	}
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
			context.start();
		} catch (Exception e) {
			logger.error("== DubboProvider context start error:",e);
		}
		synchronized (UserService.class) {
			while (true) {
				try {
					UserService.class.wait();
				} catch (InterruptedException e) {
					logger.error("== synchronized error:",e);
				}
			}
		}
	}
	
	
}
