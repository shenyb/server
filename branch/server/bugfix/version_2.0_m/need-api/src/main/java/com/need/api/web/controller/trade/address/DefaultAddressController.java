package com.need.api.web.controller.trade.address;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.trade.TradeAddressDAO;
import com.need.common.core.service.trade.TradeAddressService;
import com.need.common.domain.po.api.trade.TradeAddressPO;
import com.need.common.domain.pub.Message;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p></p>
 * @author xiehao 2015年8月13日 下午2:46:11
 * @ClassName DefaultAddressController
 * @Description TODO 用户设置默认地址
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月13日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.DEFAULT_ADDRESS)
public class DefaultAddressController {
	
	private static final Logger logger = Logger.getLogger(DefaultAddressController.class);
	
	@Autowired
	TradeAddressService tradeAddressService;
	
	@Autowired
	TradeAddressDAO tradeAddressDAO;
	
	@ParamsValidate(value={
			@ParamValidate(name="oldAddressId",notNull=true,code=BizReturnCode.TRADE_OLD_ADDRESS),
			@ParamValidate(name="addressId",notNull=true,code=BizReturnCode.TRADE_NEW_ADDRESS)})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message deleteAddress(
			@RequestParam(required = true) String oldAddressId, 
			@RequestParam(required = true)String addressId,
			String userId)  {
		
		logger.info("deleteAddress in......oldAddressId: " + oldAddressId + "\t addressId: " + addressId);
		TradeAddressPO tradeAddressPO = tradeAddressDAO.selectByPrimaryKey(oldAddressId);
		if(tradeAddressPO == null){
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		tradeAddressPO = tradeAddressDAO.selectByPrimaryKey(addressId);
		if(tradeAddressPO == null){
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		
		Message message = Message.success();
		tradeAddressService.updateDefaultAddress(oldAddressId,addressId,userId);	
		return message;
	}

}
