package com.need.gateway.cerfication;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.need.common.callback.GetCallbackInterface;
import com.need.gateway.param.CertificateParam;

public  class  ExecutorUtil {
	
	private static ExecutorUtil executorUtil=new ExecutorUtil();
	private static ExecutorService executors;
	private static ApplicationContext context;
	
	
	private ExecutorUtil() {
		/** TODO Auto-generated constructor stub */
	}
	
	public static ExecutorUtil getInstance(){
		init();
		return executorUtil;
	}
	
	//初始化  公共资源由线程管理类来初始化
	private static  void init(){
		executors= Executors.newCachedThreadPool();	//缓存方式
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
		//context=new FileSystemXmlApplicationContext(""); //文件方式
		
	}
	
	public  <T> Integer runCertificateExcutor(final CertificateParam  certificateParam,GetCallbackInterface<T> executor,Object... parmas){	
		if(StringUtils.isEmpty(certificateParam)){
			return -1;
		}
		
		Callable<Integer> call=new CertificateCallable<Integer,T>(executor,parmas);
		Future<Integer> future=executors.submit(call);
		Integer result=-1;
		try {
			result = future.get();
		} catch (InterruptedException | ExecutionException e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
			return -1;
		}
		return result;
		
	}
	
	
}
