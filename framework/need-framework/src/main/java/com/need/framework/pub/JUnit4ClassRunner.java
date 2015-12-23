//package com.need.framework.pub;
//
//import java.io.FileNotFoundException;
//
//import org.junit.runners.model.InitializationError;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.Log4jConfigurer;
//
///**
// * <p></p>
// * @author Rylan 2015年11月23日 下午1:59:59
// * @ClassName JUnit4ClassRunner
// * @Description TODO
// * @version V1.0   
// * @modificationHistory=========================逻辑或功能性重大变更记录
// * @modify by user: Rylan 2015年11月23日
// * @modify by reason:{方法名}:{原因}
// */
//public class JUnit4ClassRunner extends SpringJUnit4ClassRunner{
//
//	static{
//		try {
//			Log4jConfigurer.initLogging("classpath:log4j.properties");
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
