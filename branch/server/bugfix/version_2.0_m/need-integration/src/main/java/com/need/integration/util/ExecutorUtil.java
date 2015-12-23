//package com.need.integration.util;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//import org.springframework.util.StringUtils;
//
//import com.need.integration.dao.jdbc.api.user.UserCertificationDAO;
//import com.need.integration.service.api.attestation.CertificateCallable;
//
//public  class  ExecutorUtil {
//	
//	private static ExecutorUtil executorUtil=new ExecutorUtil();
//	private static ExecutorService executors;
//	
//	//资源信息
//	private static String certificateAccount;
//	private static String certificatePwd;
//	private static String certificateUrl;
//	private static String checkBalanceUrl;
//	private static String propertyFile="/properties/constants.properties";
//	
//	private static ApplicationContext context;
//	private UserCertificationDAO userCertificationDAO;
//	
//	private ExecutorUtil() {
//		/** TODO Auto-generated constructor stub */
//	}
//	
//	public static ExecutorUtil getInstance(){
//		init();
//		return executorUtil;
//	}
//	
//	//初始化  公共资源由线程管理类来初始化
//	private static  void init(){
//		executors= Executors.newCachedThreadPool();	//缓存方式
//		//Executors.newFixedThreadPool(threadSize);
//		certificateAccount=PropertiesUtil.getProperty(propertyFile, "certificateAccount");
//		certificatePwd=PropertiesUtil.getProperty(propertyFile, "certificatePwd");
//		certificateUrl=PropertiesUtil.getProperty(propertyFile, "certificateUrl");
//		checkBalanceUrl=PropertiesUtil.getProperty(propertyFile, "checkBalanceUrl");
//		context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		//context=new FileSystemXmlApplicationContext(""); //文件方式
//		
//	}
//	
//	public  Integer runCertificateExcutor(final String username, final String idCard){	
//		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(idCard)){
//			return -1;
//		}
//		userCertificationDAO=(UserCertificationDAO) context.getBean("userCertificationDAO");
//		Callable<Integer> call=new CertificateCallable<Integer>(null,null,null,null,null,userCertificationDAO);
//		Future<Integer> future=executors.submit(call);
//		Integer result=-1;
//		try {
//			result = future.get();
//		} catch (InterruptedException | ExecutionException e) {
//			/** TODO Auto-generated catch block */
//			e.printStackTrace();
//			return -1;
//		}
//		return result;
//		
//	}
//	
//	
//}
