package com.need.api.web.controller.trade;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.pay.PayService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.core.utils.WXPay;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.pub.Message;
import com.need.trade.enums.TradeStatus;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年8月18日 下午3:41:18
 * @ClassName GetWeChatPayTokenController
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月18日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.GET_WECHAT_PAY_SIGN)
public class GetWeChatPayTokenController {
	private static final Logger logger = Logger.getLogger(GetWeChatPayTokenController.class);
	@Autowired
	private TradeBaseDAO tradeBaseDao;
	@Autowired
	private TradeBaseService tradeBaseService;

	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private CouponService couponService;
	@Autowired
	private PayService payService;

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getPaySign(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String tradeNo)
					throws Exception {
		return Message.error(BizReturnCode.TRADE_SYSTEM_VERSION_TOO_LOW);

		// Message success = Message.success();
		// List<TradeBasePO> trades = tradeBaseDao.getByTradeNo(tradeNo);
		// TradeStatus status = trades.get(0).getTradeStatus();
		// if (status != TradeStatus.WAIT_PAY) {
		// return Message.error(BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY);
		// }
		// // 实时获取商品总价
		// int sum = 0;
		// Message message = tradeBaseService.getTradeTotalPrice(tradeNo);
		// if (message.getCode() == Message.SUCCESS) {
		// sum =
		// Integer.valueOf(message.getData().get("totalPrice").toString());
		// } else {
		// return message;
		// }
		// StringBuffer subject = new StringBuffer();
		// try {
		// for (TradeBasePO trade : trades) {
		// subject.append(goodsMainDAO.selectByPrimaryKey(trade.getGoodsId()).getGoodsName()
		// + ",");
		// tradeBaseDao.updateByOrderNo(trade);
		// }
		// } catch (Exception e) {
		// logger.error("GetPayTokenController:" + "请求签名时获取商品名称异常");
		// }
		// WXPay wx = new WXPay();
		// String title = subject.length() > 40 ? subject.substring(0, 40) :
		// subject.toString();
		// logger.info(String.format("#######wx.pay
		// params:title:%s,sum:%s,tradeNo:%s,ip:%s", title, sum, tradeNo,
		// request,
		// request.getRemoteAddr()));
		// // title太长会导致参数格式不正确,大概27个字符，多余50个会签名不成功，大于27个会自动截取
		// String paySign = wx.pay(title, sum, tradeNo,
		// request.getRemoteAddr());
		// success.addData("sign", paySign);
		// logger.info(String.format("getPaySign:%s", paySign));
		// if (!StringUtil.isBlank(paySign)) {
		// // 修改价格
		// tradeBaseService.updatePriceByTradeNo(tradeNo, sum);
		// }
		// return success;
	}

	@ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getPaySign_V1_1(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String tradeNo,
			@RequestParam(required = false) String couponNo) throws Exception {
		return Message.error(BizReturnCode.TRADE_SYSTEM_VERSION_TOO_LOW);

	}

