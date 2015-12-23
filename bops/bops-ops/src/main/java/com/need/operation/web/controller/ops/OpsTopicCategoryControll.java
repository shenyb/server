package com.need.operation.web.controller.ops;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.need.common.validate.ValidatorUtil;
import com.need.dao.bops.ops.BopsOpsTopicCategoryDAO;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.po.bops.ops.BopsOpsTopicCategory;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.UrlMappings;
import com.need.operation.constant.ViewMappings;
import com.need.service.bops.ops.OpsTopicCategoryService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;

/**
 * 
 * <p></p>
 * @author peiboli 2015年9月9日 下午3:17:04
 * @ClassName OpsTopicCategoryControll
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年9月9日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.TOPIC_CATEGORY)
public class OpsTopicCategoryControll {
	
	private static final Logger logger = Logger.getLogger(OpsHotGoodsController.class);
	@Autowired
	OpsTopicCategoryService opsTopicCategoryService;
	@Autowired
	BopsOpsTopicCategoryDAO opsTopicCategoryDAO;
	
	@Autowired
	BopsOpsTopicCategoryDAO bopsOpsTopicCategoryDAO;
	
	/**
	 * 
	 * @author peiboli 2015年9月9日 下午3:24:30
	 * @Method: addTopicCategory 
	 * @Description: TODO添加专题分类
	 * @param bopsScene
	 * @param request
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addTopicCategory(BopsOpsTopicCategory topicCategory,HttpServletResponse response,HttpServletRequest request, Model model) {
		logger.info("addTopicCategory in...."+JSON.toJSONString(topicCategory));
		Set<ConstraintViolation<BopsOpsTopicCategory>> result = ValidatorUtil.getInstance().validate(topicCategory);
		if(result.size()>0){
			for(ConstraintViolation<BopsOpsTopicCategory> c:result){
				model.addAttribute("message", c.getMessage());
			}
		}
		int isexist = opsTopicCategoryDAO.selectCategoryName(topicCategory.getTopicCategoryName());
		if (isexist == 1) {//1代表专题分类名存在
			model.addAttribute("message", "专题分类名已存在");
		}
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		topicCategory.setPublisherId(user.getUserId());
		opsTopicCategoryService.addTopicCategory(topicCategory);
		logger.info("addTopicCategory...out..."+JSON.toJSONString(topicCategory));
		return UrlMappings.TO_TOPIC_CATEGORY;

	}
	/**
	 * 
	 * @author peiboli 2015年9月9日 下午8:24:05
	 * @Method: selectBopsScene 
	 * @Description: TODO获得专题分类的列表
	 * @param page
	 * @param pageSize
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET,value="/page")
	public String getBopsTopicCategorylist(
			Page categoryPage, Model model) {
		logger.info(String.format("getBopsTopicCategorylist..in...params:%s",categoryPage.toString()));
		//Message message = Message.success();
		int total = opsTopicCategoryDAO.count();
		categoryPage.setTotal(total);
		List<BopsOpsTopicCategory> category =  opsTopicCategoryDAO.queryByPage(categoryPage);
		model.addAttribute("list", category);
		model.addAttribute("page", categoryPage);
		logger.info(String.format("getBopsTopicCategorylist..out...params:%s", categoryPage.toString()));
		return ViewMappings.TOPIC_CATEGORY_LIST;

	}
	/**
	 * 
	 * @author peiboli 2015年9月10日 上午10:44:10
	 * @Method: removeById 
	 * @Description: TODO删除该专题分类
	 * @param topicCategoryId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/{topicCategoryId}")
	public Message removeById(
			@PathVariable(value = "topicCategoryId") int topicCategoryId) {
		logger.info(String.format("deleteById..in...params: %d  ",topicCategoryId));
		Message message = Message.success();
        Boolean isSuccess = opsTopicCategoryService.removeById(topicCategoryId);
        logger.info(String.format("deleteById..out...params: %d ",topicCategoryId));
        if(isSuccess){    	
        	return message;
        }else{
    		return Message.error(5016);
        }
		

	}
	/**
	 * 
	 * @author peiboli 2015年9月10日 上午11:44:10
	 * @Method: update 
	 * @Description: TODO更新专题名称
	 * @param topicCategoryId
	 * @return 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.POST,value="/edit")
	public String update(BopsOpsTopicCategory topicCategory) {
		logger.info("deleteById..in...params:"+JSON.toJSONString(topicCategory));
		//Message message = Message.success();
		int isexist = opsTopicCategoryDAO.selectCategoryName(topicCategory.getTopicCategoryName());
		if (isexist == 2) {//1代表专题分类名存在
			//return Message.error(5015);
		}
		Set<ConstraintViolation<BopsOpsTopicCategory>> result = ValidatorUtil.getInstance().validate(topicCategory);
		if(result.size()>0){
			for(ConstraintViolation<BopsOpsTopicCategory> c:result){
				//return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		BopsOpsTopicCategory Category=bopsOpsTopicCategoryDAO.getById(topicCategory.getTopicCategoryId());
		Category.setTopicCategoryName(topicCategory.getTopicCategoryName());
		Category.setCategoryScore(topicCategory.getCategoryScore());
        opsTopicCategoryService.update(Category);
		logger.info("deleteById..in...params:"+JSON.toJSONString(topicCategory)); 	
		return UrlMappings.TO_TOPIC_CATEGORY;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/audit/{topicCategoryId}")
	public Message audit(@PathVariable(value = "topicCategoryId") int topicCategoryId, String auditStatus, HttpServletRequest request) {
		logger.info("audit..in....params:"+topicCategoryId);
		Message message = Message.success();
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		BopsOpsTopicCategory Category=bopsOpsTopicCategoryDAO.getById(topicCategoryId);
		Category.setAuditorId(user.getUserId());
		Category.setAuditStatus(auditStatus);
		message= opsTopicCategoryService.auditOpsTopic(Category);
		logger.info("audit..out....params:"+topicCategoryId);
		return message;
	}

	/**
	 * 
	 * @author LXD 2015年10月19日 下午12:01:19
	 * @Method: getById 
	 * @Description: TODO 查看单个场景分类
	 * @param topicCategoryId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{topicCategoryId}")
	public Message getById(@PathVariable(value = "topicCategoryId") int topicCategoryId) {
		logger.info("audit..in....params:"+topicCategoryId);
		Message message = Message.success();
		BopsOpsTopicCategory Category=bopsOpsTopicCategoryDAO.getById(topicCategoryId);
		message.addData("category", Category);

		return message;
	}
	
}
