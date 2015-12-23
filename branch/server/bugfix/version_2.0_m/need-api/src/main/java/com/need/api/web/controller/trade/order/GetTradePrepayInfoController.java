package com.need.api.web.controller.trade.order;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.pub.ConstantsProConfig;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.ConfirmBuyReturnTradeInfoVO;
import com.need.common.domain.vo.trade.TradeAddressVO;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(ControllerMappings.GET_TRADE_PREPAY_INFO)
public class GetTradePrepayInfoController {

	private static final Logger logger = Logger.getLogger(GetTradePrepayInfoController.class);

	@Autowired
	private ConstantsProConfig constantsProConfig;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private TradeBaseDAO tradeBaseDAO;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.2", method = RequestMethod.GET)
	public Message getTradePrepayInfo(@RequestParam(required = true) String tradeNo) {
		return Message.error(BizReturnCode.TRADE_SYSTEM_VERSION_TOO_LOW);
		// logger.info("GetTradePrepayInfoController getTradePrepayInfo
		// in..tradeNo :" + tradeNo);
		// Message message = Message.success();
		// List<TradeBasePO> baseList = tradeBaseDAO.getByTradeNo(tradeNo);
		// if (baseList == null || baseList.size() == 0) {
		// return Message.error(BizReturnCode.TRADE_NOT_EXIST);
		// }
		// TradeBasePO trade = baseList.get(0);
		// String isGoodsGlobal =
		// goodsMainDAO.getGoodsGlobal(trade.getGoodsId());
		// String isSupportALPAY = Boolean.TRUE.toString().toUpperCase();
		// String isSupportWECHAT = Boolean.TRUE.toString().toUpperCase();
		// String globalONALPAY = constantsProConfig.getAlipayServiceStatus();
		// String globalONWECHAT = constantsProConfig.getWechatServiceStatus();
		// ConfirmBuyReturnTradeInfoVO info = new ConfirmBuyReturnTradeInfoVO();
		// info.setCreateTime(trade.getCreateTime().getTime());
		// info.setPayMoney(trade.getPayPrice());
		// info.setUserTradeNo(trade.getUserTradeNo());
		// // 保税仓订单只看开关，非保税仓全开
		// if (Boolean.TRUE.toString().toUpperCase().equals(isGoodsGlobal)) {
		// isSupportALPAY = globalONALPAY;
		// isSupportWECHAT = globalONWECHAT;
		// }
		//
		// info.setIsSupportALPAY(isSupportALPAY);
		// info.setIsSupportWECHAT(isSupportWECHAT);
		// message.addData("tradeInfo", info);
		// // 增加地址信息
		// // 封装地址信息
		// TradeAddressVO addressVO = new TradeAddressVO();
		// BeanUtils.copyProperties(baseList.get(0), addressVO);
		//
		// addressVO.setMyAddress(baseList.get(0).getLogisticsValue() + "-" +
		// baseList.get(0).getAddress());
		// message.addData("address", addressVO);
		// return message;

	}

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.GET)
	public Message getTradePrepayInfoV1_3(@RequestParam(required = true) String tradeNo) {
		logger.info("GetTradePrepayInfoController getTradePrepayInfo in..tradeNo :" + tradeNo);
		Message message = Message.success();
		List<TradeBasePO> baseList = tradeBaseDAO.getByTradeNo(tradeNo);
		if (baseList == null || baseList.size() == 0) {
			return Message.error(BizReturnCode.TRADE_NOT_EXIST);
		}
		TradeBasePO trade = baseList.get(0);
		GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(trade.getGoodsId());
		String isSupportALPAY = Boolean.TRUE.toString().toUpperCase();
		String isSupportWECHAT = Boolean.TRUE.toString().toUpperCase();
		String globalONALPAY = constantsProConfig.getAlipayServiceStatus();
		String globalONWECHAT = constantsProConfig.getWechatServiceStatus();
		ConfirmBuyReturnTradeInfoVO info = new ConfirmBuyReturnTradeInfoVO();
		info.setCreateTime(trade.getCreateTime().getTime());
		info.setPayMoney(baseList.get(0).getPayPrice());
		info.setUserTradeNo(trade.getUserTradeNo());
		String warehouseType = goods.getWarehouseType();
		if (goods != null) {
			warehouseType = goods.getWarehouseType();
		}
		if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)
				|| WarehouseTypeEnum.TAX_WAREHOUSE.code.equals(warehouseType)) {
			isSupportALPAY = globalONALPAY;
			isSupportWECHAT = globalONWECHAT;
		}
		info.setIsSupportALPAY(isSupportALPAY);
		info.setIsSupportWECHAT(isSupportWECHAT);
		message.addData("tradeInfo", info);
		// 增加地址信息
		// 封装地址信息
		TradeAddressVO addressVO = new TradeAddressVO();
		BeanUtils.copyProperties(baseList.get(0), addressVO);

		addressVO.setMyAddress(baseList.get(0).getLogisticsValue() + "-" + baseList.get(0).getAddress());
		message.addData("address", addressVO);
		return message;

	}

}
