package com.need.api.web.controller.distribution;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = ControllerMappings.GET_CONSUME_LIST)
public class GetUserConsumeController {

	private static final Logger logger = Logger.getLogger(GetUserConsumeController.class);
	
	@Autowired
	private  TradeBaseService TradeBaseService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
	public Message getUserIncomeList(@RequestParam(required = true) int pageNum,@RequestParam(required = true) int pageSize,
			@RequestParam(required = true) String userId) {
		Message message=  TradeBaseService.getConsumeByUserId(userId,pageNum,pageSize);
		return message;
	}

}
