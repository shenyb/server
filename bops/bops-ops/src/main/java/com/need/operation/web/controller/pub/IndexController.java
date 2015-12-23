package com.need.operation.web.controller.pub;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.need.domain.po.bops.user.BopsUser;
import com.need.operation.constant.UrlMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.user.BopsUserService;
import com.need.service.constant.Constants;

/**
 * <p></p>
 * @author Rylan 2015年10月14日 上午12:03:51
 * @ClassName IndexController
 * @Description 用户首页信息
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年10月14日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
public class IndexController {
	
	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	private BopsUserService bopsUserService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	/**
	 * @author Rylan 2015年10月14日 上午12:20:04
	 * @Method: index 
	 * @Description: 用户首页信息
	 * @param request
	 * @return 
	 * @throws
	 */
	@RequestMapping(value=UrlMappings.WEB_ADMIN_INDEX,method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model){
		logger.info("index in..");
		BopsUser bopsUser = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		
		if(bopsUser.getUserName().equals(constantsProConfig.getUserName())&&bopsUser.getUserPwd().equals(constantsProConfig.getUserPwd())){
			model.addAttribute("authLists","admin");		
		}else{
			List<String> authLists =bopsUserService.getAuthScopesByRoleIds(bopsUser.getRoleIds());	//查询权限列表				
			model.addAttribute("authLists",authLists);
		}
		
		return ViewMappings.WEB_ADMIN_INDEX_PAGE;
	}
	
	
}
