package com.need.api.web.controller.pub;

import com.need.api.constant.ControllerMappings;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p></p>
 * @author Rylan 2015年8月29日 下午6:21:01
 * @ClassName ReceiveReportController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月29日
 * @modify by reason:{方法名}:{原因}
 */

@Controller
@RequestMapping(ControllerMappings.PUBLIC_RECEIVE_REPORT)
public class ReceiveReportController {
	
	private static final Logger logger = Logger.getLogger(ReceiveReportController.class);
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message receiveReport(String userId,String feedId) {
		logger.info("ReceiveReportController1.0  in.. userId :"+userId +"feedId :" +feedId);

		return Message.success();
	}
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.POST)
	public Message receiveReport1(String userId,String feedId) {
		logger.info("ReceiveReportController1.1  in.. userId :"+userId +"feedId :" +feedId);

		return Message.success();
	}
	
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.2", method = RequestMethod.POST)
	public Message receiveReport2(String userId,String feedId) {
		logger.info("ReceiveReportController1.2  in.. userId :"+userId +"feedId :" +feedId);

		return Message.success();
	}
		
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.5", method = RequestMethod.POST)
	public Message receiveReport5(String userId,String feedId) throws Exception {
		logger.info("ReceiveReportController1.5  in.. userId :"+userId +"feedId :" +feedId);
        
		throw new NullPointerException();
		
		
	}
}
