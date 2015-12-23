package com.need.gateway.cerfication;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.need.gateway.push.JPushService;

//@RunWith(JUnit4ClassRunner.class) 
///@ContextConfiguration(locations={"classpath*:applicationContext.xml"}) 
public class TestCerfication {
	
	private static final Logger logger = Logger.getLogger(TestCerfication.class);
	
    @Autowired
	private ApplicationContext context;

	
	
	@Test
	public void testSendAll(){
		logger.info("testSendAll in ");
		JPushService service =(JPushService) context.getBean("jPushServiceImpl");
		logger.info("out");
		
	}
	
	
	
	
}
