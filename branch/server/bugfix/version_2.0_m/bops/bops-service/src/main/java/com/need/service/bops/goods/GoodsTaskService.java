package com.need.service.bops.goods;

import com.need.domain.po.bops.goods.BopsPricechangeGoods;

public interface GoodsTaskService {

	
	
	/**
	 * @author Rylan 2015年11月18日 下午10:22:42
	 * @Method: updateStartedExcutedAndGoodsPrice 
	 * @Description: 修改excuted状态为STARTED 并且修改商品价格
	 * @param bopsPriceChangeGoodsPO 
	 * @throws
	 */
	public void updateStartedExcutedAndGoodsPrice(BopsPricechangeGoods bopsPriceChangeGoodsPO);
	
	
	/**
	 * @author Rylan 2015年11月18日 下午10:22:45
	 * @Method: updateEndedExcutedAndGoodsPrice 
	 * @Description: 修改excuted状态为ENDED 并且修改商品价格
	 * @param bopsPriceChangeGoodsPO 
	 * @throws
	 */
	public void updateEndedExcutedAndGoodsPrice(BopsPricechangeGoods bopsPriceChangeGoodsPO);
}
