package com.need.api.web.controller.trade.order;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.trade.TradeManager;
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
 * @Description 用户确认已经收货
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月9日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CONFIRM)
public class ConfirmTradeController {
	@Autowired
	private TradeManager tradeManager;
	private static final Logger logger = Logger.getLogger(ConfirmTradeController.class);

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message confirmTrade(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String tradeNo) {	
		logger.info("tradeNo :"+tradeNo +"userId :"+userId);
		int result = tradeManager.confirm(tradeNo);
		return result == Message.SUCCESS ? Message.success() : Message.error(result);
	}

}
