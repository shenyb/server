package com.need.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rylan 2015年8月6日 下午4:06:12
 * @ClassName CookieUtil
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月6日
 * @modify by reason:{方法名}:{原因}
 */
public class CookieUtil {
	
	
	/**
	 * @author Rylan 2015年8月6日 下午4:14:19
	 * @Method: addCookie 
	 * @Description: TODO 添加cookie，默认路径是 / 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge  单位是秒
	 * @throws
	 */
	public static void addCookie(HttpServletResponse response, String name,String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	/**
	 * @author Rylan 2015年5月28日 下午4:06:32
	 * @Method: getCookieByName 
	 * @Description: TODO 根据名字获取cookie（接口方法）
	 * @param request
	 * @param name
	 * @return 
	 * @throws
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}
	/**
	 * @author Rylan 2015年8月6日 下午4:21:25
	 * @Method: getCookieValue 
	 * @Description: TODO 
	 * @param request
	 * @param name
	 * @return 
	 * @throws
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie.getValue();
		} else {
			return null;
		}
	}

	/**
	 * @author Rylan 2015年5月28日 下午4:06:53
	 * @Method: ReadCookieMap 
	 * @Description: TODO 将cookie封装到Map里面（非接口方法）
	 * @param request
	 * @return 
	 * @throws
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	

	
	/**
	 * @author Rylan 2015年5月28日 下午2:16:30
	 * @Method: setCookieValueByName 
	 * @Description: TODO 修改cookie对应的值
	 * @param request
	 * @param name
	 * @param value 
	 * @throws
	 */
	public static void setCookieValueByName(HttpServletRequest request,HttpServletResponse response,String name, String value,int maxAge) {
		Cookie[] cookies = request.getCookies();
		if (cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (name.equalsIgnoreCase(cookies[i].getName())) {
					cookies[i].setPath("/");
					cookies[i].setValue(value);
					cookies[i].setMaxAge(maxAge);
					response.addCookie(cookies[i]);
				}
			}
		}
	}
	/**
	 * @author Rylan 2015年5月28日 下午3:01:02
	 * @Method: addCookieValue 
	 * @Description: TODO 有就更新没有就新增
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge  单位是秒
	 * @throws
	 */
	public static void setCookieValue(HttpServletRequest request,HttpServletResponse response,String name, String value,int maxAge) {		
		Cookie cookieValue=getCookieByName(request,name);
		
		if(cookieValue!=null){
			setCookieValueByName(request,response,name,value,maxAge);
		}else{
			addCookie(response,name,value,maxAge);
		}
	}
	
	/**
	 * @author Rylan 2015年8月17日 下午12:20:20
	 * @Method: deleteCookieValue 
	 * @Description: TODO  销毁cookie
	 * @param request
	 * @param response
	 * @param name 
	 * @throws
	 */
	public static void deleteCookieValue(HttpServletRequest request,HttpServletResponse response,String name) {		
		Cookie cookie=getCookieByName(request,name);
		if(cookie!=null){
			cookie.setMaxAge(0);
     		cookie.setPath("/");
     		response.addCookie(cookie);
		}	
		request.getSession().invalidate();// 必须销毁session对象,否则会出现没有退出的情况
			
	}
	
	/**
	 * @author Rylan 2015年10月19日 下午2:40:10
	 * @Method: setDomain 
	 * @Description: 添加跨域共享cookie，默认https不支持 
	 * @param request
	 * @param response
	 * @param name
	 * @param domain 
	 * @throws
	 */
	public static void setDomain(HttpServletRequest request,HttpServletResponse response,String name,String value,String domain) {				
		   Cookie cookie=getCookieByName(request, name);
	       if(cookie==null){
	    	   cookie=new Cookie(name, value);
	       }
		   cookie.setDomain(domain);
	       response.addCookie(cookie);
			
	}
	/**
	 * @author Rylan 2015年10月28日 下午4:32:32
	 * @Method: setDomain 
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param domain
	 * @param httpsSupport 对https的支持 
	 * @throws
	 */
	public static void setDomain(HttpServletRequest request,HttpServletResponse response,String name,String value,String domain,String httpsSupport) {				
		   Cookie cookie=getCookieByName(request, name);
	       if(cookie==null){
	    	   cookie=new Cookie(name, value);
	       }
		   cookie.setDomain(domain);
		   //https 生效
		   if(httpsSupport.equals("true")){
			   cookie.setSecure(true);
		   }		   
	       response.addCookie(cookie);
			
	}
	
}
