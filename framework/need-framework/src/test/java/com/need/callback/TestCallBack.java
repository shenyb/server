package com.need.callback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.need.common.asynchronous.AsynchronousUtil;
import com.need.common.callback.CallBackExecutor;
import com.need.common.callback.GetCallbackInterface;

public class TestCallBack {
	
	private CallBackExecutor<Student> executor;
	private static ExecutorService executors=Executors.newCachedThreadPool();;
	
	@Test
	public void testCallBack(){
		
		GetCallbackInterface<Student> callback=	new GetCallbackInterface<Student>(){
			@Override
			public Student executor(Object... parameters) {
				/** TODO Auto-generated method stub*/
				Student s=new Student();
				s.setId(Integer.parseInt(parameters[0].toString()));
				s.setName(parameters[1].toString());
				return s;
			}

			
		};  
		
		executor=new CallBackExecutor<Student>();
		System.out.println(11);
		Student s=executor.executor(callback, 1,"章撒");
		System.out.println(s);
		
		System.out.println(22);
		
		
	}
	
	public void TestAsynchronousUtil(){
		
		
		
	}
	
	
	public static void main(String[] args) {
		GetCallbackInterface<Student> callback=	new GetCallbackInterface<Student>(){


			@Override
			public Student executor(Object... parameters) {
				/** TODO Auto-generated method stub*/
				System.out.println("executor in ");
				Student s=new Student();
				s.setName(parameters[1].toString());
				
				return s;
			}
			
		};
		
		
		try {
		    System.out.println(11);
			AsynchronousUtil.getInstance().runAsynNoReturn(Executors.newFixedThreadPool(2),callback, 1,"22");
		   System.out.println(22);
		  
		} catch (InterruptedException | ExecutionException e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
		}
	}
	
	
	
}
