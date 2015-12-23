package com.need.integration.common.haimeiSdk.util;

import java.security.MessageDigest;

import com.need.integration.common.haimeiSdk.util.codec.Base64;

public class AuthUtil {
	
	public static String getMD5(String orgin) {
		return getMD5(orgin, "utf-8");
	}

	public static String getMD5(String orgin, String charset) {
		if (charset == null) charset = "utf-8";
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			result = byte2hex(md.digest(orgin.getBytes(charset)));
		} catch (Exception e) {
			throw new java.lang.RuntimeException("sign error !", e);
		}
		return result;
	}
	
	public static String byte2hex(byte[] b){
		StringBuffer hs = new StringBuffer(); 
		String stmp = ""; 
		for (int n = 0; n < b.length; n++) { 
	         stmp = (java.lang.Integer.toHexString(b[n] & 0XFF)); 
	         if (stmp.length() == 1) 
	         hs.append("0").append(stmp); 
	         else 
	         hs.append(stmp); 
	     } 
	     return hs.toString();
	}
	

	/**
	 * 将二进制内容编码为base64字符串
	 * @param srcContent 需要编码的数据
	 * @return String 编码结果。如果参数为null，则返回null。
	 */
	public static String encode(byte[] srcContent)
	{
		if(srcContent == null)
		{
			return null;
		}
		return new String(Base64.encodeBase64(srcContent));
	}
	
	
	public static String sign(String str)  {
		return encode(getMD5(str).getBytes());
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		String dataString = "{\"storeCode\":\"ZHISHENGXIAN\",\"sellerUserId\":\"ZHISHENGXIAN\"}";
		String key = "ZHISHENGXIAN";
		System.out.println(dataString);
		System.out.println(key);
		System.out.print(sign(dataString+key));
		System.out.print(new String(sign(dataString+key)).length());
	}

}
