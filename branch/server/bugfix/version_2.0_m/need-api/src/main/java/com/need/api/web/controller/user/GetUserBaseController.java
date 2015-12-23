package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.GetUserInfoParam;
import com.need.common.domain.vo.user.UserInfoParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value=ControllerMappings.USERINFO_GET_USERINFOLIST)
public class GetUserBaseController {
	
	private static final Logger logger=Logger.getLogger(GetUserBaseController.class);
		
	@Autowired
	private UserService userService;
	
  
   @ResponseBody
   @RequestMapping(params="apiVersion=1.0")
   public Message getVersionV1_0(GetUserInfoParam param) {
      Message success=Message.success();
      logger.info("getVersionV1_0.....");
      UserInfoParam userInfoParam=new UserInfoParam();
      if(param.getPageSize()!=null){//自定义每页数量
    	  userInfoParam.setCurrentPage(param.getCurrentPage());
    	  userInfoParam.setPageSize(param.getPageSize());
      }
      
      List<UserBasePO>  userBaseList= userService.getUserBaseList(userInfoParam);
      for (UserBasePO userBase : userBaseList) {
		 System.out.println(userBase);
	  }
      success.addData("userBaseList", userBaseList);
	  success.addData("totalPageCount", userInfoParam.getTotalPageCount());
      
      
      return success;
   }
  
  
	
}
