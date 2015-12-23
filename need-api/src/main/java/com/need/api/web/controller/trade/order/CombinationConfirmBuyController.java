package com.need.api.web.controller.trade.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.pub.ServiceException;
import com.need.common.core.service.trade.IConfirmBuy;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.CreateCombinationTradeParamVO;
import com.need.common.domain.vo.trade.TradeCartCompleteParma;
import com.need.common.domain.vo.trade.TradeCartVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;

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
@RequestMapping(ControllerMappings.TRADE_COMBINATION_CONFIRM_BUY)
public class CombinationConfirmBuyController {
	@Resource
	private IConfirmBuy combinationConfirmBuy;
	private static final Logger logger = Logger.getLogger(ConfirmBuyController.class);

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.4", method = RequestMethod.POST)
	public Message confirmTradeV1_4(HttpServletRequest request, TradeCartCompleteParma param,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String addressId,
			@RequestParam(required = false) String couponNo, @RequestParam(required = true) String batchNo,
			@RequestParam(required = false) String useBalance, @RequestParam(required = true) String warehouseType,
			@RequestParam(required = true) String certificationChannel) {
		logger.info("ConfirmBuyController in.parm.getGoodsList :" + param.getGoodsList() + "  userId :"
				+ param.getUserId());
		CreateCombinationTradeParamVO paramVO = transferToVO(param, userId, addressId, couponNo, batchNo,
				certificationChannel, useBalance, warehouseType);
        try {
            return combinationConfirmBuy.createTrade(paramVO);
        } catch (ServiceException ex) {
            java.util.logging.Logger.getLogger(CombinationConfirmBuyController.class.getName()).log(Level.SEVERE, null, ex);
            return Message.error(ex.getCode());
        }
	}

	private CreateCombinationTradeParamVO transferToVO(TradeCartCompleteParma param, String userId, String addressId,
			String couponNo, String batchNo, String certificationChannel, String useBalance, String warehouseType) {
		TradeCartVO[] tradeCartVOArray = JSON.parseObject(param.getGoodsList(), new TypeReference<TradeCartVO[]>() {
		});// 转换对象
		CreateCombinationTradeParamVO paramVO = new CreateCombinationTradeParamVO();
		paramVO.setAddressId(addressId);
		paramVO.setBatchNo(batchNo);
		paramVO.setCouponNo(couponNo);
		paramVO.setRecords(tradeCartVOArray);
		paramVO.setUseBalance(useBalance);
		paramVO.setUserId(userId);
		paramVO.setWarehouseType(warehouseType);
		paramVO.setCertificationChannel(certificationChannel);
		return paramVO;
	}

}
