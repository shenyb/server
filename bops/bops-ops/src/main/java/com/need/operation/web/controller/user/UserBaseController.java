package com.need.operation.web.controller.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.domain.po.api.user.UserBase;
import com.need.domain.po.bops.basedata.BopsKolCategory;
import com.need.domain.pub.Message;
import com.need.domain.vo.user.UserInfoVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.kolcategory.BopsKolCategoryService;
import com.need.service.bops.user.UserBaseService;

/**
 * <p></p>
 * @author Rylan 2015年8月8日 下午4:08:58
 * @ClassName UserBaseController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.API_USER_BASE)
public class UserBaseController {

	private static final Logger logger = Logger.getLogger(UserBaseController.class);
	
	@Autowired
	private UserBaseService userBaseService;

	@Autowired
	private BopsKolCategoryService bopsKolCategoryService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value="/{mobile}",method = RequestMethod.GET)
	public Message getUserBaseByMobile(@PathVariable(value = "mobile") String mobile){
		logger.info("getUserBaseByMobile in ..");
		Message message = Message.success();	
		if(mobile==null||mobile.equals("")){
			return message;
		}
		UserBase userBase = userBaseService.getUserBaseByMobile(mobile);
		
		if(userBase==null){
			return  message.error(2001, "没有此用户");
		}
		List<BopsKolCategory> kolCategoryList =bopsKolCategoryService.getKolCategoryList();
		userBase.setProfilePicKey(constantsProConfig.getPic_domain()+userBase.getProfilePicKey());//modify by  liyongran 20150812 去掉域名前缀  
		message.addData("userbase", userBase);
		message.addData("kolCategoryList", kolCategoryList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value="/update",method = RequestMethod.PUT)
	public Message update( String username, String password, UserInfoVO userInfoVO){
		logger.info("update in .."+userInfoVO+"    username : "+username+" password :"+password);
		
		Message message = Message.success();	
		  
			
		return message;
	}
	
	
}
