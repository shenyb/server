package com.need.erp;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.need.service.api.trade.PortalTradeService;
import com.need.service.bops.template.TopicTemplateService;

//@RunWith(JUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath*:applicationContext.xml"}) 
public class TestERP {
	
	private static final Logger logger = Logger.getLogger(TestERP.class);
	
	 private ApplicationContext context;

	 @Before
	 public void before(){
		 context=new ClassPathXmlApplicationContext("applicationContext.xml");
	 }
	 
	 
	 
	@Test
	public void testSave(){
		PortalTradeService service = (PortalTradeService) context.getBean("portalTradeServiceImpl");
		TopicTemplateService service2 = (TopicTemplateService) context.getBean("topicTemplateServiceImpl");
		//service.saveTest();
		//service2.test(25);
		
	}
	
	
}
