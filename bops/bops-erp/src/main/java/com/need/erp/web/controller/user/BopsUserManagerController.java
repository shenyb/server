package com.need.erp.web.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.need.common.validate.ValidatorUtil;
import com.need.dao.bops.role.BopsRoleDAO;
import com.need.domain.po.bops.role.BopsRole;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.role.RoleResultVO;
import com.need.domain.vo.user.BopsUserResultVO;
import com.need.domain.vo.user.QueryBopsUserVO;
import com.need.erp.constant.ControllerMappings;
import com.need.erp.constant.ViewMappings;
import com.need.framework.utils.PropertiesUtil;
import com.need.service.bops.user.BopsUserService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.StringUtil;

/**
 * 
 * <p></p>
 * @author xiehao 2015年8月4日 下午3:11:43
 * @ClassName BopsUserManagerController
 * @Description TODO Bops用户管理，对用户进行增删改查操作
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月4日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.BOPS_USER_MANAGER)
public class BopsUserManagerController {

	private static final Logger logger = Logger.getLogger(BopsUserManagerController.class);
	
	@Autowired
	private BopsUserService bopsUserService;
	
	@Autowired
	private BopsRoleDAO bopsRoleDAO;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "/add")
	public Message addBopsUserManager( BopsUser bopsUser){
		logger.info("in user addBopsUserManager: " + bopsUser);
		
		Set<ConstraintViolation<BopsUser>> result = ValidatorUtil.getInstance().validate(bopsUser);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		
		Message message = Message.success();
		if(bopsUser.getUserName().equals(PropertiesUtil.getProperty(Constants.CONSTANTS_PROPERTIES,"userName"))){//Addy liyongran 20150819 注册时增加验证 
			return Message.error(2001);		
		};	
		message.addData("object", bopsUserService.addBopsUserInfo(bopsUser));		
		logger.info("out user addBopsUserManager: " + bopsUser);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public Message getBopsUserInfo(@PathVariable Integer userId)
	{
		logger.info("in user getBopsUserInfo userId: " + userId);
		Message message = Message.success();
		message.addData("bopsUser", bopsUserService.getBopsUserInfoById(userId));
		logger.info("out user getBopsUserInfo userId: " + userId);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/put")
	public Message updateBopsUserInfo(  BopsUser bopsUser){
		logger.info("in user updateBopsUserInfo bopsUser: " + bopsUser);
		Set<ConstraintViolation<BopsUser>> result = ValidatorUtil.getInstance().validate(bopsUser);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		Message message = Message.success();
		bopsUserService.updateBospUserInfo(bopsUser);
		logger.info("out user updateBopsUserInfo bopsUser: " + bopsUser);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/{userId}")
	public Message deleteBopsUserById(@PathVariable Integer userId){
		logger.info("in user deleteBopsUserById userId: " + userId);
		Message message = Message.success();
		bopsUserService.deleteBospUserById(userId);
		logger.info("out user deleteBopsUserById userId: " + userId);
		return message;
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/page")
	public String getPageOfUserInfo(
			QueryBopsUserVO bopsUserVO,Model model){
		logger.info("in user getPageOfUserInfo bopsUserVO: " + bopsUserVO.toString());
		Message message = Message.success();
		List<BopsUser> bopsUserList = null;
		List<BopsUserResultVO> bopsUserResultList = null;	//modify by liyongran 20150811
			message.addData("page", bopsUserVO);	
		bopsUserList = bopsUserService.getPageOfBopsUser(bopsUserVO);
		bopsUserResultList=new ArrayList<BopsUserResultVO>();
		for (BopsUser bopsUser : bopsUserList) {//继续修改
			BopsUserResultVO bopsUserResultVO=new BopsUserResultVO();
//			System.out.println("bopsUser :"+bopsUser);
			BeanUtils.copyProperties(bopsUser,bopsUserResultVO);
//			System.out.println("bopsUserResultVO :"+bopsUserResultVO);
			String[] arrayRoleIds=JSON.parseObject(bopsUser.getRoleIds(), new TypeReference<String[]>(){});
			String  roleIds =StringUtil.arrayToFormatString(arrayRoleIds, ",");
			List<BopsRole> bopsRoles = bopsUserService.getBopsRolesByRoleId(roleIds);
			bopsUserResultVO.setBopsRoles(bopsRoles);
			bopsUserResultList.add(bopsUserResultVO);
		}
		List<RoleResultVO>  roleList = bopsRoleDAO.getAllRole();
		model.addAttribute("list", bopsUserResultList);
		
		model.addAttribute("roleList", roleList);
		model.addAttribute("page", bopsUserVO);
		logger.info("out user getPageOfUserInfo bopsUserVO: " + bopsUserVO.toString());
		return ViewMappings.SYSTEM_USER_LIST;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = ControllerMappings.BOPS_USER_CHANGE_PWD)
	public Message updateBopsUserPwd(String oldPwd,String newPwd,HttpServletRequest request) {
        BopsUser bopsUser = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        if(bopsUser == null) {
            return Message.error(6);
        }
		String adminName=PropertiesUtil.getProperty(Constants.CONSTANTS_PROPERTIES,"userName");
		if(bopsUser.getUserName().equals(adminName)){
            return Message.error(2002);
        }
		Message message = Message.success();
        
        Integer userId = bopsUser.getUserId();
        logger.info("user change pwd userId " + userId + " oldPwd " + oldPwd + " newPwd " + newPwd);
        int changed = bopsUserService.updateBospUserPwd(userId, oldPwd, newPwd);
        if(changed <= 0) {
            return Message.error(8);
        }
        return message;
    }
	
	
}
