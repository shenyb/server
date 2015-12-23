package com.need.service.bops.goods.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.goods.BopsPricechangeDAO;
import com.need.domain.common.enums.PriceChangeExcutedEnums;
import com.need.domain.po.bops.goods.BopsPricechangeGoods;
import com.need.service.bops.goods.GoodsTaskService;

@Service
public class GoodsTaskServiceImpl implements GoodsTaskService {
	@Autowired
	private BopsPricechangeDAO bopsPricechangeDAO ;
	
	@Autowired
	private GoodsMainDAO  goodsMainDAO;
	@Autowired
	private BopsGoodsDAO  bopsGoodsDAO;
	
	@Transactional
	public void updateStartedExcutedAndGoodsPrice(BopsPricechangeGoods bopsPriceChangeGoodsPO) {
		//修改当前记录为STARTED状态
		bopsPricechangeDAO.updateStartTimeById(bopsPriceChangeGoodsPO.getPricechangeId(),PriceChangeExcutedEnums.STARTED.code);	
		//更新时间  保证更新时间一致
		Date updateTime=Calendar.getInstance().getTime();
		//修改前台和后台商品价格
		
		bopsGoodsDAO.updatePriceById(bopsPriceChangeGoodsPO.getStartPrice(), bopsPriceChangeGoodsPO.getGoodsId(),updateTime);
		
		goodsMainDAO.updatePriceById(bopsPriceChangeGoodsPO.getStartPrice(), bopsPriceChangeGoodsPO.getGoodsId(),updateTime);
		
	}

	
	@Transactional
	public void updateEndedExcutedAndGoodsPrice(BopsPricechangeGoods bopsPriceChangeGoodsPO) {	
		//修改当前记录为ENDED状态
		bopsPricechangeDAO.updateEndTimeById(bopsPriceChangeGoodsPO.getPricechangeId(),PriceChangeExcutedEnums.ENDED.code);
		//更新时间
		Date updateTime=Calendar.getInstance().getTime();
		//修改前台和后台商品价格
		bopsGoodsDAO.updatePriceById(bopsPriceChangeGoodsPO.getEndPrice(), bopsPriceChangeGoodsPO.getGoodsId(),updateTime);
		
		goodsMainDAO.updatePriceById(bopsPriceChangeGoodsPO.getEndPrice(), bopsPriceChangeGoodsPO.getGoodsId(),updateTime);

	}
}
