package com.need.api.web.controller.trade.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.goods.GoodsItemsDAO;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeCartDAO;
import com.need.common.core.pub.ServiceException;
import com.need.common.core.service.trade.IConfirmBuy;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.enums.GoodsTypeEnums;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.goods.GoodsItemsPO;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.CreateTradeParamVO;
import com.need.common.domain.vo.trade.TradeCartCompleteParma;
import com.need.common.domain.vo.trade.TradeCartVO;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年10月12日 上午10:04:24
 * @ClassName ConfirmBuyController
 * @Description 确认购买
 * @version V1.2
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年10月12日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CONFIRM_BUY)
public class ConfirmBuyController {
	@Autowired
	private TradeBaseService tradeBaseService;
	@Resource
	private IConfirmBuy taxWarehouseConfirmBuy;
	@Resource
	private IConfirmBuy overseaWarehouseConfirmBuy;
	@Resource
	private IConfirmBuy selfWarehouseConfirmBuy;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private GoodsItemsDAO goodsItemsDAO;
	@Autowired
	private TradeCartDAO tradeCartDAO;

	private static final Logger logger = Logger.getLogger(ConfirmBuyController.class);

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.2", method = RequestMethod.POST)
	public Message confirmTrade(HttpServletRequest request, TradeCartCompleteParma param,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String addressId,
			@RequestParam(required = false) String couponNo,
			@RequestParam(required = false) String certificationChannel) {
		return Message.error(BizReturnCode.TRADE_SYSTEM_VERSION_TOO_LOW);
		//
		// Message message = Message.success();
		// logger.info("ConfirmBuyController in.parm.getGoodsList :" +
		// param.getGoodsList() + " userId :"
		// + param.getUserId());
		// TradeCartVO[] tradeCartVOArray =
		// JSON.parseObject(param.getGoodsList(), new
		// TypeReference<TradeCartVO[]>() {
		// });// 转换对象
		// String tradeNo =
		// BizCodeUtil.generateTradeNo(tradeCartVOArray[0].getGoodsId(),
		// userId);// 生成交易号
		// return
		// tradeBaseService.addTradeBaseService_V1_2(message,tradeCartVOArray,addressId,
		// userId, tradeNo,couponNo,certificationChannel);
	}

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.POST)
	public Message confirmTradeV1_3(HttpServletRequest request, TradeCartCompleteParma param,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String addressId,
			@RequestParam(required = false) String couponNo,
			@RequestParam(required = false) String certificationChannel,
			@RequestParam(required = false) String warehouseType) {
		Message message = Message.success();
		logger.info("ConfirmBuyController in.parm.getGoodsList :" + param.getGoodsList() + "  userId :"
				+ param.getUserId());
		TradeCartVO[] originalTradeCartVOArray = JSON.parseObject(param.getGoodsList(),
				new TypeReference<TradeCartVO[]>() {
				});// 转换对象
		/**
		 * 更改购物车
		 */
		TradeCartVO[] tradeCartVOArrayGoodsItems = checkHasItemsGoods(originalTradeCartVOArray);
		Message tradeNoMessage = tradeBaseService.addTradeBaseService_V1_3(message, tradeCartVOArrayGoodsItems,
				addressId, userId, couponNo, certificationChannel, warehouseType);
		if (message.getCode() == Message.SUCCESS) {
			tradeBaseService.saveTradeItemsGoodsRecord(originalTradeCartVOArray, message, warehouseType);
		}

		return tradeNoMessage;
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

	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message confirmTradeV2_0(HttpServletRequest request, TradeCartCompleteParma param,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String addressId,
			@RequestParam(required = false) String couponNo, @RequestParam(required = true) String useBalance,
			@RequestParam(required = false) String certificationChannel,
			@RequestParam(required = true) String warehouseType) throws ServiceException {
		Message message = Message.success();
		logger.info("ConfirmBuyController in.parm.getGoodsList :" + param.getGoodsList() + "  userId :"
				+ param.getUserId());
		TradeCartVO[] originalTradeCartVOArray = JSON.parseObject(param.getGoodsList(),
				new TypeReference<TradeCartVO[]>() {
				});

		WarehouseTypeEnum warehouseTypeEnum = WarehouseTypeEnum.valueOf(warehouseType);
		if (warehouseTypeEnum == WarehouseTypeEnum.TAX_WAREHOUSE) {
			int buyCount = 0;
			for (TradeCartVO tradeCartVO : originalTradeCartVOArray) {
				buyCount += tradeCartVO.getGoodsCount();
				if (buyCount > Constants.TAX_WAREHOUSE_BUY_ONCE_MAX) {
					return Message.error(BizReturnCode.TRADE_TAX_WAREHOUSE_BUY_LIMIT);
				}
			}
		}
		CreateTradeParamVO paramVO = transferToVO(param, userId, addressId, couponNo, certificationChannel, useBalance,
				warehouseType);
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

		// 删除购物车
		List<String> cartIdsList = new ArrayList<String>();
		for (TradeCartVO tradeCartVO : originalTradeCartVOArray) {
			if (!StringUtil.isBlank(tradeCartVO.getCartId())) {
				cartIdsList.add(tradeCartVO.getCartId());
			}
		}
		if (cartIdsList.size() > 0) {
			tradeCartDAO.batchDeleteByCartIds(cartIdsList);
		}

		return message;
	}

	private CreateTradeParamVO transferToVO(TradeCartCompleteParma param, String userId, String addressId,
			String couponNo, String certificationChannel, String useBalance, String warehouseType) {
		TradeCartVO[] tradeCartVOArray = JSON.parseObject(param.getGoodsList(), new TypeReference<TradeCartVO[]>() {
		});// 转换对象
		/**
		 * 谢浩，更改购物车 ,如果商品类型是套装商品则拆分
		 */
		logger.info(String.format("tradecart:goodsList:%s", JSON.toJSONString(param.getGoodsList())));
		TradeCartVO[] tradeCartVOArrayGoodsItems = checkHasItemsGoods(tradeCartVOArray);
		logger.info(String.format("tradecart:tradeCartVOArrayGoodsItems:%s",
				JSON.toJSONString(tradeCartVOArrayGoodsItems)));
		CreateTradeParamVO paramVO = new CreateTradeParamVO();
		paramVO.setAddressId(addressId);
		paramVO.setCouponNo(couponNo);
		paramVO.setRecords(tradeCartVOArrayGoodsItems);
		paramVO.setUseBalance(useBalance);
		paramVO.setUserId(userId);
		paramVO.setCertificationChannel(certificationChannel);
		return paramVO;
	}
}
