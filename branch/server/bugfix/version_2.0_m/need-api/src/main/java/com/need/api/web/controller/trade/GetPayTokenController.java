package com.need.api.web.controller.trade;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.pay.PayService;
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
 * @author shenyb 2015年8月10日 下午3:29:10
 * @ClassName GetPayTokenController
 * @Description 获取支付token
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月10日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.GET_PAY_SIGN)
public class GetPayTokenController {
	private static final Logger logger = Logger.getLogger(GetPayTokenController.class);
	@Autowired
	private PayService payService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getPaySign(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String tradeNo) {
		return Message.error(BizReturnCode.TRADE_SYSTEM_VERSION_TOO_LOW);
//
//		logger.info(String.format("GetPayTokenController.getPaySign params:userId:%s,tradeNo:%s", userId, tradeNo));
//		return payService.getAlipaySign(Message.success(), userId, tradeNo);
	}

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getPaySign_V1_1(
			@RequestParam(required = true) String userId,
			@RequestParam(required = true) String tradeNo,
			@RequestParam(required = false) String couponNo) {
		return Message.error(BizReturnCode.TRADE_SYSTEM_VERSION_TOO_LOW);
//
//		logger.info(String.format("GetPayTokenController.getPaySignV1_1 params:userId:%s,tradeNo:%s,couponNo:%s", userId, tradeNo, couponNo));
//		return payService.getAlipaySignV1_1(Message.success(), userId, tradeNo,couponNo);
	}
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.2", method = RequestMethod.GET)
	public Message getPaySign_V1_2(
			@RequestParam(required = true) String userId,
			@RequestParam(required = true) String tradeNo,
			@RequestParam(required = false) String couponNo) {
		logger.info(String.format("GetPayTokenController.getPaySignV1_1 params:userId:%s,tradeNo:%s,couponNo:%s", userId, tradeNo, couponNo));
		return payService.saveAndGetAlipaySignV1_2(Message.success(), userId, tradeNo,couponNo);
	}
}
