package com.need.wap.web.controller.trade.address;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.ControllerMappings;
import com.need.common.core.service.trade.TradeAddressService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.AddressParamVO;
import com.need.common.domain.vo.trade.AddressResultVO;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;

/**
 * <p>
 * </p>
 * 
 * @author xiehao 2015年8月19日 下午4:00:43
 * @ClassName GetAddressListController
 * @Description TODO 获取用户的地址列表
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月19日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.TRADE_ADDRESS_LIST)
public class GetAddressListController {

	private static final Logger logger = Logger.getLogger(GetAddressListController.class);

	@Autowired
	private TradeAddressService tradeAddressService;

	@ParamsValidate(value = { @ParamValidate(name = "userId", notNull = true, code = BizReturnCode.USERID_NOT_EXIST) })
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getAddressList_V1_3(
			@RequestParam(required = true) String userId,
			@RequestParam(required = false) Integer pageNum, 
			@RequestParam(required = false) Integer pageSize) {// 用户ID是必须的
		logger.info("in trade/address/list userId: " + userId);

		Message message = Message.success();
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		PageHelper.startPage(pageNum, pageSize);// 用分页工具对结果进行分页
		Page<AddressResultVO> page = (Page<AddressResultVO>) tradeAddressService.queryAddressList_V1_3(userId);
		List<AddressResultVO> addressList = page.getResult();
		message.addData("addressList", addressList);
		message.addData("totalCount", page.getTotal());

		logger.info("out trade/address/list userId: " + userId);

		return message;
	}
}
