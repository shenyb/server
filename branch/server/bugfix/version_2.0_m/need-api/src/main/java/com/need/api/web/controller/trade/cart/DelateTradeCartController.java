package com.need.api.web.controller.trade.cart;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.trade.TradeCartService;
import com.need.common.domain.pub.Message;
import com.need.utils.StringUtil;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 删除购物车业务操作
 * </p>
 * 
 * @author Rylan 2015年8月8日 下午2:38:58
 * @ClassName DelateTradeCartController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CART_DELETE)
public class DelateTradeCartController {
	@Autowired
	private TradeCartService tradeCartService;
	private static final Logger logger = Logger.getLogger(DelateTradeCartController.class);

	@ParamsValidate({ @ParamValidate(name = "cartIds", notNull = true, code = BizReturnCode.REQUEST_PARAMS_NOT_NULL) })
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message deleteTradeCart_V1(HttpServletRequest request, String cartIds) {

		logger.info(String.format("deleteTradeCart_V1...in...cartIds:%s", cartIds));
		Message message = Message.success();
		String mutiCartIds = cartIds.replace("[", "").replace("]", "");
		if(!StringUtil.isBlank(mutiCartIds)){
			tradeCartService.deleteByPrimaryKey(mutiCartIds);
		}
		return message;
	}
}
