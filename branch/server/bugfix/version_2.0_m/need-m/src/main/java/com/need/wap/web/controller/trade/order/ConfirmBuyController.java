package com.need.wap.web.controller.trade.order;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsItemsDAO;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.service.trade.IConfirmBuy;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.core.service.user.UserService;
import com.need.common.domain.enums.GoodsTypeEnums;
import com.need.common.domain.enums.OrderTypeEnum;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.goods.GoodsItemsPO;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.ConfigTradeVO;
import com.need.common.domain.vo.trade.CreateTradeParamVO;
import com.need.common.domain.vo.trade.TradeCartVO;
import com.need.jedis.JedisSentinelClient;
import com.need.jedis.constants.RedisKeyConstant;
import com.need.wap.constant.ControllerMappings;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * @author Rylan 2015年12月2日 下午9:02:00
 * @ClassName ConfirmBuyController
 * @Description 提交订单接口
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年12月2日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
public class ConfirmBuyController {
	
	private static final Logger logger = Logger.getLogger(ConfirmBuyController.class);
	
	@Resource
	private IConfirmBuy taxWarehouseConfirmBuy;
	@Resource
	private IConfirmBuy overseaWarehouseConfirmBuy;
	@Resource
	private IConfirmBuy selfWarehouseConfirmBuy;
	@Autowired
	private UserService userService;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private GoodsItemsDAO goodsItemsDAO;
	@Autowired
	private TradeBaseService tradeBaseService;
	
