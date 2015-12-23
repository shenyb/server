package com.need.api.web.controller.trade.order;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月9日 下午3:03:40
 * @ClassName CreateTrade
 * @Description 立即购买的接口
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月9日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_GOODS_CREATE_TRADE)
public class CreateTradeController {
	@Autowired
	private TradeBaseService tradeBaseService;
	private static final Logger logger = Logger.getLogger(CreateTradeController.class);

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message createTrade(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String goodsId, @RequestParam(required = true) Integer buyCount) {
		return Message.error(BizReturnCode.TRADE_SYSTEM_VERSION_TOO_LOW);
//
//		logger.info(String.format("CreateTradeController.createTrade params:userId:%s,goodsId:%s,buyCount:%s", userId, goodsId, buyCount));
//		Message message =  Message.success();
//		message = tradeBaseService.createTrade(message, userId, goodsId, buyCount);
//		
//		return message;
	}
}
