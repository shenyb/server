package com.need.api.web.controller.pub;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.trade.TradeLogisticsService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>请求全国数据</p>
 * @author Rylan 2015年8月13日 下午12:21:04
 * @ClassName LogisticsController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月13日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.PUBLIC_GET_LOGISTICS)
public class LogisticsController {
		
	private static final Logger logger = Logger.getLogger(LogisticsController.class);
	
	@Autowired
	private TradeLogisticsService tradeLogisticsService;
	/**
	 * @author Rylan 2015年8月13日 下午1:13:37
	 * @Method: getUpLoadToken 
	 * @Description: TODO
	 * @return
	 * @throws Exception 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getUpLoadToken() throws Exception {
		logger.info("in LogisticsController " );
		
		Message message = Message.success();
		message.addData("province",tradeLogisticsService.queryTradeLogistics());
		logger.info("out LogisticsController " );
		return message;
	}
	
	
}
