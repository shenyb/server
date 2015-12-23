package com.need.gateway.cerfication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.callback.GetCallbackInterface;
import com.need.gateway.param.CertificateParam;
import com.need.http.HttpClientProxy;


@SuppressWarnings("hiding")
public class CertificateCallable<Integer, T> implements Callable<Integer>{

	private static final Logger logger = Logger.getLogger(CertificateCallable.class);
	
	private String username; //用户名
	
	private String idCard;//身份证
	
	private  String certificateAccount;//第三方账户
	
	private  String certificatePwd;//密码
	
	private  String certificateUrl;//发送url地址
	
	private GetCallbackInterface<T> executor;
	
	private Object[]  parmas;
	

	public CertificateCallable(GetCallbackInterface<T> executor,Object... parmas) {
		super();
		this.executor = executor;
		this.parmas = parmas;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Integer call() throws Exception {
		/** TODO Auto-generated method stub*/
		//执行业务逻辑
		//executor.first(parmas);
		 //美意身份验证
		 Map<String, String> params=new HashMap<String, String>();
		 params.put("corp", certificateAccount);
		 String pwd=certificatePwd;
		 try {
			params.put("password", URLEncoder.encode(pwd,"ISO-8859-1"));
		 } catch (UnsupportedEncodingException e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
			return (Integer) java.lang.Integer.valueOf(-1);
		 } 
		 params.put("UserName", username);
		 params.put("IDCard", idCard);
		 
		 String result=HttpClientProxy.sendGetRequest(certificateUrl, params, "UTF-8");
		 logger.info("result :"+result);
		 //得到返回结果
		 int res=java.lang.Integer.valueOf(result.split("&")[0].split("=")[1]);
		 if(res==0){
			//校验成功插入记录
			 //executor.second(parmas);
			return (Integer) java.lang.Integer.valueOf("0");
		 }else{
			 return (Integer) java.lang.Integer.valueOf("-1");
		 }
		 
	}
	
}
