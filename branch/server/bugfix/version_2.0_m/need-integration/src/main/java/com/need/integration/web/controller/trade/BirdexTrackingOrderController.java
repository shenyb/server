package com.need.integration.web.controller.trade;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.integration.common.birdex.bean.tracking.TrackingOrderVO;
import com.need.integration.constant.ControllerMappings;
import com.need.integration.pub.Message;
import com.need.integration.service.bops.BirdexTradeService;

@Controller
@RequestMapping(value = ControllerMappings.BIRDEX_TRACKING_ORDERINFO)
public class BirdexTrackingOrderController {

	private static Logger logger = Logger.getLogger(BirdexTrackingOrderController.class);
	
	@Autowired
	private BirdexTradeService birdexTradeService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message getTrackingOrder(@RequestBody TrackingOrderVO trackingOrder){
		logger.info("trackingOrder: " + trackingOrder);
		Message message = Message.success();
		message.setCode(0);
		message.setMsg("操作成功");
		message.setData(null);
		birdexTradeService.saveTrackingOrderInfo(trackingOrder);
		
		return message;
	}
	
}
