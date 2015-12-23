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
 * @author shenyb 2015年8月10日 上午10:50:52
 * @ClassName CancelTradeController
 * @Description 取消订单
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月10日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CANCEL)
public class CancelTradeController {
	@Autowired
	private TradeManager tradeManager;
	private static final Logger logger = Logger.getLogger(CancelTradeController.class);

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message cancelTrade(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String tradeNo) {
		logger.info(String.format("CancelTradeController params: userId:%s tradeNo: %s ", userId, tradeNo));
		int result = tradeManager.cancel(tradeNo, userId);
		return result == Message.SUCCESS ? Message.success() : Message.error(result);
	}

}
