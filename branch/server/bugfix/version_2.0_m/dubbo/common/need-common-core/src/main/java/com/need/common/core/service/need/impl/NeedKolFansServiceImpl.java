package com.need.common.core.service.need.impl;

import com.need.common.core.dao.jdbc.need.NeedKolFansDAO;
import com.need.common.core.service.need.NeedKolFansService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.user.UserExternVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NeedKolFansServiceImpl implements NeedKolFansService {

	@Autowired
	private NeedKolFansDAO needKolFansDAO;
	
	@Autowired
	private TradeBaseService tradeBaseService;

	@Override
	public int countKolFans(String kolId) {
		// TODO Auto-generated method stub
		return needKolFansDAO.countKolFans(kolId);
	}

	@Override
	public int countUserConcern(String userId) {
		// TODO Auto-generated method stub
		return needKolFansDAO.getConcernCount(userId);
	}

	@Override
	public Message countUerExternInfo(String userId) {
		// TODO Auto-generated method stub
		Message message = Message.success();
		UserExternVO userExtern = new UserExternVO();
		userExtern.setUserFans(countKolFans(userId));
		userExtern.setUserFocus(countUserConcern(userId));
		userExtern.setUserOrders(tradeBaseService.countTrade(userId));
		message.addData("externInfo", userExtern);
		return message;
	}
	
}
