package com.need.share.web.controller.template;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.share.constant.Constants;
import com.need.share.constant.ControllerMappings;
import com.need.share.pub.ConstantsProConfig;
import com.need.share.pub.Message;
import com.need.share.service.template.TemplateService;
import com.need.share.service.topic.TopicService;
import com.need.share.util.DateUtil;
import com.need.share.util.UUIDUtils;

@Controller
@RequestMapping(value=ControllerMappings.WEB_TOPIC_OPERATE)
public class TopicTemplateController {
	
	private static final Logger  logger = Logger.getLogger(TopicTemplateController.class);
	
	@Autowired
	private TopicService topicService;	
	@Autowired
	private TemplateService  templateService;
	@Autowired
	private ConstantsProConfig  constantsProConfig;

	/**
	 * @throws IOException 
	 * @author Rylan 2015年9月5日 下午4:08:02
	 * @Method: detail 
	 * @Description: TODO
	 * @param goodsId
	 * @param model
	 * @return 
	 * @throws
	 */
//	@RequestMapping(name="share.htm",method = RequestMethod.GET)
//    public String detail(String goodsId,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
//		
//		String ftlPath = makeHtmlByFtl(topicService, goodsId, true, ShareTypeEnum.GOODS, "/ftls/topic/topic.ftl");	
//		System.out.println("ftlPath : "+ftlPath);
//		model.addAttribute("ftlPath", ftlPath);
//		
//		return "/goods/share";
//	}
	
	/**
	 * @author Rylan 2015年9月14日 上午11:44:52
	 * @Method: saveTopicTemplate 
	 * @Description: TODO
	 * @param topicContents
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="saveTopicTemplate",method = RequestMethod.POST)
    public Message saveTopicTemplate(String topicContents,String newFileName,HttpServletRequest request){
		logger.info("saveTopicTemplate in..topicContents:"+topicContents);
		logger.info("saveTopicTemplate in..newFileName:"+newFileName);
		if(topicContents==null){
			return Message.success();
		}
		Message success=Message.success();
		String nowStr=DateUtil.getYearMonthDaysStrByNow("yyyyMMdd");
		String saveDir;
		String visitDir = constantsProConfig.getVisitPath();//项目基础目录
		logger.info("visitDir............ "+visitDir);
		//modify by liuhongyang20151105 修改路径不一致的问题
		String fileName;
		String visitFileName;
		//topicId为空代表新增,否则为修改
		if(null == newFileName){
			fileName=UUIDUtils.getUUID()+Constants.HTML_SUFFIX;//文件名
			visitFileName=visitDir+nowStr+File.separator+fileName;//访问路径
			saveDir = request.getSession().getServletContext().getRealPath(constantsProConfig.getSavePath())+File.separator+nowStr+File.separator;//保存路径
			logger.info("new saveDir............ "+saveDir);
		}else{
			fileName=newFileName;
			visitFileName=visitDir+fileName;//访问路径
			saveDir = request.getSession().getServletContext().getRealPath(constantsProConfig.getSavePath())+"/";//保存路径
			logger.info("old saveDir............ "+saveDir);
		}
		
		
		
		logger.info("save "+saveDir+fileName);
		logger.info("visit "+visitFileName);
		
		try {
			FileUtils.writeByteArrayToFile(new File(saveDir+fileName), topicContents.getBytes());//保存到服务器
		} catch (IOException e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
			//logger.error(ex);
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();			
			logger.error("printStackTrace :"+str);
			return Message.error(1);
		}	
		success.addData("visitUrl", visitFileName);
		
		return success;
	}
	
}
