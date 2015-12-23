package com.need.api.web.controller.trade.order;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.po.api.coupon.CouponUserPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.OrderBaseVO;
import com.need.common.domain.vo.trade.TradeAddressVO;
import com.need.common.domain.vo.trade.TradeBaseResult;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(ControllerMappings.GET_TRADE_ORDER_DETAIL)
public class GetOrderInfoController {

	private static final Logger logger = Logger.getLogger(GetOrderInfoController.class);

	@Autowired
	private TradeBaseService tradeBaseService;

	@Autowired
	private CouponService couponService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getOrderDetail(HttpServletRequest request, @RequestParam(required = true) String tradeNo,
			@RequestParam(required = true) String userId) {
		logger.info("getOrderDetail in..tradeNo :" + tradeNo);
		Message message = Message.success();
		List<TradeBaseResult> baseList = tradeBaseService.selectTradeBaseByOrderNo(tradeNo, userId);
		if (baseList == null || baseList.size() == 0) {
			return message;
		}
		// 封装交易基本信息
		message.addData("payChannel", baseList.get(0).getPayChannel());
		message.addData("payAmount", baseList.get(0).getPayAmount());
		message.addData("tradeNo", baseList.get(0).getTradeNo());
		message.addData("tradeTime", baseList.get(0).getTradeTime().getTime());
		message.addData("userTradeNo", baseList.get(0).getUserTradeNo());
		message.addData("transportFee", baseList.get(0).getTransportFee());

		// 封装地址信息
		TradeAddressVO addressVO = new TradeAddressVO();
		BeanUtils.copyProperties(baseList.get(0), addressVO);

		addressVO.setMyAddress(baseList.get(0).getLogisticsValue() + "-" + baseList.get(0).getAddress());
		message.addData("address", addressVO);

		List<OrderBaseVO> orderList = new ArrayList<OrderBaseVO>();
		for (TradeBaseResult tradeBaseResult : baseList) {
			OrderBaseVO orderBaseVO = new OrderBaseVO();
			BeanUtils.copyProperties(tradeBaseResult, orderBaseVO);
			orderList.add(orderBaseVO);
		}
		message.addData("orderList", orderList);
		CouponUserPO couponUserPO = couponService.getCouponUserByTradeNo(tradeNo);
		if (couponUserPO != null) {
			message.addData("coupon", couponUserPO);
		}
		return message;

	}

	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
	public Message getOrderDetailV2_0(HttpServletRequest request, @RequestParam(required = true) String tradeNo,
			@RequestParam(required = true) String userId) {
		logger.info("getOrderDetail in..tradeNo :" + tradeNo);
		Message message = Message.success();
		List<TradeBaseResult> baseList = tradeBaseService.selectTradeBaseByOrderNo(tradeNo, userId);
		if (baseList == null || baseList.size() == 0) {
			return message;
		}
		// 封装交易基本信息
		message.addData("payChannel", baseList.get(0).getPayChannel());
		message.addData("payAmount", baseList.get(0).getPayAmount());
		message.addData("tradeNo", baseList.get(0).getTradeNo());
		message.addData("tradeTime", baseList.get(0).getTradeTime().getTime());
		message.addData("userTradeNo", baseList.get(0).getUserTradeNo());
		message.addData("transportFee", baseList.get(0).getTransportFee());
		// 添加佣金
		int commission = baseList.get(0).getCommission();
		if (commission > 0) {
			message.addData("useCommission", commission);
		}
		// 封装地址信息
		TradeAddressVO addressVO = new TradeAddressVO();
		BeanUtils.copyProperties(baseList.get(0), addressVO);

		addressVO.setMyAddress(baseList.get(0).getLogisticsValue() + "-" + baseList.get(0).getAddress());
		message.addData("address", addressVO);

		List<OrderBaseVO> orderList = new ArrayList<OrderBaseVO>();
		for (TradeBaseResult tradeBaseResult : baseList) {
			OrderBaseVO orderBaseVO = new OrderBaseVO();
			BeanUtils.copyProperties(tradeBaseResult, orderBaseVO);
			orderList.add(orderBaseVO);
		}
		message.addData("orderList", orderList);
		CouponUserPO couponUserPO = couponService.getCouponUserByTradeNo(tradeNo);
		if (couponUserPO != null) {
			message.addData("coupon", couponUserPO);
		}
		return message;

	}

}
