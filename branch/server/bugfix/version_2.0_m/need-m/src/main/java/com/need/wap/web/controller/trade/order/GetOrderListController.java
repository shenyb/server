package com.need.wap.web.controller.trade.order;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.need.common.core.service.trade.TradeManager;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.TradeListVO;
import com.need.wap.constant.ControllerMappings;

@Controller
@RequestMapping(ControllerMappings.TRADE_ORDER_LIST)
public class GetOrderListController {
	private static final Logger logger = Logger.getLogger(GetOrderListController.class);

	@Autowired
	private TradeManager tradeManager;

	@ResponseBody
	@RequestMapping( method = RequestMethod.GET)
	public Message getOrderList(@RequestParam(required = true) String userId,
			@RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize,String tradeStatus) {
		logger.info("getOrderList.....orderStatus:"+tradeStatus);
		Message message =  Message.success();
		if(tradeStatus!=null&&tradeStatus.equals("")){//设置判断，sql不查询
			tradeStatus=null;
		}
		PageHelper.startPage(pageNum, pageSize);
		List<TradeListVO> list = tradeManager.getUserOrderlist(userId, pageNum, pageSize, tradeStatus, message);;
		message.addData("tradeList", list);
		return message;

	}
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.GET)
	public Message getOrderListV1_3(@RequestParam(required = true) String userId,
			@RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize,String tradeStatus) {
		logger.info("getOrderList.....orderStatus:"+tradeStatus);
		Message message =  Message.success();
		if(tradeStatus!=null&&tradeStatus.equals("")){//设置判断，sql不查询
			tradeStatus=null;
		}
		PageHelper.startPage(pageNum, pageSize);
		List<TradeListVO> list = tradeManager.getUserOrderlistV1_3(userId, pageNum, pageSize, tradeStatus, message);;
		message.addData("tradeList", list);
		return message;

	}

}
