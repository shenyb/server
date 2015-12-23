package com.need.dao.api.trade;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.api.trade.TradeRetrieveStatusRecord;
import com.need.domain.vo.trade.TradeRetrieveStatusRecordResultVO;

public interface TradeRetrieveStatusRecordDAO {
    int deleteByPrimaryKey(Integer retrieveId);

    int insert(TradeRetrieveStatusRecord record);

    int insertSelective(TradeRetrieveStatusRecord record);

    TradeRetrieveStatusRecord selectByPrimaryKey(Integer retrieveId);

    int updateByPrimaryKeySelective(TradeRetrieveStatusRecord record);

    int updateByPrimaryKey(TradeRetrieveStatusRecord record);
    /**
     * 
     * @author liuhongyang 2015年10月28日 下午4:51:03
     * @Method: selectByUserOrderNo 
     * @Description: 根据交易号查询流转信息
     * @param userTradeNo
     * @return 
     * @throws
     */
    List<TradeRetrieveStatusRecordResultVO> selectByUserTradeNo(@Param("userTradeNo") String userTradeNo);

	List<TradeRetrieveStatusRecord> getByRetrieveStatusAndTradeNo(@Param("trackingCode")String trackingCode, @Param("tradeNo")String tradeNo);
	
	List<TradeRetrieveStatusRecord> getAll();
}