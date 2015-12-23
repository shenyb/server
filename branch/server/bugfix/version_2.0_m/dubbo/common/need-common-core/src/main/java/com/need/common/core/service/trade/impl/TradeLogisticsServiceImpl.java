package com.need.common.core.service.trade.impl;

import com.need.common.core.dao.jdbc.trade.TradeLogisticsDAO;
import com.need.common.core.service.trade.TradeLogisticsService;
import com.need.common.domain.enums.ProvinceEnum;
import com.need.common.domain.vo.pub.TradeLogisticsVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeLogisticsServiceImpl implements TradeLogisticsService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private TradeLogisticsDAO tradeLogisticsDAO;

	@Override
	public List<TradeLogisticsVO> queryTradeLogistics(Integer logisticsType) {
		/** TODO Auto-generated method stub*/
		List<TradeLogisticsVO> voList =tradeLogisticsDAO.queryTradeLogisticsPOByLogisType(logisticsType);
		return  voList ;
	}

	@Override
	public List<TradeLogisticsVO> queryTradeLogisticsPOByTypeAndParent(Integer logisticsType, Integer parentLogisticsId) {
		/** TODO Auto-generated method stub*/
		List<TradeLogisticsVO> voList=tradeLogisticsDAO.queryTradeLogisticsPOByTypeAndParent(logisticsType, parentLogisticsId);		
		return voList;
	}

	@Override
	public List<TradeLogisticsVO> queryTradeLogistics() {
		/** TODO Auto-generated method stub*/	
		List<TradeLogisticsVO> provinceList= queryTradeLogistics(ProvinceEnum.PROVINCE.code);//获得省市和直辖市
		for (TradeLogisticsVO tradeLogisticsVO : provinceList) {
			tradeLogisticsVO.setFirstList(queryTradeLogisticsPOByTypeAndParent(ProvinceEnum.FIRST.code,tradeLogisticsVO.getLogisticsId()));//获取当前的一级行政区
			for (TradeLogisticsVO firstTradeLogistic : tradeLogisticsVO.getFirstList()) {
				firstTradeLogistic.setFirstList(queryTradeLogisticsPOByTypeAndParent(ProvinceEnum.SECOND.code,firstTradeLogistic.getLogisticsId()));//获取二级行政区
			}					
		}
		return provinceList;
	}
	
	
}
