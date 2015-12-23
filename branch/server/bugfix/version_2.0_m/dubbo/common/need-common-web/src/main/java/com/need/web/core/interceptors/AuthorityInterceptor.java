package com.need.web.core.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author Rylan 2015年7月28日 上午1:20:58
 * @ClassName AuthorityInterceptor
 * @Description TODO 身份鉴权拦截器
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月28日
 * @modify by reason:{方法名}:{原因}
 */
public class AuthorityInterceptor implements HandlerInterceptor {
	
	private Logger logger=Logger.getLogger(this.getClass());

	public String[] allowUrls;
	
	public String[] getAllowUrls() {
		return allowUrls;
	}
	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		 //忽略设置好的url
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");  
        logger.debug("AuthorityInterceptor  requestUrl :"+requestUrl);
        if(null != allowUrls && allowUrls.length>=1)  
            for(String url : allowUrls) {    
                if(requestUrl.contains(url)) {
                	logger.debug("url is : "+url+"  ignore this");
                    return true;    
                }    
         } 
        
	
	
		
			
		logger.debug("auth is in authScopes .go on to deal!");	
		//request.setAttribute(Constants.WEB_REQUEST_USER_INFO, user);//此时用户数据可能不是最新，用的时候只用基本数据就好
		return true;
		
	}
	
	/**
	 * @author shenyb 2015年8月1日 下午3:03:53
	 * @Method: postHandle
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 *      java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		/** TODO Auto-generated method stub */
		
	}
	
	/**
	 * @author shenyb 2015年8月1日 下午3:03:53
	 * @Method: afterCompletion
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 *      java.lang.Object, java.lang.Exception)
	 */
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		/** TODO Auto-generated method stub */
		
	}
	
}
