package com.need.common.core.dao.jdbc.trade;

import com.need.common.domain.po.api.trade.TradeItemsGoodsRecordPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeItemsGoodsRecordDAO {
	int deleteByPrimaryKey(Long tradeItemsGoodsId);

	int insert(TradeItemsGoodsRecordPO record);

	int saveBatchRecord(@Param("recordList")List<TradeItemsGoodsRecordPO> recordList);

	int insertSelective(TradeItemsGoodsRecordPO record);

	TradeItemsGoodsRecordPO selectByPrimaryKey(Long tradeItemsGoodsId);

	int updateByPrimaryKeySelective(TradeItemsGoodsRecordPO record);

	int updateByPrimaryKey(TradeItemsGoodsRecordPO record);
}