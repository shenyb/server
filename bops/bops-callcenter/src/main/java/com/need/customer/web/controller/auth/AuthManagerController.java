package com.need.customer.web.controller.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.customer.constant.ControllerMappings;
import com.need.customer.constant.ViewMappings;
import com.need.domain.po.bops.auth.BopsAuth;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.auth.BopsAuthVO;
import com.need.jedis.JedisClient;
import com.need.jedis.JedisSentinelClient;
import com.need.service.bops.auth.BopsAuthService;
import com.need.service.bops.user.BopsUserService;
import com.need.service.constant.Constants;
import com.need.utils.CookieUtil;

/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年7月27日 下午7:08:16
 * @ClassName AuthManagerController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月27日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.AUTH)
public class AuthManagerController {

	private static final Logger logger = Logger.getLogger(AuthManagerController.class);
	@Autowired
	private BopsAuthService bopsAuthService;
	@Autowired
	private BopsUserService bopsUserService;

	/**
	 * @author Rylan 2015年7月31日 下午1:13:58 @Method: addAuthInfo @Description:
	 *         TODO 添加操作 @param bopsAuth @return @throws
     * @param bopsAuth
     * @return 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value=("/add"))
	public Message addAuthInfo(BopsAuth bopsAuth) {
		logger.info(String.format("AuthManagerController.addAuthInfo", bopsAuth.toString()));
		Message success = Message.success();
		bopsAuth.setAuthStatus("1");// 默认可用,数据库为非空
		bopsAuthService.addBopsAuth(bopsAuth);
		logger.info("bopsAuth :" + bopsAuth);
		success.addData("object", bopsAuth);
		return success;
	}

	/**
	 * 
	 * @author shenyb 2015年7月31日 下午11:30:24 @Method:
	 *         deleteAuthInfo @Description: TODO @param authId @return @throws
     * @param authId
     * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete/{authId}", method = RequestMethod.POST)
	public Message deleteAuthInfo(@PathVariable(value = "authId") String authId) {
		logger.info("deleteAuthInfo in..authId:" + authId);
		Message success = Message.success();
		bopsAuthService.delete(Integer.valueOf(authId));
		return success;
	}

	/**
	 * 
	 * @author shenyb 2015年8月2日 上午2:52:35 @Method: getAuthIPage @Description:
	 *         TODO @param authId @return @throws
     * @param bopsAuthVO
     * @param model
     * @return 
	 */
	@RequestMapping(method = RequestMethod.GET,value="/page")
	public String getAuthPage(BopsAuthVO bopsAuthVO,Model model) {
		    logger.info("deleteAuthInfo in..authId:" + bopsAuthVO.toString());
		    model.addAttribute("list", bopsAuthService.selectByPage(bopsAuthVO));
		    model.addAttribute("page", bopsAuthVO);
			logger.info("bopsAuthVO :" + bopsAuthVO.toString());
		
		return ViewMappings.AUTH_LIST;
	}

	/**
	 * 
	 * @author shenyb 2015年7月31日 下午11:26:47 @Method:
	 *         updateAuthInfo @Description: TODO @param bopsAuth @return @throws
     * @param bopsAuth
     * @return 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public Message updateAuthInfo(BopsAuth bopsAuth) {
		logger.info("updateAuthInfo in..");
		bopsAuth.setAuthStatus("1");
		Message success = Message.success();
		bopsAuthService.update(bopsAuth);
		logger.info("bopsAuth :" + bopsAuth);
		return success;
	}

	/**
	 * 
	 * @author shenyb 2015年7月31日 下午11:30:24 @Method:
	 *         getUserAuthList @Description: TODO @param authId @return @throws
     * @param request
     * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/user/auths", method = RequestMethod.GET)
	public Message getUserAuthList(HttpServletRequest request) {
		Message success = Message.success();
		String key=CookieUtil.getCookieValue(request, Constants.COOKIET_BOPS_USER_TOKEN_KEY);
		BopsUser user = JedisSentinelClient.getObject(key, BopsUser.class);
		List<BopsAuth> auths = bopsUserService.getBopsUserAuths(user);
		success.addData("auths", auths);
		return success;
	}

}
