package com.need.api.web.controller.trade.cart;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.trade.TradeCartService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.EditTradeCardParma;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 编辑用户购物车数量业务操作
 * </p>
 * 
 * @author Rylan 2015年8月8日 下午2:46:59
 * @ClassName EditCountTradeCartController
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CART_EDIT)
public class EditCountTradeCartController {
	@Autowired
	private TradeCartService tradeCartService;
	private static final Logger logger = Logger.getLogger(EditCountTradeCartController.class);

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message editTradeCart_V1(HttpServletRequest request, EditTradeCardParma param) {
		logger.info("editTradeCart_V1....in...params:" + param);
		Message message = Message.success();
		tradeCartService.updateGoodsCart(param);
		logger.info("editTradeCart_V1....out...params:" + param);
		return message;
	}
}
