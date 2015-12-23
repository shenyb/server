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
 * <p>
 * </p>
 * 
 * @author xiehao 2015年8月19日 下午3:55:47
 * @ClassName AddAddressController
 * @Description TODO 增加一个新的地址
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月19日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.TRADE_ADD_ADDRESS)
public class AddAddressController {

	private static final Logger logger = Logger.getLogger(AddAddressController.class);

	@Autowired
	private TradeAddressService tradeAddressService;

	@Autowired
	private TradeBaseService tradeBaseService;

	@ParamsValidate(value = { @ParamValidate(name = "userId", notNull = true, code = BizReturnCode.USERID_NOT_EXIST),
			@ParamValidate(name = "logisticsId", notNull = true, code = BizReturnCode.TRADE_ADDRESS_NOT_NUM, regex = "^[1-9]\\d*$"),
			@ParamValidate(name = "address", notNull = true, code = BizReturnCode.TRADE_LOGISTIC_EXIST),
			@ParamValidate(name = "telephone", notNull = true, code = BizReturnCode.TRADE_MOBILE_EXIST),
			@ParamValidate(name = "receiver", notNull = true, code = BizReturnCode.TRADE_RECEIVE_USER) })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, params = "apiVersion=1.0")
	public Message addAddress_V1(AddressParamVO addressParamVO) {

		logger.info("in trade/address/add addressParamVO: " + addressParamVO);
		if (!StringUtil.isBlank(addressParamVO.getCertificationCard())) {
			addressParamVO.setCertificationCard(addressParamVO.getCertificationCard().toUpperCase());
		}
		Message message = Message.success();
		String isSuccess = tradeAddressService.addAddress(addressParamVO);
		boolean result = String.valueOf(Boolean.FALSE).toUpperCase().equals(isSuccess);
		if (result) {// 添加地址失败返回错误码
			return Message.error(BizReturnCode.ADD_ADDRESS_FAIL);
		}
		message.addData("addressId", isSuccess);

		logger.info("out trade/address/add addressParamVO: " + addressParamVO);

		return message;
	}

	@ParamsValidate(value = { @ParamValidate(name = "userId", notNull = true, code = BizReturnCode.USERID_NOT_EXIST),
			@ParamValidate(name = "logisticsId", notNull = true, code = BizReturnCode.TRADE_ADDRESS_NOT_NUM, regex = "^[1-9]\\d*$"),
			@ParamValidate(name = "address", notNull = true, code = BizReturnCode.TRADE_LOGISTIC_EXIST),
			@ParamValidate(name = "telephone", notNull = true, code = BizReturnCode.TRADE_MOBILE_EXIST),
			@ParamValidate(name = "receiver", notNull = true, code = BizReturnCode.TRADE_RECEIVE_USER) })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, params = "apiVersion=1.1")
	public Message addAddress_V1_1(AddressParamVO addressParamVO) {

		logger.info("in trade/address/add addressParamVO: " + addressParamVO);
		if (!StringUtil.isBlank(addressParamVO.getCertificationCard())) {
			addressParamVO.setCertificationCard(addressParamVO.getCertificationCard().toUpperCase());
		}
		Message message = Message.success();
		String isSuccess = tradeAddressService.addAddress(addressParamVO);
		boolean result = String.valueOf(Boolean.FALSE).toUpperCase().equals(isSuccess);
		if (result) {// 添加地址失败返回错误码
			return Message.error(BizReturnCode.ADD_ADDRESS_FAIL);
		}
		message.addData("addressId", isSuccess);

		logger.info("out trade/address/add addressParamVO: " + addressParamVO);

		return message;
	}

	@ParamsValidate(value = { @ParamValidate(name = "userId", notNull = true, code = BizReturnCode.USERID_NOT_EXIST),
			@ParamValidate(name = "logisticsId", notNull = true, code = BizReturnCode.TRADE_ADDRESS_NOT_NUM, regex = "^[1-9]\\d*$"),
			@ParamValidate(name = "address", notNull = true, code = BizReturnCode.TRADE_LOGISTIC_EXIST),
			@ParamValidate(name = "telephone", notNull = true, code = BizReturnCode.TRADE_MOBILE_EXIST),
			@ParamValidate(name = "receiver", notNull = true, code = BizReturnCode.TRADE_RECEIVE_USER) })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, params = "apiVersion=1.2")
	public Message addAddress_V1_2(AddressParamVO addressParamVO) {
		Message message = Message.success();
		logger.info("in trade/address/add addressParamVO: " + addressParamVO);
		if (!StringUtil.isBlank(addressParamVO.getCertificationCard())) {
			addressParamVO.setCertificationCard(addressParamVO.getCertificationCard().toUpperCase());
		}
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

		String isSuccess = tradeAddressService.addAddress(addressParamVO);
		boolean result = String.valueOf(Boolean.FALSE).toUpperCase().equals(isSuccess);
		if (result) {// 添加地址失败返回错误码
			return Message.error(BizReturnCode.ADD_ADDRESS_FAIL);
		}
		message.addData("addressId", isSuccess);

		logger.info("out trade/address/add addressParamVO: " + addressParamVO);

		return message;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, params = "apiVersion=1.3")
	public Message addAddress_V1_3(AddressParamVO addressParamVO) {
		Message message = Message.success();
		logger.info("in trade/address/add addressParamVO: " + addressParamVO);
		if (!StringUtil.isBlank(addressParamVO.getCertificationCard())) {
			addressParamVO.setCertificationCard(addressParamVO.getCertificationCard().toUpperCase());
		}
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
		int isBlankAddress = tradeAddressService.hasBlankofAddress(addressParamVO);
		if(isBlankAddress!=200){
			return Message.error(isBlankAddress);
		}
		
		String isSuccess = tradeAddressService.addAddress_V1_3(addressParamVO);
		boolean result = String.valueOf(Boolean.FALSE).toUpperCase().equals(isSuccess);
		if (result) {// 添加地址失败返回错误码
			return Message.error(BizReturnCode.ADD_ADDRESS_FAIL);
		}
		message.addData("addressId", isSuccess);

		logger.info("out trade/address/add addressParamVO: " + addressParamVO);

		return message;
	}

}
