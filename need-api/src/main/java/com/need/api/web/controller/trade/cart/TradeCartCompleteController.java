package com.need.api.web.controller.trade.cart;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.TradeCartCompleteParma;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年8月11日 上午11:54:42
 * @ClassName TradeCartCompleteController
 * @Description 购车完成完成接口
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月11日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CART_CREATE_ORDER)
public class TradeCartCompleteController {

	@Autowired
	private TradeBaseService tradeBaseService;
	private static final Logger logger = Logger.getLogger(TradeCartCompleteController.class);

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message tradeCartComplete_V1(HttpServletRequest request, TradeCartCompleteParma param, String userId) {
		return Message.error(BizReturnCode.TRADE_SYSTEM_VERSION_TOO_LOW);

//		logger.info("tradeCartComplete_V1 in.parm.getGoodsList :" + param.getGoodsList() + "  userId :"
//				+ param.getUserId());
//		Message message = Message.success();
//		TradeCartVO[] tradeCartVOArray = JSON.parseObject(param.getGoodsList(), new TypeReference<TradeCartVO[]>() {
//		});// 转换对象
//		String tradeNo = BizCodeUtil.generateTradeNo(tradeCartVOArray[0].getGoodsId(), userId);// 生成交易号
//		Message result = tradeBaseService.addTradeBaseService(tradeCartVOArray, userId, tradeNo);
//		if (result.getCode() == Message.SUCCESS) {// edit 判断库存是否充足
//			message.addData("tradeNo", tradeNo);
//			return message;
//		}
//		return result;
	}
}
