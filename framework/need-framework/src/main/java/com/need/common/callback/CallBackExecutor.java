package com.need.common.callback;

public class CallBackExecutor<V> {
	
	public  V  executor(GetCallbackInterface<V> executor, Object... parameters){
		V result = executor.executor(parameters);	
		return result;
	}	
	
}
