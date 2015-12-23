package com.need.wap.web.controller.trade.address;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.ControllerMappings;
import com.need.common.core.dao.jdbc.trade.TradeAddressDAO;
import com.need.common.core.service.trade.TradeAddressService;
import com.need.common.domain.po.api.trade.TradeAddressPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.AddressParamVO;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;

/**
 * <p></p>
 * @author peiboli 2015年8月13日 上午10:42:31
 * @ClassName DeleteAddressController
 * @Description TODO 删除地址
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年8月13日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.DELETE_ADDRESS)
public class DeleteAddressController {
	
	private static final Logger logger = Logger.getLogger(DeleteAddressController.class);
	
	@Autowired
	TradeAddressService tradeAddressService;
	
	@Autowired
	TradeAddressDAO tradeAddressDAO;
	
	@ParamsValidate(value={
			@ParamValidate(name="addressIdList",notNull=true,code=BizReturnCode.TRADE_ADDRESS_NOT_NUM)})
	@ResponseBody
	@RequestMapping( method = RequestMethod.POST,value="/{addressId}" )
	public Message deleteAddress(@PathVariable(value="addressId") String addrossId)  {
		logger.info("deleteAddress in.cartIds"+addrossId);
		
		TradeAddressPO tradeAddressPO = tradeAddressDAO.selectByPrimaryKey(addrossId);
		if(tradeAddressPO == null){
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		Message message = Message.success();
		tradeAddressService.deleteAddress(addrossId);
		logger.info("deleteAddress out .cartIds"+addrossId);
		return message;
	}
}
