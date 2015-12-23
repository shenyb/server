package com.need.api.web.controller.trade;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.trade.TradeAddressDAO;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.trade.TradeAddressPO;
import com.need.common.domain.pub.Message;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 实名认证接口
 * <p>
 * </p>
 * 
 * @author shenyb 2015年10月26日 下午4:38:41
 * @ClassName CertificateCardIdControlller
 * @Description 从购物车页面进入到填写订单页面对身份验证有三种情况的提示 a 默认地址中身份证为空，提示＝身份证为空，请填写身份证信息 b
 *              身份认证信息错误，提示＝身份认证疑似错误，请重新填写 c
 *              从购物车进入到当前页面时，如果购买的是海外直邮商品，需提供身份证照片，如果为空，提示＝身份证照片为空，请提交 d
 *              如果购买的是海外直邮商品，身份证和照片为空，提示＝请补全身份证及正反面照片
 * 
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年10月26日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CERTIFICATE)
public class CertificateCardIdControlller {
	private static final Logger logger = Logger.getLogger(CertificateCardIdControlller.class);
	@Autowired
	private TradeBaseService tradeBaseService;
	@Autowired
	private TradeAddressDAO tradeAddressDAO;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.POST)
	public Message certificateCardId(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String warehouseType,
			@RequestParam(required = true) String certificationChannel,
			@RequestParam(required = true) String addressId) {
		Message message = Message.success();

		TradeAddressPO address = tradeAddressDAO.selectByPrimaryKey(addressId);
		if (address == null) {
			return Message.error(BizReturnCode.ADDRESS_NOT_EXIST);
		}
		String realName = address.getReceiver();
		String idCard = address.getCertificationCard();
		String positiveKey = address.getCertificationPositiveKey();
		String negativeKey = address.getCertificationNegativeKey();
		if (StringUtil.isBlank(idCard)) {
			return Message.error(BizReturnCode.TRADE_IDCARD_NOT_EXISTS);
		}
		if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)) {
			if (StringUtil.isBlank(positiveKey) || StringUtil.isBlank(negativeKey)) {
				return Message.error(BizReturnCode.TRADE_IDCARD_PHOTO_NOT_EXISTS);
			}
		}
		Message result = tradeBaseService.certificationCardVerify(realName, idCard, userId, certificationChannel);
		if (result.getCode() != Message.SUCCESS) {
			switch (result.getCode()) {
			case BizReturnCode.TRADE_CERTIFICATION_FAIL:
				message = Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);
				break;
			case BizReturnCode.TRADE_CERTIFICATION_TIMEOUT:
				message = Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);
				break;
			default:
				break;
			}
		}
		return message;
	}
}
