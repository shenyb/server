package com.need.oscar.interceptor;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.framework.utils.PropertiesUtil;
import com.need.jedis.JedisClient;
import com.need.jedis.JedisSentinelClient;
import com.need.oscar.pub.ConstantsProConfig;
import com.need.service.bops.user.BopsUserService;
import com.need.service.constant.Constants;
import com.need.utils.CookieUtil;
import com.need.utils.HttpUtils;
import com.need.utils.StringUtil;

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
	
	private static final Logger logger=Logger.getLogger(AuthorityInterceptor.class);
	
	@Autowired
	private BopsUserService bopsUserService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	public String[] allowUrls;
	
	public String[] getAllowUrls() {
		return allowUrls;
	}
	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		 //忽略设置好的url
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");  
        logger.info("AuthorityInterceptor  requestUrl :"+requestUrl);
        if(null != allowUrls && allowUrls.length>=1)  
            for(String url : allowUrls) {    
                if(requestUrl.contains(url)) {
                	logger.info("url is : "+url+"  ignore this");
                    return true;    
                }    
         } 
        HttpSession session=  request.getSession();
        BopsUser user= (BopsUser) session.getAttribute(Constants.WEB_SESSION_USER_INFO);//由session中获取用户信息
        boolean loginFlag=true;//登陆标示
        if(user==null){
        	//鉴权用户信息
    		String token=CookieUtil.getCookieValue(request,Constants.COOKIET_BOPS_USER_TOKEN_KEY);//从cookie中获取token信息
    		logger.info("token  is :"+token);
    		String message="身份异常,重新登陆!";
    		//message=URLEncoder.encode(message, "UTF-8");
//    		if(token==null){
//    			//response.sendRedirect(request.getScheme()+"://"+request.getHeader("Host")+"/session/toLogin?message="+message);
//    			Message error=Message.error(6);
//    			error.addData("url", constantsProConfig.getOpsLoginUrl());
//    			HttpUtils.responseOutWithJson(response, error);
//    			return false;
//    		}
//    		user = JedisSentinelClient.getObject(token, BopsUser.class);
//    		if (user == null) {			
//    			//response.sendRedirect(request.getScheme()+"://"+request.getHeader("Host")+"/session/toLogin?message="+message);
//    			Message error=Message.error(6);
//    			error.addData("url", constantsProConfig.getOpsLoginUrl());
//    			HttpUtils.responseOutWithJson(response, error);
//    			return false;
//    		}
    		//暂时写死
    		user=new BopsUser();
    		user.setUserName(constantsProConfig.getUserName());
    		user.setUserPwd(constantsProConfig.getUserPwd());
    		user.setUserId(0);
        }
		
		logger.info("user :"+user+ " loginFlag :"+loginFlag);	
		
		/**/
		String adminName=constantsProConfig.getUserName();
		String adminPwd=constantsProConfig.getUserPwd();		
		if(user.getUserName().equals(adminName)&&user.getUserPwd().equals(adminPwd)){
			logger.info("auth is in authScopes .go on to deal!");
			request.setAttribute(Constants.WEB_REQUEST_USER_INFO, user);//此时用户数据可能不是最新，用的时候只用基本数据就好
			return true;
		}
		// 判断当前用户是否有操作当前请求方法的权限
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		String auth = StringUtil.getSimpleMethod(handlerMethod.getMethod().toString());
		//过滤首页
		if(auth.equals("IndexController.index")){
			request.setAttribute(Constants.WEB_REQUEST_USER_INFO, user);//此时用户数据可能不是最新，用的时候只用基本数据就好
    		return true;
		}
		//权限查询
		List<String> authLists  =bopsUserService.getAuthScopesByRoleIds(user.getRoleIds());	//查询权限列表		
		if (!authLists.contains(auth)) { //暂时去掉
			logger.warn("auth is not in authScopes.return false!");
			//response.sendRedirect(request.getScheme()+"://"+request.getHeader("Host")+"/session/to405Page");
			HttpUtils.responseOutWithJson(response, Message.error(3));
			return false;
			
		}		
		logger.info("auth is in authScopes .go on to deal!");	
		request.setAttribute(Constants.WEB_REQUEST_USER_INFO, user);//此时用户数据可能不是最新，用的时候只用基本数据就好
		
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
	
	@Override
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
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		/** TODO Auto-generated method stub */
		
	}
	
	public static void main(String[] args) {
		System.out.println(JedisClient.getObject("aa", BopsUser.class));
	}
	
}
