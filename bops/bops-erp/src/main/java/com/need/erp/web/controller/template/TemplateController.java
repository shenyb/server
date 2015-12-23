package com.need.erp.web.controller.template;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.need.common.validate.ValidatorUtil;
import com.need.erp.constant.ControllerMappings;
import com.need.dao.bops.template.BopsTemplateDAO;
import com.need.domain.po.bops.template.BopsTemplate;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.service.bops.template.TemplateService;
import com.need.service.constant.BizReturnCode;

/**
 * <p></p>
 * @author Rylan 2015年9月10日 下午2:50:57
 * @ClassName TemplateController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value=ControllerMappings.WEB_TEMPLATE_OPERATE)
public class TemplateController {
	
	private static final Logger  logger = Logger.getLogger(TemplateController.class);
	@Autowired
	TemplateService templateService;
	@Autowired
	BopsTemplateDAO bopsTemplateDAO;
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:40:25
	 * @Method: saveTemplate 
	 * @Description: TODO添加专题模板
	 * @param bopsTemplate
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
    public Message saveTemplate(@RequestBody BopsTemplate bopsTemplate,HttpServletRequest request) {	
		logger.info("saveTemplate in.."+JSON.toJSONString(bopsTemplate));
		Message success=Message.success();
		Set<ConstraintViolation<BopsTemplate>> result = ValidatorUtil.getInstance().validate(bopsTemplate);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		Boolean isExist= templateService.checkTemplateName(bopsTemplate.getTemplateName());
		if(isExist){
			return Message.error(5019);
		}
		templateService.saveTemplate(bopsTemplate);
		logger.info("saveTemplate out.."+JSON.toJSONString(bopsTemplate));
		return success;
	}
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:44:07
	 * @Method: updateTemplate 
	 * @Description: TODO更新专题模板
	 * @param bopsTemplate
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT,value="/{templateId}")
    public Message updateTemplate(@RequestBody BopsTemplate bopsTemplate) {	
		logger.info("saveTemplate in..");
		Message success=Message.success();
		templateService.updateTemplate(bopsTemplate);
		return success;
	}
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:44:30
	 * @Method: getTemplate 
	 * @Description: TODO查看专题
	 * @param templateId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value= "/{templateId}")
    public Message getTemplate(@PathVariable(value = "templateId") String templateId) {	
		logger.info("getTemplate in..templateId:"+templateId);
		Message success=Message.success();
	    BopsTemplate template =bopsTemplateDAO.getById(templateId); 
	    success.addData("object", template);
		return success;
	}
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:44:45
	 * @Method: getPageTemplate 
	 * @Description: TODO查看分类列表
	 * @param page
	 * @param pageSize
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/page",method=RequestMethod.GET)
    public Message getPageTemplate(Integer page,Integer pageSize) {	
		logger.info("getPageTemplate in..");
		Message success=Message.success();
		Page templatePage = new Page();
		if (page == null || "".equals(page) || pageSize == null || "".equals(pageSize)) {
			templatePage.setPage(0);
			templatePage.setPageSize(10);
		} else {
			templatePage.setPage(page);
			templatePage.setPageSize(pageSize);
		}		
		List<BopsTemplate> templateList= templateService.gettemplateList(templatePage);
		success.addData("list", templateList);
		success.addData("page", templatePage);
		return success;
	}
	
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:45:24
	 * @Method: deleteTemplate 
	 * @Description: TODO删除模板
	 * @param templateId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE,value="/{templateId}")
    public Message deleteTemplate(@PathVariable(value = "templateId") String templateId) {	
		logger.info("deleteTemplate in..param:"+templateId);
		Message success=Message.success();
		templateService.delete(templateId);
		return success;
	}
	
}
