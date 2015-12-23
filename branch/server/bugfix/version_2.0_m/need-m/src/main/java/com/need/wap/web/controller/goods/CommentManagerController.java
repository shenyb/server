package com.need.wap.web.controller.goods;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.core.service.trade.TradeJudgementService;
import com.need.common.domain.pub.Message;
import com.need.wap.constant.ControllerMappings;

@Controller
@RequestMapping(value = ControllerMappings.GOODSCOMMENT)
public class CommentManagerController {
	
	private static final Logger logger = Logger.getLogger(CommentManagerController.class);
	
	@Autowired
	private TradeJudgementService tradeJudgementService;
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="list")
	public Message getGoodsComment(@RequestParam(required = true)String goodsId,@RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize) {
		logger.info("getGoodsComment.....in CommentManagerController goodsId:"+goodsId+"&&&&&pageNum:"+pageNum+"&&&&pageSize:"+pageSize);
		//商品评级集合
	//	Message message=tradeJudgementService.getCommentBypage(goodsId,pageNum,pageSize);
	//	return message;
		return null;
	}
	

	
	
	
	
}
