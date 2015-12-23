package com.need.integration.dao.jdbc.api.trade;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.integration.dao.jdbc.api.trade.po.TradeRetrieveStatusRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRetrieveStatusRecordDAO {
    int deleteByPrimaryKey(Integer retrieveId);

    int insert(TradeRetrieveStatusRecord record);
    
    int insertBiredexRecord(TradeRetrieveStatusRecord record);
    
    int insertIdentityNotPassRecord(TradeRetrieveStatusRecord record);
    
    TradeRetrieveStatusRecord getByTradeNoAndTrackingCode(@Param("tradeNo")String tradeNo, @Param("trackingCode")String trackingCode);

    int insertSelective(TradeRetrieveStatusRecord record);

    TradeRetrieveStatusRecord selectByPrimaryKey(Integer retrieveId);

    int updateByPrimaryKeySelective(TradeRetrieveStatusRecord record);
    
    int updateRecord(TradeRetrieveStatusRecord record);

    int updateByPrimaryKey(TradeRetrieveStatusRecord record);

	List<TradeRetrieveStatusRecord> getByUserTradeNo(String tradeNo);

	List<TradeRetrieveStatusRecord> getByTradeNo(String tradeNo);

	List<TradeRetrieveStatusRecord> getByRetrieveStatusAndTradeNo(@Param("trackingCode")String retrieveStatus,@Param("tradeNo") String tradeNo);
	//TradeRetrieveStatusRecord getByTradeNoAndCreateTime(@Param("tradeNo")String tradeNo,@Param("ProcessInfo")String ProcessInfo);
}