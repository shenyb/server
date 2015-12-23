package com.need.api.web.controller.trade.address;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.trade.TradeAddressService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.AddressParamVO;
import com.need.utils.StringUtil;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiehao 2015年8月19日 下午4:15:57
 * @ClassName UpdateAddressController
 * @Description TODO 更新地址信息
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月19日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.TRADE_ADDRESS_UPDATE)
public class UpdateAddressController {

	private static final Logger logger = Logger.getLogger(UpdateAddressController.class);

	@Autowired
	private TradeBaseService tradeBaseService;
	@Autowired
	private TradeAddressService tradeAddressService;

	@ParamsValidate(value = {
			@ParamValidate(name = "addressId", notNull = true, code = BizReturnCode.TRADE_ADDRESS_NOT_NUM) })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, params = "apiVersion=1.0")
	public Message updateAddress(AddressParamVO addressParamVO) {

		logger.info("in trade/address/edit addressParamVO: " + addressParamVO);
		if (!StringUtil.isBlank(addressParamVO.getCertificationCard())) {
			addressParamVO.setCertificationCard(addressParamVO.getCertificationCard().toUpperCase());
		}
		tradeAddressService.updateAddress(addressParamVO);
		logger.info("out trade/address/edit addressParamVO: " + addressParamVO);
		return Message.success();
	}

	@ParamsValidate(value = {
			@ParamValidate(name = "addressId", notNull = true, code = BizReturnCode.TRADE_ADDRESS_NOT_NUM) })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, params = "apiVersion=1.1")
	public Message updateAddressV1_1(AddressParamVO addressParamVO) {

		logger.info("in trade/address/edit addressParamVO: " + addressParamVO);
		if (!StringUtil.isBlank(addressParamVO.getCertificationCard())) {
			addressParamVO.setCertificationCard(addressParamVO.getCertificationCard().toUpperCase());
		}
		tradeAddressService.updateAddress(addressParamVO);
		logger.info("out trade/address/edit addressParamVO: " + addressParamVO);
		return Message.success();
	}

	@ParamsValidate(value = {
			@ParamValidate(name = "addressId", notNull = true, code = BizReturnCode.TRADE_ADDRESS_NOT_NUM) })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, params = "apiVersion=1.2")
	public Message updateAddressV_1_2(AddressParamVO addressParamVO) {
		Message message = Message.success();
		logger.info("in trade/address/edit addressParamVO: " + addressParamVO);
		if (!StringUtil.isBlank(addressParamVO.getCertificationCard())) {
			addressParamVO.setCertificationCard(addressParamVO.getCertificationCard().toUpperCase());
		}
		// add by shenyb 2015.10.15 身份认证信息
		if (!StringUtil.isBlank(addressParamVO.getCertificationCard())) {
			Message result = tradeBaseService.certificationCardVerify(addressParamVO.getReceiver(),
					addressParamVO.getCertificationCard(), addressParamVO.getUserId(),
					addressParamVO.getCertificationChannel());
            if(BizReturnCode.TRADE_CERTIFICATION_TIMEOUT == result.getCode()) {
                message = Message.error(BizReturnCode.TRADE_CERTIFICATION_TIMEOUT);
            } else if (Message.SUCCESS != result.getCode()) {
				return Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);
			}
		}
		tradeAddressService.updateAddress(addressParamVO);
		logger.info("out trade/address/edit addressParamVO: " + addressParamVO);
		return message;

	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, params = "apiVersion=1.3")
	public Message updateAddressV_1_3(AddressParamVO addressParamVO) {
		Message message = Message.success();
		logger.info("in trade/address/edit addressParamVO: " + addressParamVO);
		if (!StringUtil.isBlank(addressParamVO.getCertificationCard())) {
			addressParamVO.setCertificationCard(addressParamVO.getCertificationCard().toUpperCase());
		}
		int isBlankAddress = tradeAddressService.hasBlankofAddress(addressParamVO);
		if(isBlankAddress!=200){
			return Message.error(isBlankAddress);
		}
		// add by shenyb 2015.10.15 身份认证信息
		if (!StringUtil.isBlank(addressParamVO.getCertificationCard())) {
			Message result = tradeBaseService.certificationCardVerify(addressParamVO.getReceiver(),
					addressParamVO.getCertificationCard(), addressParamVO.getUserId(),
					addressParamVO.getCertificationChannel());
            if(BizReturnCode.TRADE_CERTIFICATION_TIMEOUT == result.getCode()) {
                message = Message.error(BizReturnCode.TRADE_CERTIFICATION_TIMEOUT);
            } else if (Message.SUCCESS != result.getCode()) {
				return Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);
			}
		}
		tradeAddressService.updateAddress_V1_3(addressParamVO);
		logger.info("out trade/address/edit addressParamVO: " + addressParamVO);
		return message;

	}
}
