package com.need.common.core.service.trade.impl;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.trade.TradeCartDAO;
import com.need.common.core.service.trade.TradeCartService;
import com.need.common.domain.po.api.trade.TradeCartPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.EditTradeCardParma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月8日 下午12:35:14
 * @ClassName CartServiceImpl
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class TradeCartServiceImpl implements TradeCartService {
	@Autowired
	private TradeCartDAO tradeCartDAO;

	@Override
	@Transactional
	public int updateTradeCart(String goodsId, int goodsCount, String userId) {
		
		if (goodsCount <= 0) {
			return BizReturnCode.TRADE_CART_COUNT_LESS_ONE;
		}
		
		TradeCartPO cart = tradeCartDAO.getByUserIdAndGoodsId(userId, goodsId);
		if (cart == null) {
			cart = new TradeCartPO();
			cart.setCartId(BizCodeUtil.generateCartId(goodsId, userId));
			cart.setGoodsId(goodsId);
			cart.setGoodsCount(goodsCount);
			cart.setUserId(userId);
			int result = tradeCartDAO.insert(cart);
			return result>0?BizReturnCode.SUCCESS:BizReturnCode.TRADE_CART_ADD_ERR;
		}
		
		cart.setGoodsCount(goodsCount + cart.getGoodsCount());
		tradeCartDAO.updateByPrimaryKey(cart); //TODO 提供具体的DAO方法：变更购物车的商品数量
		
		return Message.SUCCESS;
	}

	@Override
	@Transactional
	public int deleteByPrimaryKey(String cardIds) {
		return tradeCartDAO.deleteByCartIds(cardIds);
	}

	@Override
	public int updateByPrimaryKeySelective(TradeCartPO record) {
		return 0;
	}

	/**
	 * 服务层统一采用Message，封装业务码的处理；DAO层统一采用int，表明sql执行变更的行数。
	 * 
	 */
	@Override
	@Transactional
	public int updateGoodsCart(EditTradeCardParma param) {
		
		int modifyCount = 0;
		modifyCount = tradeCartDAO.updateGoodsCount(param.getGoodsCount(), param.getCartId());
		return modifyCount;
	}
}
