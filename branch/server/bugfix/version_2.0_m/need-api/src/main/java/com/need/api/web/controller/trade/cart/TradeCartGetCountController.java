package com.need.api.web.controller.trade.cart;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.dao.jdbc.trade.TradeCartDAO;
import com.need.common.domain.pub.Message;
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
 * @author shenyb 2015年8月8日 下午12:24:03
 * @ClassName TradeCartGetCountController
 * @Description
 * @version V2.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CART_COUNT)
public class TradeCartGetCountController {

	@Autowired
	private TradeCartDAO tradeCartDAO;

	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
	public Message listCart(@RequestParam(required = true) String userId) {
		Message success = Message.success();
		success.addData("cartCount", tradeCartDAO.getGoodsCountByUserId(userId));
		return success;
	}

}
