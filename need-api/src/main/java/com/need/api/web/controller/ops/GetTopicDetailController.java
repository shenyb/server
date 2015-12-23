package com.need.api.web.controller.ops;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.ops.OpsTopicDAO;
import com.need.common.domain.po.api.ops.OpsTopicPO;
import com.need.common.domain.pub.Message;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p></p>
 * @author LXD 2015年8月8日 下午3:09:08
 * @ClassName GetHomeOps
 * @Description TODO  获取专题详情
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.TOPIC_DETAIL)
public class GetTopicDetailController {
	private static final Logger logger = Logger.getLogger(GetTopicDetailController.class);
	@Autowired
	private OpsTopicDAO opsTopicDAO;
	/**
	 * 
	 * @author LXD 2015年8月9日 上午10:29:56
	 * @Method: GetHotGoods 
	 * @Description: TODO 获取人气商品，需要加分页
 	 * @param userId
	 * @return 
	 * @throws
	 */
	@ParamsValidate(value={
			@ParamValidate(name="topicId",notNull=true,code=BizReturnCode.NEED_TOPIC_ID_NOT_EXIST)})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getTopicDetail(@RequestParam(required = true)String topicId){
		logger.info(String.format("getTopicDetail: topicId: %s", topicId) );
		Message success =Message.success();
		OpsTopicPO ops= opsTopicDAO.selectByPrimaryKey(Integer.parseInt(topicId));
		success.addData("topicId", ops.getTopicId().toString());
		success.addData("topicName", ops.getTopicName());
		success.addData("TopicImgKey", ops.getCoverKey());
		success.addData("content", ops.getContent());
		return success;
		
		
	}
	
}
