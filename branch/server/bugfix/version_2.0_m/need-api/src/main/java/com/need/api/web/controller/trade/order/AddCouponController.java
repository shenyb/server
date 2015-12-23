package com.need.api.web.controller.trade.order;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.domain.pub.Message;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>订单绑定优惠券信息</p>
 * @author Rylan 2015年10月3日 下午2:55:03
 * @ClassName AddCouponController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年10月3日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.TRADE_ORDER_ADD_COUPON)
public class AddCouponController {
	
	private static final Logger logger = Logger.getLogger(AddCouponController.class);
	
	@Autowired
	private CouponService couponService;
	
	@ParamsValidate(value={
			@ParamValidate(name="tradeNo",notNull=true,code=BizReturnCode.REQUEST_PARAMS_NOT_NULL),
			@ParamValidate(name="couponNo",notNull=true,code=BizReturnCode.REQUEST_PARAMS_NOT_NULL)
			})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.POST)
	public Message addCoupon_V1_1(String userId,String tradeNo,String couponNo) {
	    logger.info("addCoupon_V1_1 in .tradeNo :"+tradeNo+"  couponNo :"+couponNo);
	    //订单绑定优惠券信息
        Message userCouponMessage = couponService.useCouponPrepay(couponNo, tradeNo, userId);
		return userCouponMessage;
	}
	
}
