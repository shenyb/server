package com.need.service.api.trade.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.api.trade.TradeBaseDAO;
import com.need.dao.api.trade.TradeRefundDAO;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.api.trade.TradeBasePO;
import com.need.domain.po.api.trade.TradeRefundPO;
import com.need.domain.pub.Message;
import com.need.service.api.trade.PortalTradeService;
import com.need.service.common.exception.ServiceException;
import com.need.trade.TradeStatusProcess;
import com.need.trade.enums.TradeStatus;

/**
 * 
 * @author david.tan
 *
 */
@Service
public class PortalTradeServiceImpl implements PortalTradeService {
	
	private static final Logger logger = Logger.getLogger(PortalTradeServiceImpl.class);
	
	@Autowired
	private TradeBaseDAO bopsTradeBaseDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private TradeBaseDAO portalTradeBaseDAO;
	@Autowired
	private TradeRefundDAO portalTradeRefundDAO;

	/**
	 * 
	 * @author shenyb 2015年8月24日 下午5:44:21
	 * @Method: sendTrade
	 * @Description:
	 * @param tradeNo
	 * @return
	 * @see com.need.bops.service.portal.trade.PortalTradeService#sendTrade(java.lang.String)
	 */
	@Override
	@Transactional("api_txManager")
	public Message sendTrade(String tradeNo) {
		Message message = Message.success();
		logger.info("into PortalTradeServiceImpl sendTrade : tradeNo: " + tradeNo);
		List<TradeBasePO> tradeList = bopsTradeBaseDAO.getByTradeNo(tradeNo);
		TradeStatus status = TradeStatus.WAIT_PLATFORM_SEND;
		TradeStatus toStatus = TradeStatusProcess.tradeProcess(status);
		for (TradeBasePO trade : tradeList) {
			String tradeStatus = trade.getTradeStatus();
			if (tradeStatus.equals(status.code)) {
				// 1 修改交易状态
				trade.setTradeStatus(toStatus.code);
				trade.setOrderStatus(toStatus.code);
				trade.setTradeTime(Calendar.getInstance().getTime());
				// 2交易状态变更成功后
				// 减前台商品主表库存数
				// 2.1. 根据交易编号找到对应子订单的商品列表；
				// 2.2. 锁定数量减去子订单对应的购买数，意味着实际减掉了。
				String goodsId = trade.getGoodsId();
				int buyCount = trade.getBuyCount();
				if (goodsId == null) {
					logger.info("into PortalTradeServiceImpl sendTrade : goodsId: " + goodsId);
					throw new ServiceException(7012, Message.error(7012).getMsg());
				//	return Message.error(7012);
				}
				GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);
				if (goodsMain != null) {
					bopsTradeBaseDAO.updateByPrimaryKey(trade);
					goodsMainDAO.updateLockCountByGoodsId(goodsId, buyCount);
				} else {
					logger.info("into PortalTradeServiceImpl sendTrade : goodsId: " + goodsId);
					throw new ServiceException(7007, Message.error(7007).getMsg());
					//return Message.error(7007);
				}

			} else {
				logger.info("into PortalTradeServiceImpl sendTrade : tradeStatus: " + trade.getTradeStatus());
				throw new ServiceException(7003, Message.error(7003).getMsg());
				// Message.error(7003);
			}
		}
		
		return message;
	}

	@Override
	public Message refund(String tradeNo, int refundAmount, String memo) {
		/**
		 * 先执行前台的交易逻辑，根据结果决定是否处理后台的退款逻辑。
		 */
		Message message = Message.success();
		int result = 0;
		List<TradeBasePO> tradeList = portalTradeBaseDAO.getByTradeNo(tradeNo);
		String tradeStatus = tradeList.get(0).getTradeStatus();
		
		/**
		 * 1修改前台交易主表状态
		 */
		for (TradeBasePO trade : tradeList) {
			// 修改交易状态退款成功
			trade.setTradeStatus(TradeStatus.REFUND_SUCCESS.code);
			trade.setOrderStatus(TradeStatus.REFUND_SUCCESS.code);
			trade.setTradeTime(Calendar.getInstance().getTime());
			result += portalTradeBaseDAO.updateByPrimaryKey(trade);
		}
		if (result == 0) {
			return Message.error(7009);
		}
		result = 0;
		/**
		 * 2生成退款记录，插入数据库
		 */
		if (tradeList != null && tradeList.size() > 0) {
			TradeRefundPO refund = new TradeRefundPO();
			refund.setTradeNo(tradeNo);
			refund.setOrderNo(tradeNo);
			refund.setRefundAmount(refundAmount);
			refund.setTradeStatus(TradeStatus.REFUND_SUCCESS.code);
			refund.setMemo(memo == null ? "" : memo);
			result += portalTradeRefundDAO.insert(refund);
		}
		
		/**
		 * 退款时把待发货的库存锁定数还原
		 */
		if(TradeStatus.WAIT_PLATFORM_SEND.code.equals(tradeStatus)){
			for(TradeBasePO tradeBase: tradeList){
				logger.info("into PortalTradeServiceImpl refund : tradeStatus: " + tradeStatus);
				String goodsId = tradeBase.getGoodsId();
				GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goodsId);
				if(goodsMain.getLockCount() >= tradeBase.getBuyCount()){
					goodsMainDAO.updateLockcount(goodsId, tradeBase.getBuyCount());
				}
			}
		}
		
		return result == 0 ? Message.error(7010) : message;
	}
//
//	@Override
//	@Transactional("api_txManager")
//	public Message saveTest() {
//		/** TODO Auto-generated method stub*/
//		TradeBasePO po=bopsTradeBaseDAO.selectByPrimaryKey("201508211830a268ea8856453");
//		po.setBuyerId("ererere");
//		bopsTradeBaseDAO.updateByPrimaryKey(po);
//		
//		System.out.println("----------------------");
//		bopsTradeBaseDAO.insert(po);
//		
//		return null;
//	}

}
