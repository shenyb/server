package com.need.wap.web.controller.goods;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.service.goods.GoodsDetailService;
import com.need.common.core.service.goods.GoodsMainService;
import com.need.common.core.service.trade.TradeJudgementService;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.goods.GoodsDetailVO;
import com.need.common.domain.vo.goods.GoodsProfileResultVO;
import com.need.common.domain.vo.trade.GoodsJudgementListResultVO;
import com.need.wap.constant.ControllerMappings;

@Controller
@RequestMapping(value = ControllerMappings.GOODS)
public class GoodsManagerController {
	
	private static final Logger logger = Logger.getLogger(GoodsManagerController.class);
	
	@Autowired
	private GoodsMainService goodsMainService;
	@Autowired
	private GoodsDetailService goodsDetailService;
	@Autowired
	private TradeJudgementService tradeJudgementService;
	@Autowired
	private UserBaseDAO userBaseDAO;
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="{goodsId}")
	public Message getGoodsDetail(@PathVariable String goodsId) {
		Message success = Message.success();
		logger.info("getGoodsDetail.....in GoodsManagerController");
		//详情页面
		GoodsProfileResultVO goodsProfileResultVO= goodsMainService.getgoodsProfiled(goodsId);
		GoodsDetailVO goodsDetailVO = goodsDetailService.getGoodsDetailById(goodsId);
		JSONObject goodsParam = goodsDetailService.getGoodsParamsById(goodsId);
		goodsProfileResultVO.setGoodsDesc(goodsDetailVO.getGoodsDesc());
		goodsProfileResultVO.setPicList(goodsDetailVO.getPicList());
		goodsProfileResultVO.setSize(goodsParam.getString("size"));
		goodsProfileResultVO.setOriginPlace(goodsParam.getString("originPlace"));
		goodsProfileResultVO.setColor(goodsParam.getString("color"));
		goodsProfileResultVO.setWeight(goodsParam.getString("weight"));
		success.addData("goods", goodsProfileResultVO);
	//商品评级集合
		List<GoodsJudgementListResultVO> commentList= tradeJudgementService.getGoodsJudgementOpsList(goodsId);
		for(GoodsJudgementListResultVO goodsJudgementListResultVO :commentList){
			UserBasePO user= userBaseDAO.selectByPrimaryKey(goodsJudgementListResultVO.getUserId());
			goodsJudgementListResultVO.setProfilePicKey(user!=null?user.getProfilePicKey():"");
			goodsJudgementListResultVO.setUserName(user!=null?user.getNickName():"");
		}

		success.addData("commentList", commentList);
		
		return success;
	}
	

	
	
	
	
}
