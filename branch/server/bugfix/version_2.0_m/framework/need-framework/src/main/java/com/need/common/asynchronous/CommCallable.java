package com.need.common.asynchronous;

import java.util.concurrent.Callable;

import com.need.common.callback.GetCallbackInterface;

public class CommCallable<T> implements Callable<T>{

	private GetCallbackInterface<T> callBack;
	
	private Object[]  params;
	
	public CommCallable() {
		/** TODO Auto-generated constructor stub */
	}
	
	
	public CommCallable(GetCallbackInterface<T> callBack, Object... params) {
		super();
		this.callBack = callBack;
		this.params = params;
	}


	@Override
	public T call() throws Exception {
		/** TODO Auto-generated method stub*/
		System.out.println("call in ");
		T result=callBack.executor(params);
		System.out.println("call out ");
		return result;
	}
	
}
