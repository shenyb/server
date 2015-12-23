package com.need.task.service.goods;

import com.need.task.dao.jdbc.bops.goods.po.BopsPriceChangeGoodsPO;

public interface GoodsService {

	/**
	 * @author Rylan 2015年11月18日 下午10:22:42
	 * @Method: updateStartedExcutedAndGoodsPrice 
	 * @Description: 修改excuted状态为STARTED 并且修改商品价格
	 * @param bopsPriceChangeGoodsPO 
	 * @throws
	 */
	public void updateStartedExcutedAndGoodsPrice(BopsPriceChangeGoodsPO bopsPriceChangeGoodsPO);
	
	
	/**
	 * @author Rylan 2015年11月18日 下午10:22:45
	 * @Method: updateEndedExcutedAndGoodsPrice 
	 * @Description: 修改excuted状态为ENDED 并且修改商品价格
	 * @param bopsPriceChangeGoodsPO 
	 * @throws
	 */
	public void updateEndedExcutedAndGoodsPrice(BopsPriceChangeGoodsPO bopsPriceChangeGoodsPO);
	
}
