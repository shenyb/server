package com.need.api.web.controller.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.TradeGoodVO;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * 
 * <p></p>
 * @author peiboli 2015年8月9日 下午3:28:02
 * @ClassName GetTradeGoodsController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月9日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.USERTRADEGOODSLIST)
public class GetTradeGoodsController {
	private static final Logger logger = Logger.getLogger(GetTradeGoodsController.class);
	
	@Autowired
	private TradeBaseService tradeBaseService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0",method = RequestMethod.GET)
	public Message getTradeGoodsList(@RequestParam(required = false) String userId,
			@RequestParam(required = true)Integer pageNum,@RequestParam(required = true) Integer pageSize) {
		Message success = Message.success();
		if(StringUtil.isBlank(userId)){
			return Message.error(BizReturnCode.USERID_NOT_EXIST);
		}
		PageHelper.startPage(pageNum, pageSize);
		logger.info(String.format("getTradeGoodsList....in...param:%s %d %d", userId,pageNum,pageSize));
		Page<TradeGoodVO> page= (Page<TradeGoodVO>)tradeBaseService.getTradeGoodsList(userId);
		List<TradeGoodVO> tradeGoodVO= page.getResult();
		for(int i=0;i<tradeGoodVO.size();i++){
			
			tradeGoodVO.get(i).setOwnPicKey(tradeGoodVO.get(i).getOwnPicKey().split(",")[0]);
		}
		success.addData("goodsList", tradeGoodVO);
		success.addData("total", page.getTotal());
		logger.info(String.format("getTradeGoodsList....out...param:%s %d %d", userId,pageNum,pageSize));
		return success;
	}


}
