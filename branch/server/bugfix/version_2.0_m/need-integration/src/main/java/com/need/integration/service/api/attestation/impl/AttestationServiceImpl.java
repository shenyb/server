package com.need.integration.service.api.attestation.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.need.http.HttpClientProxy;
import com.need.integration.service.api.attestation.AttestationService;

@Service
public class AttestationServiceImpl implements AttestationService {
	
//	@Autowired
//	private ConstantsProConfig constantsProConfig;
//	
//	@Override
//	public int attestationIdCard(String id, String username) {
//		/** TODO Auto-generated method stub*/
//		 Map<String, String> params=new HashMap<String, String>();
//		 params.put("corp", constantsProConfig.getCertificateAccount());
//		 String pwd=constantsProConfig.getCertificatePwd();
//		 try {
//			params.put("password", URLEncoder.encode(pwd,"ISO-8859-1"));
//		 } catch (UnsupportedEncodingException e) {
//			/** TODO Auto-generated catch block */
//			e.printStackTrace();
//			return -1;
//		 } 
//		 params.put("UserName", username);
//		 params.put("IDCard", id);
//		 
//		 String result=null;
//		try {
//			result = HttpClientProxy.sendGetRequest(constantsProConfig.getCertificateUrl(), params, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			/** TODO Auto-generated catch block */
//			e.printStackTrace();
//		}
//		 System.out.println("result :"+result);
//		 return Integer.parseInt(result.split("&")[0].split("=")[1]);
//	}
//
//	@Override
//	public int attestationIdCard1(String id, String username) {
//		/** TODO Auto-generated method stub*/
//		 Map<String, String> params=new HashMap<String, String>();
//		 params.put("corp", "cszh");
//		 String pwd="tOilUhi8UsnhtfkCIlgqDnmqZtITKBYMyqDqGdgsazYnxy+MS09Fy35N9IsgLxnfGM5UCdJfyRneenSOP8OLWaBaqd+s/iZyco7Qj9TRHA8K1xQRU7u6O56IbvCyfZA0Q+aazDMLk8yRvz9Q0cl0nodCegvc4nICngnkxpcNAc4=";		 
//		 try {
//			params.put("password", URLEncoder.encode(pwd,"ISO-8859-1"));
//		 } catch (UnsupportedEncodingException e) {
//			/** TODO Auto-generated catch block */
//			e.printStackTrace();
//			return -1;
//		 } 
//		 params.put("UserName", username);
//		 params.put("IDCard", id);
//		 
//		 String result=null;
//		try {
//			result = HttpClientProxy.sendGetRequest("http://203.171.229.145:9000/IDCertificate.ashx", params, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			/** TODO Auto-generated catch block */
//			e.printStackTrace();
//		}
//		 System.out.println("result :"+result);
//		 return Integer.parseInt(result.split("&")[0].split("=")[1]);
//	}
//
//	
//	@Override
//	public int checkBalance() {
//		/** TODO Auto-generated method stub*/
//		 Map<String, String> params=new HashMap<String, String>();
//		 params.put("corp", constantsProConfig.getCertificateAccount());
//		 String pwd=constantsProConfig.getCertificatePwd();;
//		 try {
//			params.put("password", URLEncoder.encode(pwd,"ISO-8859-1"));
//		} catch (UnsupportedEncodingException e) {
//			/** TODO Auto-generated catch block */
//			e.printStackTrace();
//			return -1;
//		}
//		String result=HttpClientProxy.sendPostRequest(constantsProConfig.getCheckBalanceUrl(), params, "UTF-8");    
//		return Integer.parseInt(result.split("&")[0].split("=")[1]);
//	}
	
	/**
	测试
	public static void main(String[] args) throws UnsupportedEncodingException  {
		
		 Map<String, String> params=new HashMap<String, String>();	
		 String psw="tOilUhi8UsnhtfkCIlgqDnmqZtITKBYMyqDqGdgsazYnxy+MS09Fy35N9IsgLxnfGM5UCdJfyRneenSOP8OLWaBaqd+s/iZyco7Qj9TRHA8K1xQRU7u6O56IbvCyfZA0Q+aazDMLk8yRvz9Q0cl0nodCegvc4nICngnkxpcNAc4=";		 
		 params.put("corp","cszh");
		 params.put("password", URLEncoder.encode(psw,"ISO-8859-1") );
		
		 
		 params.put("UserName", "高雁飞");
		 params.put("IDCard", "130185197810150917");
	
		String result=HttpClientProxy.sendGetRequest("http://203.171.229.145:9000/IDCertificate.ashx", params, "UTF-8");
		//String result=HttpClientProxy.sendGetRequest("http://203.171.229.145:9000/getBalance.ashx", params, "UTF-8");
		
		System.out.println("result :"+result);
		System.out.println(result.split("&")[0].split("=")[1]);
				
	}
*/	
    //正式
	public static void main(String[] args) throws UnsupportedEncodingException  {
		
		 Map<String, String> params=new HashMap<String, String>();	
		 String psw="kBJmLXloNFoei75EGSEk1ZZY5Gk2qSaVScrl2p+upjeReHR1e9UG4n3C8a+c6uaTL4pJdF4IY3YzQBig0XwDci20cidHmPQzYsdGJRLV3kqe4kfuLamLh6ZtMY2sgTQInJCINB+JAHMuan+PZl0ZUJw2JLjgTL75Gn8jFGthijw=";		 
		 params.put("corp","need");
		 params.put("password", URLEncoder.encode(psw,"ISO-8859-1") );
	 
		 params.put("UserName", "高雁飞");
		 params.put("IDCard", "130185197810150917");
	
		String result=HttpClientProxy.sendGetRequest("http://123.56.124.192:9000/IDCertificate.ashx", params, "UTF-8");
		
		System.out.println("result :"+result);
		System.out.println(result.split("&")[0].split("=")[1]);
		
		
		
				
	}



	
	
	
}
