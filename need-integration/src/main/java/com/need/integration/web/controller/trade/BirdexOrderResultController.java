package com.need.integration.web.controller.trade;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.need.integration.common.birdex.BirdexConstant;
import com.need.integration.common.birdex.bean.orderResult.OrderResultVO;
import com.need.integration.constant.ControllerMappings;
import com.need.integration.pub.Message;
import com.need.integration.service.bops.BirdexTradeService;

@Controller
@RequestMapping(value = ControllerMappings.BIRDEX_ORDER_RESULT)
public class BirdexOrderResultController {

	private static Logger logger = Logger.getLogger(BirdexOrderResultController.class);
	
	@Autowired
	private BirdexTradeService birdexTradeService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message getOrderResult(@RequestBody OrderResultVO orderResult){
		logger.info("orderResult: " + orderResult);
		Message message = Message.success();
		message.setCode(0);
		message.setMsg("操作成功");
		message.setData(null);
		String userTradeNo = orderResult.getLogisticsId();
		String birdexString = JSONObject.toJSONString(orderResult);
		birdexTradeService.birdexResultUpdate(userTradeNo, birdexString);
		/**
		 * 0 表示笨鸟返回成功
		 */
		if(BirdexConstant.BIRDEX_CODE_0.equals(orderResult.getCode())){
			birdexTradeService.birdexTradeResultUpdate(userTradeNo, orderResult);
		}
		/**
		 * 43001 表示笨鸟返回身份证照片审核失败
		 */
		else if(BirdexConstant.BIRDEX_CODE_43001 .equals(orderResult.getCode())){
			birdexTradeService.identityNotPass(userTradeNo, birdexString);
		}
		
		return message;
	}
}
