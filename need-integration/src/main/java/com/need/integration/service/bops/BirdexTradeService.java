package com.need.integration.service.bops;

import com.need.integration.common.birdex.bean.orderResult.OrderResultVO;
import com.need.integration.common.birdex.bean.tracking.TrackingOrderVO;
import com.need.integration.pub.Message;

public interface BirdexTradeService {

	int birdexTradeResultUpdate(String userTradeNo, OrderResultVO orderResult);
	
	int birdexResultUpdate(String userTradeNo, String result);
	
	int updateLogisticsNo(String userTradeNo, String logisticsNo);
	
	int saveTrackingOrderInfo(TrackingOrderVO trackingOrderVO);
	
	int identityNotPass(String userTradeNo, String result);
	
	public void send(String orderNo, String transporterId, String outIds);
	
	public Message reduceStore(String tradeNo);
	
}
