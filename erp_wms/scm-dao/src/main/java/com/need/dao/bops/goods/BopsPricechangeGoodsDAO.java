package com.need.dao.bops.goods;

import java.util.List;

import com.need.domain.po.bops.goods.BopsPricechangeGoods;
import com.need.domain.vo.goods.GoodsPriceDetailParamVO;
import com.need.domain.vo.goods.GoodsPriceDetailResultVO;
import com.need.domain.vo.goods.GoodsPriceResultVO;

public interface BopsPricechangeGoodsDAO {
	int deleteByPrimaryKey(Integer pricechangeGoodsId);

	int insert(BopsPricechangeGoods record);

	int countGoodsPrice(int pricechange_id);

	int insertSelective(BopsPricechangeGoods record);

	BopsPricechangeGoods selectByPrimaryKey(Integer pricechangeGoodsId);

	List<GoodsPriceResultVO> queryPriceList(int pricechangeId);
	
	List<BopsPricechangeGoods> queryPricePO(int pricechangeId);

	int countGoodsPriceDetailList(GoodsPriceDetailParamVO param);

	List<GoodsPriceDetailResultVO> queryPriceDetailList(GoodsPriceDetailParamVO param);

	int updateByPrimaryKeySelective(BopsPricechangeGoods record);

	int updateByPrimaryKey(BopsPricechangeGoods record);
}