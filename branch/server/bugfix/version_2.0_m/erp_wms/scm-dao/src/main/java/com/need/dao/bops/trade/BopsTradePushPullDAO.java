package com.need.dao.bops.trade;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.trade.BopsTradePushPull;
import com.need.domain.vo.trade.TradeBatchNoParamsVO;

public interface BopsTradePushPullDAO {
	int deleteByPrimaryKey(String orderNo);

	int insert(BopsTradePushPull record);

	int insertSelective(BopsTradePushPull record);

	BopsTradePushPull selectByPrimaryKey(String orderNo);

	int updateByPrimaryKeySelective(BopsTradePushPull record);

	int updateByPrimaryKey(BopsTradePushPull record);

	List<String> queryOrderNosByRetrieveStatus(@Param("retrieveStatus")String retrieveStatus);

	String getlogisticsNo(@Param("orderNo")String orderNo);
	
	List<BopsTradePushPull> queryBatchInfoList(TradeBatchNoParamsVO param);

	List<BopsTradePushPull> getbyBatchNo(String batchNo);

	int getBatchInfoCount(TradeBatchNoParamsVO param);
	
	List<BopsTradePushPull> queryListToDeliver(); 
	int updateByTradeNo(BopsTradePushPull record);
	int updateByOrderNo(BopsTradePushPull record);
	BopsTradePushPull getByTradeNo(String tradeNo);
	
}