package com.need.api.web.controller.device;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.device.DeviceFeedBackDAO;
import com.need.common.domain.po.api.device.DeviceFeedBackPO;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p></p>
 * @author peiboli 2015年8月19日 下午7:24:18
 * @ClassName UserNeedCommentController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月19日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USER_FEEDBACK)
public class FeedBackController {

	private static final Logger logger = Logger.getLogger(FeedBackController.class);

	@Autowired
	private DeviceFeedBackDAO  userNeedCommentDAO;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0",method = RequestMethod.POST)
	public Message userNeedComment(DeviceFeedBackPO comment) {
		Message success = Message.success();
		logger.info("getUserInfo.....");
		try{
		userNeedCommentDAO.insertFeedBack(comment);
		}catch(Exception e){
			
			return Message.error(BizReturnCode.FEED_BACK_NO_SUPPORT_EMOJN);
		}
		return success;
	}


	
}
