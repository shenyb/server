package com.need.wap.web.controller.trade.order;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.need.wap.constant.ControllerMappings;

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
	@RequestMapping(method = RequestMethod.GET)
	public Message getTradePrepayInfoV1_3(@PathVariable String tradeNo) {
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