	/**
	 * @author Rylan 2015年12月6日 上午12:24:30
	 * @Method: confirmTrade 
	 * @Description: 普通下单接口
	 * @param request
	 * @param configTradeVO
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value=ControllerMappings.TRADE_CONFIRM_BUY,method = RequestMethod.POST)
	public Message confirmTrade(HttpServletRequest request, @RequestBody ConfigTradeVO configTradeVO) throws Exception{	
		Message message = Message.success();
        TradeCartVO[] originalTradeCartVOArray = configTradeVO.getTradeCartVOArray();
		logger.info("confirmTrade in.parm :" + Arrays.toString(originalTradeCartVOArray) + "  userId :"+ configTradeVO.getUserId());
        String warehouseType = configTradeVO.getWarehouseType();
		WarehouseTypeEnum warehouseTypeEnum = WarehouseTypeEnum.valueOf(warehouseType);
		CreateTradeParamVO paramVO = new CreateTradeParamVO();
		paramVO.setAddressId(configTradeVO.getAddressId());
		paramVO.setUserId(configTradeVO.getUserId());
		paramVO.setUseBalance(null);
		paramVO.setCouponNo(null);
		paramVO.setCertificationChannel("WAP");
		paramVO.setRecords(checkHasItemsGoods(originalTradeCartVOArray));
		logger.info("warehouseTypeEnum is  :" + warehouseTypeEnum);		
		
		switch (warehouseTypeEnum) {
			case TAX_WAREHOUSE:
				message = taxWarehouseConfirmBuy.createTrade(paramVO);
				break;
			case SELF_WAREHOUSE:
				message = selfWarehouseConfirmBuy.createTrade(paramVO);

				break;
			case OVERSEA_WAREHOUSE:
				message = overseaWarehouseConfirmBuy.createTrade(paramVO);
				break;
			default:
				break;
			}
		if (message.getCode() == Message.SUCCESS) {
			tradeBaseService.saveTradeItemsGoodsRecord(originalTradeCartVOArray, message, warehouseType);
		}
		
		logger.info("confirmTrade out:" + message.getData());		
		return message;
	}

	
	/**
	 * @author Rylan 2015年12月6日 上午12:29:26
	 * @Method: retailingConfirmTrade 
	 * @Description: 分销下单接口
	 * @param request
	 * @param configTradeVO
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value=ControllerMappings.RETAILING_TRADE_CONFIRM_BUY,method = RequestMethod.POST)
	public Message retailingConfirmTrade(HttpServletRequest request, @RequestBody ConfigTradeVO configTradeVO,String shareUserToken) throws Exception{	
		Message message = Message.success();
		logger.info("retailingConfirmTrade in.parm :" + "goodsId :"+configTradeVO.getGoodsId()+" count :" +configTradeVO.getGoodsCount()+ "  userId :"+ configTradeVO.getUserId()+" sut:"+configTradeVO.getSut());		
        String warehouseType = configTradeVO.getWarehouseType();
		WarehouseTypeEnum warehouseTypeEnum = WarehouseTypeEnum.valueOf(warehouseType);
		CreateTradeParamVO paramVO = new CreateTradeParamVO();
		paramVO.setAddressId(configTradeVO.getAddressId());
		paramVO.setUserId(configTradeVO.getUserId());
		
		paramVO.setUseBalance(null);
		paramVO.setCouponNo(null);
		paramVO.setCertificationChannel("WAP");
		paramVO.setOrderType(OrderTypeEnum.DISTRIBUTION.code);
		
		//token 检查
		if(StringUtils.isEmpty(configTradeVO.getSut())){
			return Message.error(BizReturnCode.TRADE_CONFIRM_URL_ERROR);
		}
		//获取userId
		String shareUserId= userService.getUserIdBysut(configTradeVO.getSut());		
		if(StringUtils.isEmpty(shareUserId)){
			return Message.error(BizReturnCode.SYSTEM_USER_TOKEN_OLD);
		}
		
		//分享者id
		paramVO.setShareUserId(shareUserId);
		//设置商品信息
		TradeCartVO  tradeCartVO=new TradeCartVO();
		tradeCartVO.setGoodsId(configTradeVO.getGoodsId());
		tradeCartVO.setGoodsCount(configTradeVO.getGoodsCount());
		tradeCartVO.setCartId("");//暂时设空
		configTradeVO.setTradeCartVOArray(tradeCartVO);
        TradeCartVO[] originalTradeCartVOArray = configTradeVO.getTradeCartVOArray();
		paramVO.setRecords(checkHasItemsGoods(originalTradeCartVOArray));
		
		
		logger.info("warehouseTypeEnum is  :" + warehouseTypeEnum+"paramVO :"+paramVO);		
		//分仓库属性处理
		switch (warehouseTypeEnum) {
			case TAX_WAREHOUSE:
				//报税仓特殊处理
				if(configTradeVO.getGoodsCount() > 1){
					return Message.error(BizReturnCode.TRADE_CONFIRM_ORDER_EXCEED_ERROR);
				}				
				message = taxWarehouseConfirmBuy.createTrade(paramVO);
				break;
			case SELF_WAREHOUSE:
				message = selfWarehouseConfirmBuy.createTrade(paramVO);
				break;
			case OVERSEA_WAREHOUSE:
				message = overseaWarehouseConfirmBuy.createTrade(paramVO);
				break;
			default:
				break;
			}
		if (message.getCode() == Message.SUCCESS) {
			tradeBaseService.saveTradeItemsGoodsRecord(originalTradeCartVOArray, message, warehouseType);
		}
		
		logger.info("retailingConfirmTrade out:" + message.getData());		
		return message;
	}

	private TradeCartVO[] checkHasItemsGoods(TradeCartVO[] tradeCartVOArray) {
		List<TradeCartVO> cartList = new ArrayList<TradeCartVO>();
		for (TradeCartVO tradeCartVO : tradeCartVOArray) {
			String goodsId = tradeCartVO.getGoodsId();
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(goodsId);
			if (GoodsTypeEnums.SINGLE.code.equals(goods.getGoodsType())) {
				addCartList(cartList, tradeCartVO);
				continue;
			}
			if (GoodsTypeEnums.MORE.code.equals(goods.getGoodsType())) {
				List<GoodsItemsPO> goodsItemsList = goodsItemsDAO.queryByGoodsGroupId(goodsId);
				if (null != goodsItemsList && goodsItemsList.size() > 0) {
					for (GoodsItemsPO goodsItems : goodsItemsList) {
						TradeCartVO goodsItemsCart = new TradeCartVO();
						goodsItemsCart.setGoodsCount(goodsItems.getGoodsCount() * tradeCartVO.getGoodsCount());
						goodsItemsCart.setGoodsId(goodsItems.getGoodsId());
						addCartList(cartList, goodsItemsCart);
					}
				}
			}
		}
		int size = cartList.size();
		TradeCartVO[] resultCartArray = (TradeCartVO[]) cartList.toArray(new TradeCartVO[size]);
		return resultCartArray;
	}

	private void addCartList(List<TradeCartVO> cartList, TradeCartVO tradeCartVO) {
		boolean isExist = false;
		if (null != cartList && cartList.size() == 0) {
			cartList.add(tradeCartVO);
			return;
		}
		if (null != cartList && cartList.size() > 0) {
			for (TradeCartVO cart : cartList) {
				if (cart.getGoodsId().equals(tradeCartVO.getGoodsId())) {
					isExist = true;
					cart.setGoodsCount(cart.getGoodsCount() + tradeCartVO.getGoodsCount());
					break;
				}
			}
			if (!isExist) {
				cartList.add(tradeCartVO);
			}
		}
	}
	
}
