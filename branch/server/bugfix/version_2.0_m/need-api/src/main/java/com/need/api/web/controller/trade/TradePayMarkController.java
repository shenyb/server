package com.need.api.web.controller.trade;

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

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年8月16日 下午5:59:34
 * @ClassName TradePayMarkController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月16日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_PAY_MARK)
public class TradePayMarkController {

	private static final Logger logger = Logger.getLogger(TradePayMarkController.class);

	@Autowired
	private TradeBaseService tradeBaseService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message tradePayMark_V1(HttpServletRequest request, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String tradeNo, @RequestParam(required = true) String addressId,
			@RequestParam(required = true) String payChannel) {
		logger.info(String.format("tradePayMark_V1 in..params:", tradeNo, addressId, payChannel));
		Message message = Message.success();
		tradeBaseService.updateAddressAndChannelByTradeNo(tradeNo, addressId, payChannel);
		return message;
	}

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.POST)
	public Message tradePayMark_V1_1(HttpServletRequest request, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String tradeNo, @RequestParam(required = true) String addressId,
			@RequestParam(required = true) String payChannel) {
		logger.info(String.format("tradePayMark_V1 in..params:", tradeNo, addressId, payChannel));
		Message message = tradeBaseService.updateAddressAndChannelByTradeNo_V1_1(tradeNo, addressId, payChannel);
		return message;
	}

}
