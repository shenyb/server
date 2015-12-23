package com.need.api.web.controller.trade.order;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.pub.ServiceException;
import com.need.common.core.service.cheap.CheapService;
import com.need.common.core.service.trade.IConfirmBuy;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.CreateCheapTradeParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 团便宜下单接口
 * <p>
 * </p>
 * 
 * @author shenyb 2015年10月24日 下午3:43:36
 * @ClassName CreateCheapTradeController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年10月24日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_CHEAP_CREATE)
public class CreateCheapTradeController {
	@Autowired
	private CheapService cheapService;
	@Resource
	private IConfirmBuy cheapConfirmBuy;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.3", method = RequestMethod.POST)
	public Message createTrade(@RequestParam(required = true) String userId,
			@RequestParam(required = true) int cheapOpenId, @RequestParam(required = true) String addressId) {
		Message message = cheapService.createTrade(cheapOpenId, userId, addressId);
		return message;
	}

	@ResponseBody
	@RequestMapping(params = "apiVersion=2.0", method = RequestMethod.POST)
	public Message createTradeV2_0(@RequestParam(required = true) String userId,
			@RequestParam(required = true) int cheapOpenId, @RequestParam(required = true) String addressId,
			@RequestParam(required = true) String useBalance) {
		CreateCheapTradeParamVO paramVO = transferToVO(userId, cheapOpenId, addressId, useBalance);
        try {
            return cheapConfirmBuy.createTrade(paramVO);
        } catch (ServiceException ex) {
            Logger.getLogger(CreateCheapTradeController.class.getName()).log(Level.SEVERE, null, ex);
            return Message.error(ex.getCode());
        }
	}

	/**
	 * 
	 * @author shenyb 2015年12月4日 下午3:16:58 @Method: transferToVO @Description:
	 * 参数转换 @param userId @param cheapOpenId @param addressId @param
	 * useBalance @return @throws
	 */
	private CreateCheapTradeParamVO transferToVO(String userId, int cheapOpenId, String addressId, String useBalance) {
		CreateCheapTradeParamVO paramVO = new CreateCheapTradeParamVO();
		paramVO.setAddressId(addressId);
		paramVO.setUseBalance(useBalance);
		paramVO.setUserId(userId);
		paramVO.setCheapOpenId(cheapOpenId);
		return paramVO;
	}

}
