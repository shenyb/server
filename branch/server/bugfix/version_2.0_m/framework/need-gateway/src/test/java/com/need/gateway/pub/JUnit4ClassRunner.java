//package com.need.gateway.pub;
//
//import java.io.FileNotFoundException;
//
//import org.junit.runners.model.InitializationError;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.Log4jConfigurer;
//
//public class JUnit4ClassRunner extends SpringJUnit4ClassRunner{
//
//	static{
//		try {
//			Log4jConfigurer.initLogging("classpath:properties/*log4j.properties");
//		} catch (FileNotFoundException e) {
//			/** TODO Auto-generated catch block */
//			e.printStackTrace();
//			System.out.println("加载log4j 文件失败！");
//		}  	
//	}
//	
//	
//	public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
//		super(clazz);
//		/** TODO Auto-generated constructor stub */
//	}
//	
//}
