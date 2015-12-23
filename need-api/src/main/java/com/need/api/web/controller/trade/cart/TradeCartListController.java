package com.need.api.web.controller.trade.cart;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeCartDAO;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.trade.TradeCartPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.*;
import com.need.utils.StringUtil;
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
/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月8日 下午12:24:03
 * @ClassName TradeCartListController
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CART_LIST)
public class TradeCartListController {
	private Logger logger = Logger.getLogger(TradeCartListController.class);

	@Autowired
	private TradeCartDAO tradeCartDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;

	@ParamsValidate(value = { @ParamValidate(name = "userId", notNull = true, code = 2015) })
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message listCart(@RequestParam(required = true) String userId,
			@RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize)
					throws Exception {
		logger.info(String.format("TradeCartListController.listCart:params:%s,%d,%d", userId, pageNum, pageSize));
		Message success = Message.success();
		PageHelper.startPage(pageNum, pageSize);
		Page<TradeCartPO> page = (Page<TradeCartPO>) tradeCartDAO.selectByPage(userId);
		List<TradeCartPO> goodsList = page.getResult();
		List<TradeCartVO> result = new ArrayList<TradeCartVO>();
		TradeCartVO vo = null;
		// 如果购物车存在，但是商品不存在，则返回给客户端的总数要正确
		int goodsCountTmp = 0;
		for (TradeCartPO po : goodsList) {
			vo = new TradeCartVO();
			vo.setCartId(po.getCartId());
			vo.setGoodsCount(po.getGoodsCount());
			String goodsId = po.getGoodsId();
			GoodsMainPO goodsTmp = goodsMainDAO.selectByPrimaryKey(goodsId);
			if (goodsTmp == null) {
				tradeCartDAO.deleteByCartId(po.getCartId());
				goodsCountTmp++;
				continue;
			}
			TradeCartGoodsVO goods = new TradeCartGoodsVO();
			goods.setGoodsId(goodsTmp.getGoodsId());
			goods.setGoodsName(goodsTmp.getGoodsName());
			// 修改为场景图 edit by shenyanbin 20151011
			// goods.setTopicPicKey(StringUtil.stringToList(goodsTmp.getTopPicKeys(),
			// ",").get(0));
			goods.setTopicPicKey(goodsTmp.getScenePicKey());

			goods.setOnsalePrice(goodsTmp.getOnsalePrice());
			goods.setDiscountPrice(goodsTmp.getDiscountPrice());
			vo.setGoods(goods);
			result.add(vo);
		}
		success.addData("cartList", result);
		success.addData("totalCount", page.getTotal() - goodsCountTmp);
		return success;
	}

	@ParamsValidate(value = { @ParamValidate(name = "userId", notNull = true, code = 2015) })
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.GET)
	public Message listCartV1_3(@RequestParam(required = true) String userId,
			@RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize)
					throws Exception {
		logger.info(String.format("TradeCartListController.listCart:params:%s,%d,%d", userId, pageNum, pageSize));
		Message success = Message.success();
		// PageHelper.startPage(pageNum, pageSize);
		// Page<TradeCartPO> page = (Page<TradeCartPO>)
		// tradeCartDAO.selectByPage(userId);
		List<TradeCartPO> goodsList = tradeCartDAO.selectByPage(userId);
		List<TradeCartItemVO> result = new ArrayList<TradeCartItemVO>();
		TradeCartItemVO taxTradeCartItemVO = new TradeCartItemVO();
		TradeCartItemVO selfTradeCartItemVO = new TradeCartItemVO();
		TradeCartItemVO overseaTradeCartItemVO = new TradeCartItemVO();
		taxTradeCartItemVO.setWarehouseType(WarehouseTypeEnum.TAX_WAREHOUSE.code);
		taxTradeCartItemVO.setWarehouseName(WarehouseTypeEnum.TAX_WAREHOUSE.desc);
		selfTradeCartItemVO.setWarehouseType(WarehouseTypeEnum.SELF_WAREHOUSE.code);
		selfTradeCartItemVO.setWarehouseName(WarehouseTypeEnum.SELF_WAREHOUSE.desc);
		overseaTradeCartItemVO.setWarehouseType(WarehouseTypeEnum.OVERSEA_WAREHOUSE.code);
		overseaTradeCartItemVO.setWarehouseName(WarehouseTypeEnum.OVERSEA_WAREHOUSE.desc);
		result.add(taxTradeCartItemVO);
		result.add(selfTradeCartItemVO);
		result.add(overseaTradeCartItemVO);
		for (TradeCartPO po : goodsList) {
			String goodsId = po.getGoodsId();
			GoodsMainPO goodsTmp = goodsMainDAO.selectByPrimaryKey(goodsId);
			if (goodsTmp == null) {
				tradeCartDAO.deleteByCartId(po.getCartId());
				continue;
			}
			TradeCartGoodsItemVO goodsItemVO = new TradeCartGoodsItemVO();
			goodsItemVO.setCartId(po.getCartId());
			goodsItemVO.setGoodsCount(po.getGoodsCount());
			goodsItemVO.setGoods(getGoodsMainVO(goodsTmp));
			String warehouseType = goodsTmp.getWarehouseType();
			if (WarehouseTypeEnum.TAX_WAREHOUSE.code.equals(warehouseType)) {
				taxTradeCartItemVO.getGoodsList().add(goodsItemVO);
			} else if (WarehouseTypeEnum.SELF_WAREHOUSE.code.equals(warehouseType)) {
				selfTradeCartItemVO.getGoodsList().add(goodsItemVO);
			} else if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)) {
				overseaTradeCartItemVO.getGoodsList().add(goodsItemVO);
			}
		}
		success.addData("cartList", result);
		return success;
	}

	private GoodsMainVO getGoodsMainVO(GoodsMainPO goods) {

		GoodsMainVO vo = new GoodsMainVO();
		vo.setGoodsId(goods.getGoodsId());
		vo.setDiscountPrice(goods.getDiscountPrice());
		vo.setGoodsName(goods.getGoodsName());
		vo.setOnsalePrice(goods.getOnsalePrice());
		vo.setTopPicKey(StringUtil.stringToList(goods.getTopPicKeys(), ",").get(0));
		return vo;
	}
}
