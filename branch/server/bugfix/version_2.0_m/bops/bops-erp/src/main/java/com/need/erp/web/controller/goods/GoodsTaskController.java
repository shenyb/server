package com.need.erp.web.controller.goods;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.need.dao.bops.goods.BopsPricechangeDAO;
import com.need.domain.po.bops.goods.BopsPricechangeGoods;
import com.need.erp.constant.ControllerMappings;
import com.need.service.bops.goods.BopsGoodsService;
import com.need.service.bops.goods.GoodsTaskService;

@Controller
@RequestMapping(ControllerMappings.GOODS_TASK)
public class GoodsTaskController {

	private static final Logger logger = Logger.getLogger(GoodsTaskController.class);

	@Autowired
	private BopsPricechangeDAO bopsPricechangeDAO;
	@Autowired
	private GoodsTaskService goodsTaskService;
	@Autowired
	private BopsGoodsService bopsGoodsService;

	@RequestMapping(method = RequestMethod.GET, value = "dealGoodsPriceStartTime")
	public void dealGoodsPriceStartTime() {
		logger.info("dealGoodsPriceStartTime in..");
		List<BopsPricechangeGoods> startChangeList = bopsPricechangeDAO.selectChangeStartTimeRecord();
		if (startChangeList == null || startChangeList.size() == 0) {
			logger.info("startChangeList is null or startChangeList.size() is 0,continue this.");
			startChangeList = null;
			return;
		}
		logger.info("begin to deal startChangeList.size is :" + startChangeList.size());
		for (BopsPricechangeGoods bopsPriceChangeGoods : startChangeList) {
			logger.info("begin deal bopsPriceChangeGoods :" + bopsPriceChangeGoods);
			goodsTaskService.updateStartedExcutedAndGoodsPrice(bopsPriceChangeGoods);
			bopsGoodsService.updateGroupGoodsPrice(bopsPriceChangeGoods.getGoodsId());
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "dealGoodsPriceEndTime")
	public void dealGoodsPriceEndTime() {
		logger.info("dealGoodsPriceEndTime in..");
		List<BopsPricechangeGoods> endChangeList = bopsPricechangeDAO.selectChangeEndTimeRecord();
		if (endChangeList == null || endChangeList.size() == 0) {
			logger.info("endChangeList is null or endChangeList.size() is 0,continue this.");
			endChangeList = null;
			return;
		}
		logger.info("begin to deal endChangeList.size is :" + endChangeList.size());
		for (BopsPricechangeGoods bopsPriceChangeGoods : endChangeList) {
			goodsTaskService.updateEndedExcutedAndGoodsPrice(bopsPriceChangeGoods);
			bopsGoodsService.updateGroupGoodsPrice(bopsPriceChangeGoods.getGoodsId());
		}

	}

}