	// Message success = Message.success();
	// List<TradeBasePO> trades = tradeBaseDao.getByTradeNo(tradeNo);
	// TradeStatus status = trades.get(0).getTradeStatus();
	// if (status != TradeStatus.WAIT_PAY) {
	// return Message.error(BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY);
	// }
	// // 实时获取商品总价
	// int sum = 0;
	// Message message = tradeBaseService.getTradeTotalPrice(tradeNo);
	// if (message.getCode() == Message.SUCCESS) {
	// sum = Integer.valueOf(message.getData().get("totalPrice").toString());
	// } else {
	// return message;
	// }
	// // 如果有优惠券则减去优惠券面值
	// if (!StringUtil.isBlank(couponNo)) {
	// Message userCouponMessage = couponService.useCouponPrepay(couponNo,
	// tradeNo, userId);
	// if (userCouponMessage.getCode() != Message.SUCCESS) {
	// return userCouponMessage;
	// }
	// int decrease = (int) userCouponMessage.getData().get("value");
	// sum = sum - decrease;
	// }
	// StringBuffer subject = new StringBuffer();
	// try {
	// for (TradeBasePO trade : trades) {
	// subject.append(goodsMainDAO.selectByPrimaryKey(trade.getGoodsId()).getGoodsName()
	// + ",");
	// tradeBaseDao.updateByOrderNo(trade);
	// }
	// } catch (Exception e) {
	// logger.error("GetPayTokenController:" + "请求签名时获取商品名称异常");
	// }
	// WXPay wx = new WXPay();
	// String title = subject.length() > 40 ? subject.substring(0, 40) :
	// subject.toString();
	// logger.info(String.format("#######wx.pay
	// params:title:%s,sum:%s,tradeNo:%s,ip:%s", title, sum, tradeNo, request,
	// request.getRemoteAddr()));
	// // title太长会导致参数格式不正确,大概27个字符，多余50个会签名不成功，大于27个会自动截取
	// // String paySign = wx.pay(title, sum, tradeNo,
	// // request.getRemoteAddr());modify by liyonrgan 20150923 签名给微信14位
	// String paySign = wx.pay(title, sum, trades.get(0).getUserTradeNo(),
	// request.getRemoteAddr());
	// success.addData("sign", paySign);
	// if (!StringUtil.isBlank(paySign)) {
	// // 修改价格
	// tradeBaseService.updatePriceByTradeNo(tradeNo, sum);
	// }
	// logger.info(String.format("getPaySign:%s", paySign));
	// return success;
	// }
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.2", method = RequestMethod.GET)
	public Message getPaySign_V1_2(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String tradeNo,
			@RequestParam(required = false) String couponNo) throws Exception {
		Message success = Message.success();
		List<TradeBasePO> trades = tradeBaseDao.getByTradeNo(tradeNo);
		TradeStatus status = trades.get(0).getTradeStatus();
		if (status != TradeStatus.WAIT_PAY) {
			return Message.error(BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY);
		}
		// 检查余额
		TradeBasePO tradeBasePO = trades.get(0);
		Message checkMessage = payService.checkUserBalance(tradeBasePO);
		if(checkMessage.getCode()!=Message.SUCCESS){
			return checkMessage;
		}
		int sum = trades.get(0).getPayPrice();
		StringBuffer subject = new StringBuffer();
		try {
			for (TradeBasePO trade : trades) {
				subject.append(goodsMainDAO.selectByPrimaryKey(trade.getGoodsId()).getGoodsName() + ",");
				tradeBaseDao.updateByOrderNo(trade);
			}
		} catch (Exception e) {
			logger.error("GetPayTokenController:" + "请求签名时获取商品名称异常");
		}
		WXPay wx = new WXPay();
		String title = subject.length() > 40 ? subject.substring(0, 40) : subject.toString();
		logger.info(String.format("#######wx.pay params:title:%s,sum:%s,tradeNo:%s,ip:%s", title, sum, tradeNo,
				request.getRemoteAddr()));
		// title太长会导致参数格式不正确,大概27个字符，多余50个会签名不成功，大于27个会自动截取
		// String paySign = wx.pay(title, sum, tradeNo,
		// request.getRemoteAddr());modify by liyonrgan 20150923 签名给微信14位
		String paySign = wx.pay(title, sum, trades.get(0).getUserTradeNo(), request.getRemoteAddr());
		success.addData("sign", paySign);
		if (!StringUtil.isBlank(paySign)) {
			// 检查库存
			Message goodsStoreMessage = tradeBaseService.checkGoodsStore(tradeNo);
			if (goodsStoreMessage.getCode() != Message.SUCCESS) {
				return goodsStoreMessage;
			}
			// 修改单价和总价
			//tradeBaseService.updatePriceByTradeNo(tradeNo, sum);
		}
		logger.info(String.format("getPaySign:%s", paySign));
		return success;
	}

}
