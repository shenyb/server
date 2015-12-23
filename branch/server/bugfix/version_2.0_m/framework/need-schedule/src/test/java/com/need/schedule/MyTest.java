package com.need.schedule;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
	
	private static final transient Logger LOGGER = LoggerFactory.getLogger(MyTest.class);
	
	private ApplicationContext context;
	
	
	@Before
	public void before(){
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void test(){
		
		context.getBean("zkScheduleManager");
		
	}
	
}
