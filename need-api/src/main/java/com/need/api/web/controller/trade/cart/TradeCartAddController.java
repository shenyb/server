package com.need.api.web.controller.trade.cart;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.trade.TradeCartService;
import com.need.common.domain.pub.Message;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
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
 * @author shenyb 2015年8月8日 下午12:24:03
 * @ClassName CartController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CART_ADD)
public class TradeCartAddController {
	private Logger logger = Logger.getLogger(TradeCartAddController.class);

	@Autowired
	private TradeCartService tradeCartService;

	@ResponseBody
	@ParamsValidate(value={
			@ParamValidate(name="goodsId",notNull=true,code=3001),
			@ParamValidate(name="userId",notNull=true,code=2015)})
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message addToCart(@RequestParam(required = true) String goodsId,
			@RequestParam(required = true) Integer goodsCount, @RequestParam(required = true) String userId)
					throws Exception {
		logger.info(String.format("TradeCartAddController.addToCart:params:%s,%s,%s", goodsId,goodsCount,userId));
		// 先使用userId,goodsId查询，如果有就累加没有就新建
		Message success = Message.success();
		int result = tradeCartService.updateTradeCart(goodsId, goodsCount, userId);
		return result == Message.SUCCESS ? success : Message.error(result);
	}
}
