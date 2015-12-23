package com.need.common.core.dao.jdbc.trade;

import com.need.common.domain.po.api.trade.TradeRetrieveStatusRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RetrieveStatusRecordDAO {

	int insert(TradeRetrieveStatusRecord record);

	List<TradeRetrieveStatusRecord> getByTradeNo(String tradeNo);
	List<TradeRetrieveStatusRecord> getByRetrieveStatusAndTradeNo(@Param("trackingCode")String retrieveStatus,@Param("tradeNo") String tradeNo);

}