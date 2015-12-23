package com.need.api.web.controller.trade.order;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.dao.jdbc.trade.TradeJudgementDAO;
import com.need.common.core.service.trade.TradeManager;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.po.api.trade.TradeJudgementPO;
import com.need.common.domain.pub.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * <p></p>
 * @author LXD 2015年8月13日 上午10:30:20
 * @ClassName AddOrderJudgement
 * @Description  添加订单评价 
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月13日
 * @modify by reason:{方法名}:{原因}
 */ 
@Controller
@RequestMapping(ControllerMappings.TRADE_ADD_JUDGEMENT)
public class AddOrderJudgementController {
	private static final Logger logger = Logger.getLogger(AddOrderJudgementController.class);
	
	@Autowired
	private TradeManager tradeManager;
	
	@Autowired
	private TradeJudgementDAO tradeJudgementDAO;
	
	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message addOrderJudgement(@RequestParam(required = true)String userId,@RequestParam(required = true) String orderNo, @RequestParam(required = true) String goodsId,@RequestParam(required=true) String judgement){
		logger.info("in trade/judgement/add userId: " + userId + "\t orderNo: " + orderNo + "\t goodId: " + goodsId + "\t judgement: " + judgement);
		
		Message message =Message.success();
		/***
		 * 安卓把参数传错了，迫不得已这么修改。。。。
		 */
		TradeBasePO tradeBase= tradeBaseDAO.getByorderNo(orderNo);
		
		if(tradeBase==null){	
		 TradeBasePO tradeBasePO=tradeBaseDAO.getBytradeNoAndGoodsId(orderNo,goodsId);	
		 if(tradeBasePO!=null){
			 orderNo= tradeBasePO.getOrderNo();
		 }
			
		}
		TradeJudgementPO judgementPO=this.convertJudgementPo(userId,orderNo,goodsId,judgement);
		//查询数据库，看是否已评价
		TradeJudgementPO judge =tradeJudgementDAO.getbyParams(judgementPO.getOrderNo(),judgementPO.getUserId());
		if(judge!=null){
			return Message.error(BizReturnCode.TRADE_JUDGEMENT_ERROR);
		}
		tradeManager.addtradeComment(judgementPO);
		return message;
	}
	private TradeJudgementPO convertJudgementPo(String userId, String orderNo, String goodsId, String judgement) {
		TradeJudgementPO judgementPO =new TradeJudgementPO();
		judgementPO.setUserId(userId);
		judgementPO.setOrderNo(orderNo);
		judgementPO.setGoodsId(goodsId);
		judgementPO.setJudgement(judgement);
		return judgementPO;
	}
	
}
