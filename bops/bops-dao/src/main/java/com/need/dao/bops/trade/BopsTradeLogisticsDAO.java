package com.need.dao.bops.trade;

import java.util.List;

import com.need.domain.po.bops.trade.BopsTradeLogistics;

public interface BopsTradeLogisticsDAO {
    int deleteByPrimaryKey(String tradeNo);

    int insert(BopsTradeLogistics record);

    int insertSelective(BopsTradeLogistics record);

    BopsTradeLogistics selectByPrimaryKey(String tradeNo);

    int updateByPrimaryKeySelective(BopsTradeLogistics record);

    int updateByPrimaryKey(BopsTradeLogistics record);

	BopsTradeLogistics getByTradeNo(String tradeNo);
	/**
	 * 查询所有
	 * @author zhangmengbin
	 * @return
	 */
	List<BopsTradeLogistics> queryAllBopsTradeLogistics();
}