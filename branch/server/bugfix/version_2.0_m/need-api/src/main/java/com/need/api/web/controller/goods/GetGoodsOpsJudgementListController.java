package com.need.api.web.controller.goods;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.service.trade.TradeJudgementService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.goods.GoodsResultVO;
import com.need.common.domain.vo.trade.GoodsJudgementListResultVO;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(ControllerMappings.GOODS_OPS_JUDGEMENTLIST)
public class GetGoodsOpsJudgementListController {
	
	private static final Logger logger = Logger.getLogger(GetGoodsOpsJudgementListController.class);
	
	@Autowired
	private TradeJudgementService tradeJudgementService;
	
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	
	@ParamsValidate(value={
			@ParamValidate(name="goodsId",notNull=true,code=BizReturnCode.NOT_FIND_GOODS,regex="^\\w\\S*$")})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getOpsJudgementList_V1(
			@RequestParam(required = true) String goodsId){//商品ID是必须的
		logger.info("in goods/judgement/opsList goodsId: " + goodsId);
		
		Message message = Message.success();
		GoodsResultVO goodsResultVO = goodsMainDAO.getGoodsById(goodsId);
		if(goodsResultVO == null){
			return Message.error(BizReturnCode.NOT_FIND_GOODS);
		}
		//获取运营部门提供的较好的对商品评价，目前只是按从所有的评价中按时间倒序取前3条
		PageHelper.startPage(1, 3);
		Page<GoodsJudgementListResultVO> page = (Page<GoodsJudgementListResultVO>)tradeJudgementService.getGoodsJudgementOpsList(goodsId);
		message.addData("judgementCount", page.getTotal());
		message.addData("judgementList", page.getResult());
		
		logger.info("out goods/judgement/opsList goodsId: " + goodsId);
		
		return message;
	}

}
