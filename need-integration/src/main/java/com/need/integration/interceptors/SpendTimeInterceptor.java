package com.need.integration.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Ryaln 2015年6月24日 下午10:18:59
 * @ClassName RequestMappingInterceptor
 * @Description TODO 创建SpringMvc的拦截器，用于计算每一个方法从执行到生成返回值的耗时。
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Ryaln 2015年6月24日
 * @modify by reason:{方法名}:{原因}
 */
public class SpendTimeInterceptor extends HandlerInterceptorAdapter{

	private Logger logger=Logger.getLogger(this.getClass());
	
	/** 
	* @Fields spendTime : TODO 花费的时间
	*/ 
	private long spendTime;
	
	/**
	 * @author Ryaln 2015年6月24日 下午10:19:50
	 * @Method: afterCompletion 
	 * @Description: TODO afterCompletion() 方法在DispatcherServlet完全处理完请求后被调用   
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception) 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		/** TODO Auto-generated method stub*/
		logger.debug("用时："+(System.currentTimeMillis()-spendTime)+"ms");
		super.afterCompletion(request, response, handler, ex);
	}


	/**
	 * @author Ryaln 2015年6月24日 下午10:20:20
	 * @Method: postHandle 
	 * @Description: TODO postHandle()方法在业务处理器处理请求之后被调用   
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView) 
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/** TODO Auto-generated method stub*/
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * @author Ryaln 2015年6月24日 下午10:20:12
	 * @Method: preHandle 
	 * @Description: TODO preHandle()方法在业务处理器处理请求之前被调用  
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object) 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		/** TODO Auto-generated method stub*/
		spendTime=System.currentTimeMillis();
		return super.preHandle(request, response, handler);
	}

}
