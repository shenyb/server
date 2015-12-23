package com.need.operation.web.controller.ops;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.dao.api.template.OpsTopicTemplateDAO;
import com.need.domain.common.enums.CategoryPositionEnum;
import com.need.domain.po.api.template.OpsTopicTemplate;
import com.need.domain.po.bops.ops.BopsTopicCategory;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.TopicCategroyVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.ops.TopicCategoryService;
import com.need.service.constant.Constants;
import com.need.utils.DateUtil;



/***
 * 
 * <p></p>
 * @author LXD 2015年9月10日 上午11:21:29
 * @ClassName TopicCategoryManagerController
 * @Description TODO 专题分类管理（为专题匹配专题类型）
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value=ControllerMappings.TOPIC_CATEGORY_RELATION)
public class TopicCategoryManagerController {
	private static final Logger logger = Logger.getLogger(TopicCategoryManagerController.class);
	
	@Autowired
	private TopicCategoryService  topicCategoryService;
	@Autowired
	private OpsTopicTemplateDAO TopicTemplateDAO;
	@Autowired
	private ConstantsProConfig ConstantsProConfig;
	/**
	 * 
	 * @author LXD 2015年9月10日 下午3:37:54
	 * @Method: editTopicCategory 
	 * @Description: TODO 增加专题类型关联
	 * @param bopsTopicCategory
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message AddTopicCategory(@RequestBody TopicCategroyVO bopsTopicCategory,HttpServletRequest request){
		logger.info("AddTopicCategory in..TopicCategoryManagerController" + bopsTopicCategory.toString());
		Message success = Message.success();
		OpsTopicTemplate tmplate =TopicTemplateDAO.selectById(bopsTopicCategory.getTopicId());
		if (tmplate==null) {
			Message errorMessage=Message.error(3006);
			return errorMessage;
		}
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsTopicCategory.setPublisherId(user.getUserId().toString());
		return topicCategoryService.addCategory(bopsTopicCategory,success);
	}
	/***
	 * 
	 * @author LXD 2015年9月10日 下午4:42:20
	 * @Method: getTopicCategory 
	 * @Description: TODO 根据专题Id查询关联信息
	 * @param relationId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/{topicId}", method = RequestMethod.GET)
	public Message getTopicCategory(@PathVariable(value = "topicId") int topicId){
		logger.info("in TopicCategoryManagerController getTopicCategory relationId: " + topicId);
		Message message = Message.success();
		BopsTopicCategory topicCategory= topicCategoryService.getRelationByTopicId(topicId);
		if(topicCategory!=null){
			if (topicCategory.getEffDate() != null && topicCategory.getExpDate() != null) {
				try {
					topicCategory.setEffDateString(DateUtil.formatDateToStr(topicCategory.getEffDate(), "yyyy-MM-dd HH:mm:ss"));
					topicCategory.setExpDateString(DateUtil.formatDateToStr(topicCategory.getExpDate(), "yyyy-MM-dd HH:mm:ss"));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		 message.addData("sign",Boolean.TRUE.toString());	
		}else{
	     message.addData("sign",Boolean.FALSE.toString());	
		}
		message.addData("imgURl",ConstantsProConfig.getPic_domain() );
		message.addData("topicRelation",topicCategory);
		return message;
			
	}
	
	/**
	 * 
	 * @author LXD 2015年9月10日 下午6:30:39
	 * @Method: getPageOfOpsInfo 
	 * @Description: TODO 获取关系的分页数据
	 * @param topicCategroyVO
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getPageOfTopicCategory(TopicCategroyVO topicCategroyVO) {
		logger.info("in TopicCategoryManagerController getPageOfTopicCategory topicCategroyVO: " + topicCategroyVO.toString());
		Message message = Message.success();
		List<BopsTopicCategory> list = topicCategoryService.queryBopsTopicCategoryBypage(topicCategroyVO);
		message.addData("list", list);
		message.addData("page", topicCategroyVO);
		return message;
	}
	/***
	 * 
	 * @author LXD 2015年9月10日 下午6:44:49
	 * @Method: editTopicCategory 
	 * @Description: TODO 删除
	 * @param bopsTopicCategory
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/{relationId}",method = RequestMethod.DELETE)
	public Message deleteTopicCategory(@PathVariable(value = "relationId") int relationId) {
		logger.info("in TopicCategoryManagerController editTopicCategory relationId: " + relationId);
		Message message = Message.success();
		topicCategoryService.removeTopicCategory(relationId);
		return message;
	}
	
	/**
	 * 
	 * @author LXD 2015年9月11日 下午6:21:44
	 * @Method: editTopicCategory 
	 * @Description: TODO need1.1 增加或修改专题和专题分类关联
	 * @param bopsTopicCategory
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/{topicId}",method = RequestMethod.POST)
	public Message editTopicCategory(BopsTopicCategory bopsTopicCategory,HttpServletRequest request) {
		logger.info("in TopicCategoryManagerController editTopicCategory topicCategroyVO: " + bopsTopicCategory.toString());
		Message message = Message.success();
		OpsTopicTemplate tmplate =TopicTemplateDAO.selectById(bopsTopicCategory.getTopicId());
		if (tmplate==null) {
			Message errorMessage=Message.error(3006);
			return errorMessage;
		}
		if(StringUtils.isNotBlank(bopsTopicCategory.getEffDateString())&&StringUtils.isNotBlank(bopsTopicCategory.getExpDateString())){
			try {
				bopsTopicCategory.setEffDate(DateUtil.formatStrToDate(bopsTopicCategory.getEffDateString(), "yyyy-MM-dd HH:mm:ss"));
	        } catch (ParseException ex) {
	        	logger.error(ex.getMessage(), ex);
	        }
	        try {
	        	bopsTopicCategory.setExpDate(DateUtil.formatStrToDate(bopsTopicCategory.getExpDateString(), "yyyy-MM-dd HH:mm:ss"));
	        } catch (ParseException ex) {
	        	logger.error(ex.getMessage(), ex);
	        }
			}else{
				bopsTopicCategory.setEffDate(Calendar.getInstance().getTime());
				
				 try {
					 bopsTopicCategory.setExpDate(DateUtil.formatStrToDate(ConstantsProConfig.getExpDate(), "yyyy-MM-dd HH:mm:ss"));
			        } catch (ParseException ex) {
			        	logger.error(ex.getMessage(), ex);
			        }
			}
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsTopicCategory.setPublisherId(user.getUserId().toString());
		return topicCategoryService.editTopicCategory(bopsTopicCategory,message);
	}
	
	
	
	
}
