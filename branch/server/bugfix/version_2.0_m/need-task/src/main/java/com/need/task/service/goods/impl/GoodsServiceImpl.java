package com.need.task.service.goods.impl;

import com.need.task.common.enums.PriceChangeExcutedEnums;
import com.need.task.dao.jdbc.api.goods.GoodsMainDAO;
import com.need.task.dao.jdbc.bops.goods.BopsGoodsDAO;
import com.need.task.dao.jdbc.bops.goods.BopsPriceChangeDAO;
import com.need.task.dao.jdbc.bops.goods.po.BopsPriceChangeGoodsPO;
import com.need.task.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private BopsPriceChangeDAO bopsPriceChangeDAO ;
	
	@Autowired
	private GoodsMainDAO  goodsMainDAO;
	
	@Autowired
	private BopsGoodsDAO  bopsGoodsDAO;
	
	@Transactional
	public void updateStartedExcutedAndGoodsPrice(BopsPriceChangeGoodsPO bopsPriceChangeGoodsPO) {
		//修改当前记录为STARTED状态
		bopsPriceChangeDAO.updateStartTimeById(bopsPriceChangeGoodsPO.getPricechangeId(),PriceChangeExcutedEnums.STARTED.code);	
		//更新时间  保证更新时间一致
		Date updateTime=Calendar.getInstance().getTime();
		//修改前台和后台商品价格
		
		bopsGoodsDAO.updatePriceById(bopsPriceChangeGoodsPO.getStartPrice(), bopsPriceChangeGoodsPO.getGoodsId(),updateTime);
		
		goodsMainDAO.updatePriceById(bopsPriceChangeGoodsPO.getStartPrice(), bopsPriceChangeGoodsPO.getGoodsId(),updateTime);
		
		
		
	}

	
	@Transactional
	public void updateEndedExcutedAndGoodsPrice(BopsPriceChangeGoodsPO bopsPriceChangeGoodsPO) {	
		//修改当前记录为ENDED状态
		bopsPriceChangeDAO.updateEndTimeById(bopsPriceChangeGoodsPO.getPricechangeId(),PriceChangeExcutedEnums.ENDED.code);
		//更新时间
		Date updateTime=Calendar.getInstance().getTime();
		//修改前台和后台商品价格
		bopsGoodsDAO.updatePriceById(bopsPriceChangeGoodsPO.getEndPrice(), bopsPriceChangeGoodsPO.getGoodsId(),updateTime);
		
		goodsMainDAO.updatePriceById(bopsPriceChangeGoodsPO.getEndPrice(), bopsPriceChangeGoodsPO.getGoodsId(),updateTime);

	}
	
}
