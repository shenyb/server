package com.need.api.web.controller.goods;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.service.trade.TradeJudgementService;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.pub.Message;
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

import java.util.List;

/**
 * <p></p>
 * @author xiehao 2015年8月19日 下午3:33:13
 * @ClassName GetGoodsJudgementListController
 * @Description TODO 获得对该商品的所有的评价
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月19日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.GOODS_JUDGEMENT_LSIT)
public class GetGoodsJudgementListController {
	
	private static final Logger logger = Logger.getLogger(GetGoodsJudgementListController.class);
	
	@Autowired
	private TradeJudgementService tradeJudgementService;
	
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	
	@ParamsValidate(value={
			@ParamValidate(name="goodsId",notNull=true,code=BizReturnCode.GOODS_NOT_EXIST)})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getJudgementList_V1(
			@RequestParam(required = true) String goodsId,//商品ID是必须的
			@RequestParam(required = false) Integer pageNum, //分页信息不是必须的
			@RequestParam(required = false) Integer pageSize){
		
		logger.info("in goods/judgement/list goodsId: " + goodsId);
		GoodsMainPO GoodsMainPO = goodsMainDAO.selectByPrimaryKey(goodsId);
		if(GoodsMainPO == null){
			return Message.error(BizReturnCode.NOT_FIND_GOODS);
		}
		
		Message message = Message.success();
		if(pageNum == null)//为分页信息加上默认值
			pageNum = 1;
		if(pageSize == null)
			pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);//用分页工具对结果进行分页
		Page<GoodsJudgementListResultVO> page = (Page<GoodsJudgementListResultVO>)tradeJudgementService.getGoodsJudgementOpsList(goodsId);
		List<GoodsJudgementListResultVO> judgementList = page.getResult();
		message.addData("judgementList", judgementList);
		message.addData("totalCount", page.getTotal());
		
		logger.info("out goods/judgement/list goodsId: " + goodsId);
		
		return message;
	}

}
