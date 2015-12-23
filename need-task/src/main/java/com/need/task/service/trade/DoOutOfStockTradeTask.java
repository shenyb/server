package com.need.task.service.trade;

import com.need.task.dao.jdbc.api.goods.GoodsMainDAO;
import com.need.task.dao.jdbc.api.goods.po.GoodsMainPO;
import com.need.task.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.task.dao.jdbc.api.trade.po.TradeBasePO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 定时处理缺货的订单，如果库存够了，就往下走
 * @author zhangmengbin
 *
 */
@Component
public class DoOutOfStockTradeTask {
	private static final Logger logger = Logger.getLogger(DoOutOfStockTradeTask.class);
	
	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	
	
	//@Scheduled(cron="${queryOutOfStoreTrade}")
	public void queryOutOfStoreTrade(){
		logger.info("定时处理缺货的订单");
		List<String> list = tradeBaseDAO.queryOutOfStoreTrade();
		if(list!=null && list.size()>0){
			for(String tradeNo :list ){
				doOutOfStockTrade(tradeNo);
			}
		}
	}
	@Transactional("api_txManager")
	public void doOutOfStockTrade(String tradeNo){
		try {
			logger.info("查询缺货的订单");
			List<TradeBasePO> list =  tradeBaseDAO.queryByTradeNo(tradeNo);
			boolean flag= true;
			if(null!=list && list.size()>0){
				for(TradeBasePO tradeBasePO : list){
					int buyCount = tradeBasePO.getBuyCount();
					GoodsMainPO goodsMainPO= goodsMainDAO.selectByPrimaryKey(tradeBasePO.getGoodsId());
					int storeCount =goodsMainPO.getStoreCount();
					if(buyCount>storeCount){
						flag = false;
						break;
					}
				}
			}
			if(flag){
				logger.info("queryOutOfStoreTrade 修改订单状态");
				tradeBaseDAO.updateISNormalByTradeNo(tradeNo, "IN_STORE");
				if(null!=list && list.size()>0){
					for(TradeBasePO tradeBasePO : list){
						logger.info("queryOutOfStoreTrade 减库存，加冻结");
						goodsMainDAO.updateStoreCountAndLockCountByGoodsId(tradeBasePO.getGoodsId(), tradeBasePO.getBuyCount());
					}
				}
				
			}
		
		} catch (Exception e) {
			logger.error("处理缺货的订单，定时失败"+e.getMessage());
			e.printStackTrace();
		}
	}
		
}
