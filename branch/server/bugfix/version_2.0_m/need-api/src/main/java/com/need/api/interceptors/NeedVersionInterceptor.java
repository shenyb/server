package com.need.api.interceptors;

import com.need.common.core.dao.jdbc.user.UserDeviceRelDAO;
import com.need.common.domain.po.api.user.UserDeviceRel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>need版本拦截器，用户统一记录用户使用的need版本信息</p>
 * @author Rylan 2015年10月28日 下午12:56:33
 * @ClassName NeedVersionInterceptor
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年10月28日
 * @modify by reason:{方法名}:{原因}
 */
public class NeedVersionInterceptor extends HandlerInterceptorAdapter  {

	private static final Logger logger = Logger.getLogger(NeedVersionInterceptor.class);
	
	@Autowired
	private UserDeviceRelDAO userDeviceRelDAO;
	
	/**
	 * @author Rylan 2015年10月28日 下午12:57:59
	 * @Method: preHandle 
	 * @Description: 处理need版本
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
		logger.info("NeedVersionInterceptor.preHandle  in.. ");
		String protocolVerStr = request.getHeader("apiVersion");
		float protocolVer = Float.parseFloat(protocolVerStr);// 获取请求版本号
		//只处理1.3版本之后
		if(protocolVer < 1.3f ){
			logger.debug("protocolVer < 1.3f continue. ");
			return super.preHandle(request, response, handler);
		}
		
		String userId=request.getParameter("userId");
		if(StringUtils.isEmpty(userId)){
			logger.debug("userId isEmpty  continue. ");
			return super.preHandle(request, response, handler);
		}
		//获取need版本号和设备id信息
		String deviceId = request.getHeader("deviceId");
		String appVersion = request.getHeader("appVersion");
		UserDeviceRel userDeviceRel= userDeviceRelDAO.selectByUserId(userId);
		//没有新增.有则更新
		if(userDeviceRel==null){			
			userDeviceRel=new UserDeviceRel();	
			userDeviceRel.setAppVersion(appVersion);
			userDeviceRel.setDeviceId(deviceId);
			userDeviceRel.setUserId(userId);	
			logger.debug("begin insert userDeviceRel "+userDeviceRel);
			userDeviceRelDAO.insert(userDeviceRel);
		}else{
			if(!userDeviceRel.getAppVersion().equals(appVersion)||!userDeviceRel.getDeviceId().equals(deviceId)){
				userDeviceRel.setAppVersion(appVersion);
				userDeviceRel.setDeviceId(deviceId);
				logger.debug("begin update userDeviceRel "+userDeviceRel);
				userDeviceRelDAO.updateByUserId(userDeviceRel);	
			}
					
		}				
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		/** TODO Auto-generated method stub*/			
		
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
					throws Exception {
		/** TODO Auto-generated method stub*/
		//System.out.println("afterCompletion response:"+);
		System.out.println(handler);
		
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/** TODO Auto-generated method stub*/
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
	
	
	
	
	
}
