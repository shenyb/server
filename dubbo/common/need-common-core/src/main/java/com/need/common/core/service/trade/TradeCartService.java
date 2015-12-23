package com.need.common.core.service.trade;

import com.need.common.domain.po.api.trade.TradeCartPO;
import com.need.common.domain.vo.trade.EditTradeCardParma;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月8日 下午12:34:31
 * @ClassName CartService
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
public interface TradeCartService {

	int updateTradeCart(String goodsId, int goodsCount, String userId);
	
	int deleteByPrimaryKey(String cartIds);
	
	int updateByPrimaryKeySelective(TradeCartPO record);
	
	/**
	 * @author Rylan 2015年8月10日 下午10:46:57
	 * @Method: updateGoodsCart 
	 * @Description: TODO
	 * @param param
	 * @return 
	 * @throws
	 */
	int updateGoodsCart(EditTradeCardParma param);

	
}
