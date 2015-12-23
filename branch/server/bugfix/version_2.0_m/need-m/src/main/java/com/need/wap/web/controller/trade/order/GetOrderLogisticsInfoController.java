package com.need.wap.web.controller.trade.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.core.dao.jdbc.trade.RetrieveStatusRecordDAO;
import com.need.common.core.dao.jdbc.trade.TradePushPullDAO;
import com.need.common.domain.po.api.trade.TradePushPullPO;
import com.need.common.domain.po.api.trade.TradeRetrieveStatusRecord;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.RetrieveStatusVO;
import com.need.utils.StringUtil;
import com.need.wap.constant.ControllerMappings;

@Controller
@RequestMapping(ControllerMappings.GET_ORDER_LOGISTICS_INFO)
public class GetOrderLogisticsInfoController {

	@Autowired
	private TradePushPullDAO TradePushPullDAO;

	@Autowired
	private RetrieveStatusRecordDAO retrieveStatusRecordDAO;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getOrderLogisticsInfo(@PathVariable String userOrderNo) {
		Message message = Message.success();
		List<RetrieveStatusVO> list = new ArrayList<RetrieveStatusVO>();
		List<TradeRetrieveStatusRecord> retrieveList = retrieveStatusRecordDAO.getByTradeNo(userOrderNo);
		TradePushPullPO push = TradePushPullDAO.getByTradeNo(userOrderNo);
		String logisticsNo = push == null ? "" : push.getLogisticsNo();
		for (TradeRetrieveStatusRecord retrieve : retrieveList) {
			RetrieveStatusVO retrieveVO = new RetrieveStatusVO();
			if (!StringUtil.isBlank(retrieve.getTrackingDesc())) {
				retrieveVO.setState(retrieve.getTrackingDesc());
				retrieveVO.setCreateTime(retrieve.getCreateTime());
				list.add(retrieveVO);
			}
		}
		message.addData("tradeStatusList", list);
		message.addData("logisticsNo", logisticsNo);
		return message;

	}

}
