package com.need.show.web.controller.trade;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.show.pub.ConstantsProConfig;
import com.need.show.pub.Message;
import com.need.show.service.trade.TradeBaseService;
import com.need.show.web.controller.trade.vo.ShowTradeInfoVO;


@Controller
public class ShowTradeIndoController {

	private static final Logger logger = Logger.getLogger(ShowTradeIndoController.class);

	@Autowired
	private ConstantsProConfig  constantsProConfig;
	@Autowired
	private TradeBaseService tradeBaseService;
	
	
	@ResponseBody
	@RequestMapping(value="showTradeInfo",method = RequestMethod.GET)
	public Message statisticsTradeAndUser(Model model) {
		logger.info("statisticsTradeAndUser  in ..begin time is :"+constantsProConfig.getStatisticsBeginTime());
		Message success=Message.success();					
		ShowTradeInfoVO  showTradeInfoVO =tradeBaseService.statisticsTradeAndUserInfo(constantsProConfig.getStatisticsBeginTime());
		showTradeInfoVO.setTotalTradePrice(showTradeInfoVO.getTotalTradePrice());
		logger.info(" showTradeInfoVO  :"+showTradeInfoVO);
		success.addData("object", showTradeInfoVO);		
		
		return success;
		
	}
	
	
	
}
