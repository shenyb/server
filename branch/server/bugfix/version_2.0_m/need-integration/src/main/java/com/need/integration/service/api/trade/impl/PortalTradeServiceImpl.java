package com.need.integration.service.api.trade.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.integration.common.exception.ServiceException;
import com.need.integration.dao.jdbc.api.goods.GoodsMainDAO;
import com.need.integration.dao.jdbc.api.goods.po.GoodsMainPO;
import com.need.integration.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.integration.dao.jdbc.api.trade.po.TradeBasePO;
import com.need.integration.pub.Message;
import com.need.integration.service.api.trade.PortalTradeService;
import com.need.trade.TradeStatusProcess;
import com.need.trade.enums.TradeStatus;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年9月10日 上午9:38:56
 * @ClassName PortalTradeServiceImpl
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
@Service
@Transactional
public class PortalTradeServiceImpl implements PortalTradeService {
	private static Logger logger = Logger.getLogger(PortalTradeServiceImpl.class);
	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
/**
 * 
 * @author shenyb 2015年10月27日 下午9:06:01
 * @Method: sendTrade 
 * @Description: TODO
 * @param tradeNo 注意是长的交易号
 * @return 
 * @see com.need.integration.service.api.trade.PortalTradeService#sendTrade(java.lang.String)
 */
	@Override
	@Transactional("portal_txManager")
	public Message sendTrade(String tradeNo) {
		Message message = Message.success();
		List<TradeBasePO> tradeList = tradeBaseDAO.queryByTradeNo(tradeNo);
		TradeStatus status = TradeStatus.WAIT_PLATFORM_SEND;
		TradeStatus toStatus = TradeStatusProcess.tradeProcess(status);
		for (TradeBasePO trade : tradeList) {
			String tradeStatus = trade.getTradeStatus();
			if (tradeStatus.equals(status.code)) {
				// 1 修改交易状态
				trade.setTradeStatus(toStatus.code);
				trade.setOrderStatus(toStatus.code);
				trade.setTradeTime(Calendar.getInstance().getTime());
				int updateCount = tradeBaseDAO.updateByPO(trade);
				if(updateCount<=0){
					logger.error(String.format("sendTrade error:tradeNo:%s", tradeNo));
					throw new ServiceException();
				}
				// 2交易状态变更成功后
				// 减前台商品主表库存数
				// 2.1. 根据交易编号找到对应子订单的商品列表；
				// 2.2. 锁定数量减去子订单对应的购买数，意味着实际减掉了。
				String goodsId = trade.getGoodsId();
				int buyCount = trade.getBuyCount();
				if (goodsId == null) {
					logger.error(String.format("sendTrade error:goodsId null tradeNo:%s", tradeNo));
					throw new ServiceException();
				}
				GoodsMainPO goodsMain = goodsMainDAO.getByGoodsId(goodsId);
				if (goodsMain != null) {
					goodsMainDAO.updateLockCountByGoodsId(goodsId, buyCount);
				}
				else{
					throw new ServiceException();
				}
			} else {
				logger.error(String.format("sendTrade error:status is not wait_platform_send tradeNo:%s", tradeNo));
				throw new ServiceException();
			}
		}

		return message;
	}

}
