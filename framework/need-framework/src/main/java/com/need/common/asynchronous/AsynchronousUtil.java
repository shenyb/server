package com.need.common.asynchronous;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.need.common.callback.GetCallbackInterface;

/**
 * <p>线程异步调用</p>
 * @author Rylan 2015年11月22日 下午6:14:55
 * @ClassName AsynchronousUtil
 * @Description 使用Runnable和Callable实现
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年11月22日
 * @modify by reason:{方法名}:{原因}
 */
public class AsynchronousUtil {
	
	private static AsynchronousUtil asynchronousUtil=new AsynchronousUtil();
	
	
	private  AsynchronousUtil() {
		/** TODO Auto-generated constructor stub */
	}
	
	public static AsynchronousUtil  getInstance(){
		return asynchronousUtil;
	}
	
	/**
	 * @author Rylan 2015年11月22日 下午6:16:20
	 * @Method: runAsynByReturn 
	 * @Description: 线程异步实现回调，并返回结果
	 * @param executor
	 * @param params
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException 
	 * @throws
	 */
	public  <T>  Object runAsynByReturn(GetCallbackInterface<T> executor, Object... params) throws InterruptedException, ExecutionException{
		ExecutorService executors=null;
		T result =null;
		Callable<T> callable =new CommCallable<T>(executor,params);		
		Future<T> future=executors.submit(callable);
		result=future.get();
		return result;
	}
	
	/**
	 * @author Rylan 2015年11月22日 下午6:16:01
	 * @Method: runAsynNoReturn 
	 * @Description: 线程异步实现回调
	 * @param executor
	 * @param params
	 * @throws InterruptedException
	 * @throws ExecutionException 
	 * @throws
	 */
	public  <T> void runAsynNoReturn(ExecutorService executors,GetCallbackInterface<T> executor, Object... params) throws InterruptedException, ExecutionException{
		
		Runnable command  =new  CommRunable<T>(executor,params); 
		executors.execute(command);
		return ;
	}
	
}
