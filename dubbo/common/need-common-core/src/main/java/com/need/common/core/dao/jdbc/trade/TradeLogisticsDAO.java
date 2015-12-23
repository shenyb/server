package com.need.common.core.dao.jdbc.trade;

import com.need.common.domain.po.api.trade.TradeLogisticsPO;
import com.need.common.domain.vo.pub.TradeLogisticsVO;

import java.util.List;

public interface TradeLogisticsDAO {
   
	/**
	 * @author Rylan 2015年8月13日 下午12:47:42
	 * @Method: insert 
	 * @Description: TODO
	 * @param record
	 * @return 
	 * @throws
	 */
	int insert(TradeLogisticsPO record);
    
    /**
     * @author Rylan 2015年8月13日 下午12:47:45
     * @Method: getTradeLogisticsPOById 
     * @Description: TODO
     * @param logisticsId
     * @return 
     * @throws
     */
    TradeLogisticsPO getTradeLogisticsPOById(Integer logisticsId);
    /**
     * @author Rylan 2015年8月13日 下午12:47:50
     * @Method: queryTradeLogisticsPOByLogisType 
     * @Description: TODO 根据类型获取列表
     * @param logisticsType
     * @return 
     * @throws
     */
    List<TradeLogisticsVO>  queryTradeLogisticsPOByLogisType(Integer logisticsType);
    
    /**
     * @author Rylan 2015年8月13日 下午12:52:38
     * @Method: queryTradeLogisticsPOByTypeAndParent 
     * @Description: TODO
     * @param logisticsType
     * @param parentId
     * @return 
     * @throws
     */
    List<TradeLogisticsVO>  queryTradeLogisticsPOByTypeAndParent(Integer logisticsType,Integer parentLogisticsId);
    /**
     * @author Rylan 2015年8月13日 下午1:12:28
     * @Method: deleteByLogisticsId 
     * @Description: TODO
     * @param logisticsId
     * @return 
     * @throws
     */
    int deleteByLogisticsId(Integer logisticsId);
    /**
     * @author Rylan 2015年8月13日 下午1:12:32
     * @Method: updateBylogisticsId 
     * @Description: TODO
     * @param record
     * @return 
     * @throws
     */
    int updateBylogisticsId(TradeLogisticsPO record);

}