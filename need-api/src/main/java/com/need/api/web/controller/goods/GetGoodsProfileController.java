package com.need.api.web.controller.goods;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.need.NeedGoodsDAO;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.service.goods.GoodsMainService;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.goods.GoodsResultVO;
import com.need.utils.StringUtil;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xiehao 2015年8月11日 下午3:19:14
 * @ClassName GetGoodsProfileController
 * @Description TODO 获取商品基本信息
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月11日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.GOODS_PROFILE)
public class GetGoodsProfileController {

	private static final Logger logger = Logger.getLogger(GetGoodsProfileController.class);
	
	@Autowired
	private GoodsMainService goodsMainService;
	
	@Autowired
	private NeedGoodsDAO needGoodsDAO;
	
	@Autowired
	private UserBaseDAO userBaseDAO;
	
	@ParamsValidate(value={
			@ParamValidate(name="goodsId",notNull=true,code=BizReturnCode.NOT_FIND_GOODS,regex="^\\w\\S*$"),
			@ParamValidate(name="userId",notNull=true,code=BizReturnCode.USERID_NOT_EXIST,regex="^\\w\\S*$")})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getGoodsProfile_V1(
			@RequestParam(required = true)String goodsId,
			@RequestParam(required = true)String userId){//用户ID和商品Id 是必须的
		logger.info("in goods/profile goodsId: " + goodsId +"\t userId: " + userId);
		
		Message message = Message.success();
		
		GoodsResultVO goodsResultVO = goodsMainService.getGoodsById(goodsId);
		
		if(goodsResultVO == null){
			return Message.error(BizReturnCode.NOT_FIND_GOODS);
		}
		UserBasePO userBasePO = userBaseDAO.selectByPrimaryKey(userId);
		if(userBasePO == null){
			return Message.error(BizReturnCode.USER_NOT_EXIST);
		}
		
		message.addData("goodsId", goodsResultVO.getGoodsId());
		message.addData("goodsName", goodsResultVO.getGoodsName());
		message.addData("onsalePrice", goodsResultVO.getOnsalePrice());
		message.addData("brief", goodsResultVO.getBrief());
		message.addData("discountPrice", goodsResultVO.getDiscountPrice());
		//把商品主图封装成List
		List<String> picList = StringUtil.stringToList(goodsResultVO.getTopPicKeys(), ",");
		message.addData("topPicKeys", picList);
		
		message.addData("need", needGoodsDAO.countIsNeeded(userId, goodsId).toString().toUpperCase());
		logger.info("out goods/profile goodsId: " + goodsId +"\t userId: " + userId);
		
		return message;
	}
	
	/**
	 * @author xiehao 2015年9月9日 下午2:06:57
	 * @Method: getGoodsProfile_V1_1 
	 * @Description: TODO 1.1版本，返回值新增一个字段，isGlobal是否是保税仓TRUE/FALSE
	 * @param goodsId
	 * @param userId
	 * @return 
	 * @throws
	 */
	@ParamsValidate(value={
			@ParamValidate(name="goodsId",notNull=true,code=BizReturnCode.NOT_FIND_GOODS,regex="^\\w\\S*$")})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getGoodsProfile_V1_1(
			@RequestParam(required = true)String goodsId,//商品ID是必须的
			@RequestParam(required = false)String userId){//用户ID不是必须的
		logger.info("in goods/profile goodsId: " + goodsId +"\t userId: " + userId);
		
		Message message = Message.success();
		
		if(!StringUtils.hasText(userId)){
			userId = "";
		}
		
		GoodsResultVO goodsResultVO = goodsMainService.getGoodsById(goodsId);
		
		if(goodsResultVO == null){
			return Message.error(BizReturnCode.NOT_FIND_GOODS);
		}
		
		message = goodsMainService.getGoodsProfileById(goodsId, userId);
		
		logger.info("out goods/profile goodsId: " + goodsId +"\t userId: " + userId);
		
		return message;
	}
	
	@ParamsValidate(value={
			@ParamValidate(name="goodsId",notNull=true,code=BizReturnCode.NOT_FIND_GOODS,regex="^\\w\\S*$")})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.2", method = RequestMethod.GET)
	public Message getGoodsProfile_V1_2(
			@RequestParam(required = true)String goodsId,//商品ID是必须的
			@RequestParam(required = false)String userId){//用户ID不是必须的
		logger.info("in goods/profile goodsId: " + goodsId +"\t userId: " + userId);
		
		
		Message message = Message.success();
		
		if(!StringUtils.hasText(userId)){
			userId = "";
		}
		
		GoodsResultVO goodsResultVO = goodsMainService.getGoodsById(goodsId);
		
		if(goodsResultVO == null){
			return Message.error(BizReturnCode.NOT_FIND_GOODS);
		}
		
		message = goodsMainService.getGoodsProfileById(goodsId, userId);
		
		logger.info("out goods/profile goodsId: " + goodsId +"\t userId: " + userId);
		
		return message;
	}
	
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.GET)
	public Message getGoodsProfile_V1_3(
			@RequestParam(required = true)String goodsId,//商品ID是必须的
			@RequestParam(required = false)String userId){//用户ID不是必须的
		logger.info("in goods/profile goodsId: " + goodsId +"\t userId: " + userId);
		
		
		Message message = Message.success();
		
		if(!StringUtils.hasText(userId)){
			userId = "";
		}
		
		GoodsResultVO goodsResultVO = goodsMainService.getGoodsById(goodsId);
		
		if(goodsResultVO == null){
			return Message.error(BizReturnCode.NOT_FIND_GOODS);
		}
		
		message = goodsMainService.getGoodsProfileById_V1_3(goodsId, userId);
		
		logger.info("out goods/profile goodsId: " + goodsId +"\t userId: " + userId);
		
		return message;
	}
	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.GET)
	public Message getGoodsProfile_V2_0(
			@RequestParam(required = true)String goodsId,//商品ID是必须的
			@RequestParam(required = false)String userId){//用户ID不是必须的
		logger.info("in goods/profile goodsId: " + goodsId +"\t userId: " + userId);
		
		
		Message message = Message.success();
		
		if(!StringUtils.hasText(userId)){
			userId = "";
		}
		message = goodsMainService.getGoodsProfileById_V2_0(goodsId, userId);
		
		logger.info("out goods/profile goodsId: " + goodsId +"\t userId: " + userId);
		
		return message;
	}
}
