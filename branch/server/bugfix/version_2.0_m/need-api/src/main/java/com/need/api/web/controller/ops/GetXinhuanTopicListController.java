package com.need.api.web.controller.ops;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.dao.jdbc.ops.OpsTopicTemplateDAO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.ops.XinhuanTemplateVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 
 * <p></p>
 * @author peiboli 2015年9月10日 下午2:58:44
 * @ClassName GetXinhuanTopicListController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.OPS_XINHUAN_SHOW)
public class GetXinhuanTopicListController {
	private static final Logger logger = Logger.getLogger(GetTopicDetailController.class);
	
	@Autowired
    OpsTopicTemplateDAO opsTopicTemplateDAO;
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getTopicList(@RequestParam(required = true)String pageNum,
			@RequestParam(required = true)String pageSize){
		logger.info(String.format("getTopicList: params: %s %s", pageNum,pageSize) );
		Message message =Message.success();
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		Page<XinhuanTemplateVO> page= opsTopicTemplateDAO.queryByPage();
		List<XinhuanTemplateVO>  topicList = page.getResult();
		message.addData("totalCount",page.getTotal());
		message.addData("topicList", topicList);
		logger.info(String.format("getTopicList: params: %s %s", pageNum,pageSize) );
		return message;
		
		
	}


}
