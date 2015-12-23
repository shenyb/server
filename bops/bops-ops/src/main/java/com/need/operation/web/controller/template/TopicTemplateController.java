	package com.need.operation.web.controller.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.need.dao.bops.ops.BopsOpsTopicCategoryDAO;
import com.need.dao.bops.template.BopsTemplateDAO;
import com.need.dao.bops.template.BopsTopicTemplateDAO;
import com.need.domain.po.bops.ops.BopsOpsTopicCategory;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.domain.vo.template.TemplatePageParamVO;
import com.need.domain.vo.template.TemplateParamVO;
import com.need.domain.vo.template.TemplateResultVO;
import com.need.http.HttpClientProxy;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.bops.template.TopicTemplateService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.CookieUtil;


@Controller
@RequestMapping(value=ControllerMappings.WEB_SPECIAL)
public class TopicTemplateController {
	
	private static final Logger  logger = Logger.getLogger(TopicTemplateController.class);
	

	@Autowired
	private TopicTemplateService  templateService;
	
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	@Autowired
	BopsOpsTopicCategoryDAO opsTopicCategoryDAO;
	@Autowired
	private BopsTopicTemplateDAO bopsTemplateDAO;
	

	
	/**
	 * @author Rylan 2015年9月7日 上午11:17:19
	 * @Method: saveTemplate 
	 * @Description: 保存模板文件
	 * @param templateContent
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
    public Message saveTopicTemplate(@RequestBody TemplateParamVO templateParamVO,Model model,HttpServletRequest request) {
		logger.debug("saveTemplate in..templateContent :"+templateParamVO);
		Message success = null;
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		
		Map<String, String> params= new HashMap<String,String>();	
		params.put(constantsProConfig.getTemplateUploadParam(), templateParamVO.getTopicContents());//设置参数
		/**
		 * 上传到share工程
		 */
		String jsonString=HttpClientProxy.sendPostRequest(constantsProConfig.getUploadShareUrl(), params, "UTF-8");
		success=JSON.parseObject(jsonString, new TypeReference<Message>() {});// 转换对象		
		if(success.getCode()!=BizReturnCode.SUCCESS){
			return Message.error(BizReturnCode.SYSTEM_ERR);
		}		
		
		String url =success.getData().get(constantsProConfig.getTemplateReturnResult()).toString();//获取返回地址
			
		templateParamVO.setVisitUrl(url);//设置访问地址
		//修改保存合并 modify by liyongran  20150918
		if(templateParamVO.getTopicId()!=null){
			logger.info("will be updateTempalteById .");		
			return templateService.updateTempalteById(templateParamVO);//修改操作
			
		}
		templateParamVO.setPublisherId(user.getUserId());//提交者
		success=templateService.saveTemplate(templateParamVO);		
		
		return success;
	}
	
	/**
	 * @author Rylan 2015年9月9日 下午9:15:18
	 * @Method: getPageTemplate 
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @param request
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/page",method = RequestMethod.GET)
    public String getPageTopicTemplate(TemplatePageParamVO vo,Model model) {	
		logger.info("getPageTopicTemplate in..vo:"+vo.toString());
		Page categoryPage =new Page();
		int total = opsTopicCategoryDAO.count();
		categoryPage.setTotal(total);
		categoryPage.setPageSize(Integer.MAX_VALUE);
		List<BopsOpsTopicCategory> categoryList =  opsTopicCategoryDAO.queryByPage(categoryPage);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("list",templateService.queryTemplate(vo));
		model.addAttribute("page",vo);
		return ViewMappings.TOPIC_LIST;
	}
	
	/**
	 * @author Rylan 2015年9月10日 下午3:02:30
	 * @Method: getTopicTemplate 
	 * @Description: TODO
	 * @param topicId
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/{topicId}",method = RequestMethod.GET)
    public Message getTopicTemplate(@PathVariable int topicId,HttpServletRequest request) {	
		logger.info("getTopicTemplate in..topicId:"+topicId);
		Message success=Message.success();	
		success.addData("object", templateService.getTempalteById(topicId));
		return success;
	}
	
	/**
	 * @author Rylan 2015年9月10日 下午3:02:35
	 * @Method: auditTopicTemplate 
	 * @Description: TODO
	 * @param vo
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/audit/{topicId}",method = RequestMethod.POST)
    public Message auditTopicTemplate(@PathVariable(value="topicId") int topicId,String auditStatus, HttpServletRequest request) {	
		logger.debug("auditTopicTemplate in..vo:"+topicId);
		Message success=Message.success();
		BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		TemplateResultVO resultVO= templateService.getTempalteById(topicId);
		TemplateParamVO vo =new TemplateParamVO();
		BeanUtils.copyProperties(resultVO, vo);
		vo.setAuditStatus(auditStatus);
		vo.setAuditorId(user.getUserId());
		templateService.auditTemplate(vo);		
		return success;
	}
	
	/**
	 * @author Rylan 2015年9月10日 下午3:02:40
	 * @Method: updateTopicTemplate 
	 * @Description: TODO  有待完善 确认要是内容修改了 还需上传share工程
	 * @param vo
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
    public Message updateTopicTemplate(@RequestBody TemplateParamVO vo,HttpServletRequest request) {	
		logger.info("updateTopicTemplate in..");
		Message success=Message.success();
		templateService.updateTempalteById(vo);		
		return success;
	}
	/**
	 * @author Rylan 2015年9月10日 下午3:02:49
	 * @Method: deleteTopicTemplate 
	 * @Description: TODO
	 * @param vo
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/{topicId}",method = RequestMethod.POST)
    public Message deleteTopicTemplate(@PathVariable(value="topicId") int topicId,HttpServletRequest request) {	
		logger.info("deleteTopicTemplate in..topicId:"+topicId);
		Message success=Message.success();	
		templateService.deleteTempalteById(topicId);
		return success;
	}
	

	
	@RequestMapping(value="/toAddUrl")
    public void toAddTopicURL(HttpServletResponse response) throws IOException {	
		logger.info("toAddUrl");
		System.out.println(constantsProConfig.getAddTopicURL());
		response.sendRedirect(constantsProConfig.getAddTopicURL());
	}
	
	@RequestMapping(value="/toEditUrl")
    public void toEditTopicURL(String topicId, HttpServletResponse response) throws IOException {	
		logger.info("toEditUrl topicId:"+topicId);
		TemplateResultVO topic= bopsTemplateDAO.getByTempalteId(Integer.parseInt(topicId));
		if(topic.getTopicVersion()==null){
		response.sendRedirect(constantsProConfig.getEditTopicURL()+topicId);
		}else{
			response.sendRedirect(constantsProConfig.getNewEditTopic()+topicId);	
		}
	}
	
}
