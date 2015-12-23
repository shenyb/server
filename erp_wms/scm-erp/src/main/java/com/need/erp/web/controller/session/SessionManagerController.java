package com.need.erp.web.controller.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.erp.constant.ControllerMappings;
import com.need.erp.constant.UrlMappings;
import com.need.erp.constant.ViewMappings;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.user.UserInfoVO;
import com.need.jedis.JedisClient;
import com.need.jedis.JedisSentinelClient;
import com.need.service.bops.user.BopsUserService;
import com.need.service.constant.Constants;
import com.need.service.constant.ConstantsTimeOut;
import com.need.utils.CookieUtil;
import com.need.utils.MD5Util;

/**
 * <p></p>
 * @author Rylan 2015年7月30日 上午10:47:32
 * @ClassName SessionManagerController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月30日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_LOGIN)
public class SessionManagerController {

	Logger logger=Logger.getLogger(this.getClass());

	@Autowired
	private BopsUserService bopsUserService;
	/**
	 * @author Rylan 2015年7月30日 上午10:51:25
	 * @Method: createSession 
	 * @Description: TODO 登陆(相当于创建session),使用@RequestBody 进行封装
	 * @param id
	 * @return 
	 * @throws
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String createSession(UserInfoVO userInfo,HttpServletResponse response,HttpServletRequest request,Model model) {
		logger.info("createSession in..");
		String md5Password= MD5Util.MD5Encode(userInfo.getPassword());
		logger.info("username :"+userInfo.getUsername()+" password :"+md5Password); 
		StringBuffer userkey=new StringBuffer();
		BopsUser bopsUser=new BopsUser();
		boolean flag=bopsUserService.loginService(userInfo.getUsername(), md5Password,userkey,bopsUser);
		logger.info("flag :"+flag+" userkey:"+userkey);
		if(flag){
			String oldToken=CookieUtil.getCookieValue(request, Constants.COOKIET_BOPS_USER_TOKEN_KEY);
			if(oldToken!=null){
				JedisSentinelClient.del(oldToken);//清除token
			}
			CookieUtil.setCookieValue(request, response, Constants.COOKIET_BOPS_USER_TOKEN_KEY, userkey.toString(), ConstantsTimeOut.USER_COOKIE_EXPIRE_TIME);
			System.out.println(bopsUser);
			request.getSession().setAttribute(Constants.WEB_SESSION_USER_INFO,bopsUser);
			return "redirect:"+UrlMappings.WEB_ADMIN_INDEX;//主页
		}
		model.addAttribute("message", "用户名或密码错误!");
		return ViewMappings.WEB_LOGIN_PAGE;//登陆失败
	}
	
	/**
	 * @author Rylan 2015年7月30日 下午8:16:52
	 * @Method: destroySession 
	 * @Description: TODO 注销
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE)
	public Message destroySession(HttpServletRequest request, HttpServletResponse response) {
		logger.info("destroySession in..");
		Message success = Message.success();
		String userToken=request.getHeader("token");	
		logger.info(userToken);
		Cookie cookie = CookieUtil.getCookieByName(request, Constants.COOKIET_BOPS_USER_TOKEN_KEY);	
		if(userToken==null&&cookie!=null){//如果不携带
			userToken=cookie.getValue();
	    }
		if(cookie != null){	   
			CookieUtil.deleteCookieValue(request, response, userToken);//清除cookie
		}		
		JedisSentinelClient.del(userToken);//清除缓存
		success.addData("object", "");
		return success;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/toLoginOut" )
	public String destroySession_GET(HttpServletRequest request, HttpServletResponse response) {
		logger.info("destroySession in..");
		Message success = Message.success();
		String userToken=request.getHeader("token");	
		logger.info(userToken);
		Cookie cookie = CookieUtil.getCookieByName(request, Constants.COOKIET_BOPS_USER_TOKEN_KEY);	
		if(userToken==null&&cookie!=null){//如果不携带
			userToken=cookie.getValue();
	    }
		if(cookie != null){	   
			CookieUtil.deleteCookieValue(request, response, userToken);//清除cookie
		}		
		JedisSentinelClient.del(userToken);//清除缓存
		success.addData("object", "");
		return ViewMappings.WEB_LOGIN_PAGE;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/toLogin")
	public String toLogin(String message, Model model) {
		logger.info("toLogin in.. message"+message);		
		model.addAttribute("message", message);
		return ViewMappings.WEB_LOGIN_PAGE;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/to405Page")
	public String to405Page(String message, Model model) {
		return ViewMappings.WEB_REEOR_405_PAGE;
	}
}
