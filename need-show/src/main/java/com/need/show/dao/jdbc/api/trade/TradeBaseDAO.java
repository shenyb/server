package com.need.show.dao.jdbc.api.trade;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.show.dao.jdbc.api.trade.po.TradeBasePO;
import com.need.show.web.controller.trade.vo.ShowTradeInfoVO;

public interface TradeBaseDAO {

	List<TradeBasePO> getByTradeNo(String tradeNo);
	
	
	ShowTradeInfoVO  getShowTradeInfo(@Param("beginTime") String beginTime);
	
	
	
}