package com.need.common.asynchronous;

import com.need.common.callback.GetCallbackInterface;

public class CommRunable<T> implements Runnable{

	
   private GetCallbackInterface<T> callBack;
	
	private Object[]  params;
	
	public CommRunable() {
		/** TODO Auto-generated constructor stub */
	}
		
	
	public CommRunable(GetCallbackInterface<T> callBack, Object... params) {
		super();
		this.callBack = callBack;
		this.params = params;
	}

	@Override
	public void run() {
		/** TODO Auto-generated method stub*/
		callBack.executor(params);		
	}
	
}
