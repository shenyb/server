package com.need.api.web.controller.goods;

import com.alibaba.fastjson.JSONObject;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsDetailDAO;
import com.need.common.core.service.goods.GoodsDetailService;
import com.need.common.domain.po.api.goods.GoodsDetailPO;
import com.need.common.domain.pub.Message;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiehao 2015年8月19日 下午2:58:10
 * @ClassName GetGoodsParamsController
 * @Description TODO 获取商品详情页的商品参数
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月19日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.GOODS_PARAMS)
public class GetGoodsParamsController {
	
	private static final Logger logger = Logger.getLogger(GetGoodsParamsController.class);
	
	@Autowired
	private GoodsDetailService goodsDetailService;
	
	@Autowired
	private GoodsDetailDAO goodsDetailDAO;
	
	
	@ParamsValidate(value={
			@ParamValidate(name="goodsId",notNull=true,code=BizReturnCode.NOT_FIND_GOODS,regex="^\\w\\S*$")})
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, params = "apiVersion=1.0")
	public Message getGoodsParamsById(
			@RequestParam(required = true)String goodsId){//商品ID是必须的
		logger.info("in goods/params  goodsId: " + goodsId);
		
		Message message = Message.success();
		
		GoodsDetailPO goodsDetailPO = goodsDetailDAO.selectByPrimaryKey(goodsId);
		if(goodsDetailPO == null){
			return Message.error(BizReturnCode.NOT_FIND_GOODS);
		}
		
		JSONObject goodsParam = goodsDetailService.getGoodsParamsById(goodsId);
		//目前商品统一只有这4中属性
		message.addData("size", goodsParam.getString("size"));
		message.addData("color", goodsParam.getString("color"));
		message.addData("originPlace", goodsParam.getString("originPlace"));
		message.addData("weight", goodsParam.getString("weight"));
		
		logger.info("out goods/params  goodsId: " + goodsId);
		
		return message;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, params = "apiVersion=1.1")
	public Message getGoodsParamsById_V1_1(
			@RequestParam(required = true)String goodsId){//商品ID是必须的
		logger.info("in goods/params  goodsId: " + goodsId);
		
		Message message = Message.success();
		
		GoodsDetailPO goodsDetailPO = goodsDetailDAO.selectByPrimaryKey(goodsId);
		if(goodsDetailPO == null){
			return Message.error(BizReturnCode.NOT_FIND_GOODS);
		}
		
		JSONObject goodsParam = goodsDetailService.getGoodsParamsById(goodsId);
		//目前商品统一只有这4中属性
		message.addData("size", goodsParam.getString("size"));
		message.addData("color", goodsParam.getString("color"));
		message.addData("originPlace", goodsParam.getString("originPlace"));
		if(NumberUtils.isNumber(goodsParam.getString("weight"))){
			message.addData("weight", goodsParam.getString("weight") + " kg");
		}
		else{
			message.addData("weight", goodsParam.getString("weight"));
		}
		
		logger.info("out goods/params  goodsId: " + goodsId);
		
		return message;
	}
}
