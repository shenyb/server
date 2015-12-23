package com.need.common.core.service.trade;

import com.need.common.domain.vo.pub.TradeLogisticsVO;

import java.util.List;

/**
 * <p></p>
 * @author Rylan 2015年8月13日 下午1:16:28
 * @ClassName TradeLogisticsService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月13日
 * @modify by reason:{方法名}:{原因}
 */
public interface TradeLogisticsService {

	/**
	 * @author Rylan 2015年8月13日 下午1:16:24
	 * @Method: queryTradeLogistics 
	 * @Description: TODO
	 * @param logisticsType
	 * @return 
	 * @throws
	 */
	List<TradeLogisticsVO> queryTradeLogistics(Integer logisticsType);
	
	/**
	 * @author Rylan 2015年8月13日 下午1:16:32
	 * @Method: queryTradeLogisticsPOByTypeAndParent 
	 * @Description: TODO
	 * @param logisticsType
	 * @param parentLogisticsId
	 * @return 
	 * @throws
	 */
	List<TradeLogisticsVO>  queryTradeLogisticsPOByTypeAndParent(Integer logisticsType,Integer parentLogisticsId);
	
	
	List<TradeLogisticsVO> queryTradeLogistics();
	
}
