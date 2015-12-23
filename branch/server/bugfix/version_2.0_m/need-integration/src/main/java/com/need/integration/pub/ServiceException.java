package com.need.integration.pub;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;

public class ServiceException extends Exception{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 5713814013800006249L;

	private int code=1;
	
	private String content;
	
	private Map<String,Object> maps;
	
	public ServiceException(){
		this.code=1;
		this.content="error";
	}
	
	public ServiceException(int code){
		this.code=code;
		this.content="";
	}
	
	public ServiceException(int code,String message){
		this.code=code;
		this.content=message;
	}
	
	public ServiceException(int code,Map<String,Object> maps){
		this.code=code;
		this.maps=maps;
	}
	
	@Override
    public synchronized Throwable getCause() {
	    /** TODO Auto-generated method stub*/
	    return super.getCause();
    }

	@Override
    public synchronized Throwable initCause(Throwable cause) {
	    /** TODO Auto-generated method stub*/
	    return super.initCause(cause);
    }

	@Override
    public void printStackTrace() {
	    /** TODO Auto-generated method stub*/
	    super.printStackTrace();
    }

	@Override
    public void printStackTrace(PrintStream s) {
	    /** TODO Auto-generated method stub*/
	    super.printStackTrace(s);
    }

	@Override
    public void printStackTrace(PrintWriter s) {
	    /** TODO Auto-generated method stub*/
	    super.printStackTrace(s);
    }

	@Override
    public synchronized Throwable fillInStackTrace() {
	    /** TODO Auto-generated method stub*/
	    return super.fillInStackTrace();
    }

	@Override
    public StackTraceElement[] getStackTrace() {
	    /** TODO Auto-generated method stub*/
	    return super.getStackTrace();
    }

	@Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
	    /** TODO Auto-generated method stub*/
	    super.setStackTrace(stackTrace);
    }

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	public int getCode() {
		return code;
	}

	public String getContent() {
		return content;
	}

	public Map<String, Object> getMaps() {
		return maps;
	}

}
