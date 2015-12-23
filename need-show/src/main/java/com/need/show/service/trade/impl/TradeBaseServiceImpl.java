package com.need.show.service.trade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.show.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.show.dao.jdbc.api.user.UserBaseDAO;
import com.need.show.service.trade.TradeBaseService;
import com.need.show.web.controller.trade.vo.ShowTradeInfoVO;

@Service
public class TradeBaseServiceImpl implements  TradeBaseService{

	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Autowired
	private UserBaseDAO userBaseDAO;
	
	
	@Override
	public ShowTradeInfoVO statisticsTradeAndUserInfo(String beginTime) {
		/** TODO Auto-generated method stub*/		
		ShowTradeInfoVO  showTradeInfoVO = tradeBaseDAO.getShowTradeInfo(beginTime);
		if(showTradeInfoVO==null){
			showTradeInfoVO=new ShowTradeInfoVO();
		}
		
		showTradeInfoVO.setTotalUserCount(userBaseDAO.getCurrentUserCount(beginTime));
		showTradeInfoVO.setTotalTradePrice(showTradeInfoVO.getTotalTradePrice()/100 * 100);
		return showTradeInfoVO;
	}
	
	
	
	
}
