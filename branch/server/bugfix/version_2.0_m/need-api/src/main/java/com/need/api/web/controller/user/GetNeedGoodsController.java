package com.need.api.web.controller.user;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.need.NeedService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(value = ControllerMappings.USERGOODSLIST)
public class GetNeedGoodsController {

	private static final Logger logger = Logger.getLogger(GetTradeGoodsController.class);
	
	@Autowired
	private NeedService needService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0",method = RequestMethod.GET)
	public Message getNeedGoodsList(@RequestParam(required = true) String userId,
			@RequestParam(required = true)Integer pageNum,@RequestParam(required = true) Integer pageSize) {
		logger.info(String.format("getNeedGoodsList....in...params:%s %d %d", userId,pageNum,pageSize));

		return needService.getNeedGoodsList(userId,pageNum,pageSize,Boolean.FALSE );
	}
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1",method = RequestMethod.GET)
	public Message getNeedGoodsList_v1_1(@RequestParam(required = true) String userId,
			@RequestParam(required = true)Integer pageNum,@RequestParam(required = true) Integer pageSize) {
		logger.info(String.format("getNeedGoodsList....in...params:%s %d %d", userId,pageNum,pageSize));
        
		return needService.getNeedGoodsList(userId,pageNum,pageSize );
	}

	
}
