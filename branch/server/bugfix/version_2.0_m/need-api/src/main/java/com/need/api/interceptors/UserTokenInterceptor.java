package com.need.api.interceptors;

import com.need.api.constant.Constants;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.domain.pub.Message;
import com.need.jedis.JedisSentinelClient;
import com.need.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Rylan 2015年7月30日 下午5:52:45
 * @ClassName UserTokenInterceptor
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月30日
 * @modify by reason:{方法名}:{原因}
 */
public class UserTokenInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(UserTokenInterceptor.class);

	private List<Object> noMappingObject;//具体到方法级别

	public List<Object> getNoMappingObject() {
		return noMappingObject;
	}

	public void setNoMappingObject(List<Object> noMappingObject) {
		this.noMappingObject = noMappingObject;
	}

	/**
	 * @author Rylan 2015年7月30日 下午5:52:56
	 * @Method: preHandle
	 * @Description: TODO preHandle()方法在业务处理器处理请求之前被调用
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//为了得到controller的method层。转换handler  未完待续
//		HandlerMethod handlerMethod = (HandlerMethod)handler;
//		String requestMethod = StringUtil.getSimpleMethod(handlerMethod.getMethod().toString());		
//		logger.info("UserTokenInterceptor . requestMethod :" + requestMethod);
//		if(noMappingObject!=null){
//			if(noMappingObject.contains(requestMethod)){
//				logger.info("requestMethod is : "+requestMethod+"  ignore this");
//				return true;
//			}
//		}	
		String userToken = request.getHeader("userToken");
		if (userToken == null) {
			//logger.error("the user userToken is null，return false!"); 
			logger.warn("the user userToken is null，return false!");
			HttpUtils.responseOutWithJson(response, Message.error(BizReturnCode.SYSTEM_USER_TOKEN_NOT_EXIST));
			return false;
		}
		String userId=null;
		try {
			userId = BizCodeUtil.getUserIdByToken(userToken);
		} catch (Exception e) {
			/** TODO Auto-generated catch block */
			HttpUtils.responseOutWithJson(response, Message.error(BizReturnCode.SYSTEM_USER_TOKEN_OLD));
            return false;
		}
		// 从缓存中取得用户信息,并使用最新的token  edit shenyb open 20150821
		String userTokenRedis = JedisSentinelClient.getString(userId);
		logger.debug("userTokenRedis :"+userTokenRedis);	
		if (userToken.equals(userTokenRedis)) {
			logger.debug("userTokenRedis ==userToken ");	
			request.setAttribute(Constants.APP_REQUEST_USER_INFO, userId);
			return super.preHandle(request, response, handler);
		}
		logger.debug("userTokenRedis !=userToken ");	
		HttpUtils.responseOutWithJson(response, Message.error(BizReturnCode.SYSTEM_USER_TOKEN_OLD));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
