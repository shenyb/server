package com.need.integration.service.api.trade;

import com.need.integration.pub.Message;

/**
 * 
 * <p></p>
 * @author shenyb 2015年9月10日 上午9:38:28
 * @ClassName PortalTradeService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
public interface PortalTradeService {


	Message sendTrade(String tradeNo);
	
}
