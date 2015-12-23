package com.need.service.bops.trade;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.need.domain.po.bops.goods.BopsGoodsBrandPO;
import com.need.domain.po.bops.trade.BopsTradePushPull;


public interface BopsTradePushPullService {

	List<BopsTradePushPull> queryByBatchNo(String batchNo);
	/**
	 * 定时把订单推送给wms
	 * @author zhangmengbin
	 */
	void createDeliverTrade();
	
	HSSFWorkbook exportBatch(List<BopsTradePushPull> list);
}
