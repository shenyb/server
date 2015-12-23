package com.need.wap.web.controller.trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.fluent.Request;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.ControllerMappings;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.service.pay.PayService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.core.utils.WXPay;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.PaySignVO;
import com.need.trade.enums.TradeStatus;
import com.need.utils.HttpUtils;
import com.need.utils.StringUtil;

/**
 * <p>wap获取签名接口</p>
 * @author Rylan 2015年12月6日 上午11:17:18
 * @ClassName GetpayTokenController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年12月6日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
public class GetpayTokenController {
	
	private static final Logger logger = Logger.getLogger(GetpayTokenController.class);
	@Autowired
	private TradeBaseDAO tradeBaseDao;
	@Autowired
	private TradeBaseService tradeBaseService;

	@Autowired
	private GoodsMainDAO goodsMainDAO;
	
	@Autowired
	private PayService payService;
	
	/**
	 * @author Rylan 2015年12月6日 上午11:00:34
	 * @Method: getWECHARTPaySign 
	 * @Description: 公众号获取微信支付签名
	 * @param request
	 * @param response
	 * @param userId
	 * @param tradeNo
	 * @param couponNo
	 * @return
	 * @throws Exception 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value ="trade/WeChatPaySign", method = RequestMethod.POST)
	public Message getWECHARTPaySign(HttpServletRequest request, HttpServletResponse response, @RequestBody PaySignVO paySignVO) throws Exception {
		logger.info(String.format("WECHARTPaySign . params:userId:%s,tradeNo:%s", paySignVO.getUserId(), paySignVO.getTradeNo()));
		Message success = Message.success();
		List<TradeBasePO> trades = tradeBaseDao.getByTradeNo(paySignVO.getTradeNo());
		if(trades==null||trades.size()==0){
			return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
		}
		TradeStatus status = trades.get(0).getTradeStatus();
		if (status != TradeStatus.WAIT_PAY) {
			return Message.error(BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY);
		}
		// 实时获取商品总价
		int sum = trades.get(0).getPayPrice();
		StringBuffer subject = new StringBuffer();
		try {
			for (TradeBasePO trade : trades) {
				subject.append(goodsMainDAO.selectByPrimaryKey(trade.getGoodsId()).getGoodsName() + ",");
				tradeBaseDao.updateByOrderNo(trade);
			}
		} catch (Exception e) {
			logger.error("getWECHARTPaySign:" + "请求签名时获取商品名称异常");
		}
		WXPay wx = new WXPay();
		String title = subject.length() > 40 ? subject.substring(0, 40) : subject.toString();
		logger.info(String.format("#######wx.pay params:title:%s,sum:%s,tradeNo:%s,ip:%s", title, sum, paySignVO.getTradeNo(),HttpUtils.getRealIp(request)));
		Map<Object,Object>  resultMap= wx.payByJSAPI(title, sum, trades.get(0).getUserTradeNo(), HttpUtils.getRealIp(request),paySignVO.getOpenId(),"JSAPI");
		//Map<Object,Object>   resultMap= wx.payByJSAPI(title, sum, trades.get(0).getUserTradeNo(), "182.92.182.28",paySignVO.getOpenId(),"JSAPI");
		
		success.addData("appid", resultMap.get("appId").toString());
		success.addData("timestamp", resultMap.get("timeStamp").toString());
		success.addData("nonceStr", resultMap.get("nonceStr").toString());
		success.addData("package", resultMap.get("package").toString());
		success.addData("signType", "MD5");
		success.addData("paySign", resultMap.get("paySign").toString());
		
		logger.debug("getWECHARTPaySign will be return :"+success.getData());		
		// 检查库存
		Message goodsStoreMessage = tradeBaseService.checkGoodsStore(paySignVO.getTradeNo());
		if (goodsStoreMessage.getCode() != Message.SUCCESS) {
			return goodsStoreMessage;
		}		
		return success;
			
	}
	
	/**
	 * @author Rylan 2015年12月6日 上午11:07:59
	 * @Method: getPaySign_V1_2 
	 * @Description: 支付宝获取签名
	 * @param userId
	 * @param tradeNo
	 * @param couponNo
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "trade/AliPaySign", method = RequestMethod.POST)
	public Message getALiPayPaySign(@RequestBody PaySignVO paySignVO) {
		logger.info(String.format("getALiPayPaySignin . params:userId:%s,tradeNo:%s", paySignVO.getUserId(), paySignVO.getTradeNo()));
		return payService.saveAndGetAlipaySignForWap(Message.success(), paySignVO.getUserId(), paySignVO.getTradeNo(),null,paySignVO.getReturnUrl());
	}
	
	
	/**
	 * @author Rylan 2015年12月8日 下午8:34:32
	 * @Method: getPrepayId 
	 * @Description: 统一下单接口，返回
	 * @param request
	 * @param paySignVO
	 * @return 
	 * @throws
	 */
//	@ResponseBody
//	@RequestMapping(value = "trade/prepayId", method = RequestMethod.POST)
//	public Message getPrepayId(HttpServletRequest request,@RequestBody PaySignVO paySignVO) {
//		Message success= Message.success();
//		String openId=paySignVO.getOpenId();
//		if(StringUtils.isEmpty(openId)){
//			
//		}
//		success=tradeBaseService.wecharPaymentUnifiedOrder(openId, paySignVO.getTradeNo(), request.getRemoteAddr());
//		return success;
//	}
	
	
	/**
	 * @author Rylan 2015年12月11日 下午6:55:32
	 * @Method: getBusinessWECHARTPaySign 
	 * @Description: 微信商户版获取签名
	 * @param request
	 * @param response
	 * @param paySignVO
	 * @return
	 * @throws Exception 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value ="trade/businessWeChatPaySign", method = RequestMethod.POST)
	public Message getBusinessWECHARTPaySign(HttpServletRequest request, HttpServletResponse response, @RequestBody PaySignVO paySignVO) throws Exception {
		logger.info(String.format("getBusinessWECHARTPaySign . params:userId:%s,tradeNo:%s", paySignVO.getUserId(), paySignVO.getTradeNo()));
		Message success = Message.success();
		List<TradeBasePO> trades = tradeBaseDao.getByTradeNo(paySignVO.getTradeNo());
		if(trades==null||trades.size()==0){
			return Message.error(BizReturnCode.SYSTEM_OPERATE_ERR);
		}
		TradeStatus status = trades.get(0).getTradeStatus();
		if (status != TradeStatus.WAIT_PAY) {
			return Message.error(BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY);
		}
		// 实时获取商品总价
		int sum = trades.get(0).getPayPrice();
		StringBuffer subject = new StringBuffer();
		try {
			for (TradeBasePO trade : trades) {
				subject.append(goodsMainDAO.selectByPrimaryKey(trade.getGoodsId()).getGoodsName() + ",");
				tradeBaseDao.updateByOrderNo(trade);
			}
		} catch (Exception e) {
			logger.error("getWECHARTPaySign:" + "请求签名时获取商品名称异常");
		}
		WXPay wx = new WXPay();
		String title = subject.length() > 40 ? subject.substring(0, 40) : subject.toString();
		logger.info(String.format("#######wx.pay params:title:%s,sum:%s,tradeNo:%s,ip:%s", title, sum, paySignVO.getTradeNo(),HttpUtils.getRealIp(request)));
		
		Map<Object,Object>  resultMap= wx.payByJSAPI(title, sum, trades.get(0).getUserTradeNo(), HttpUtils.getRealIp(request),null,"WAP");
		//Map<Object,Object>   resultMap= wx.payByJSAPI(title, sum, trades.get(0).getUserTradeNo(), "182.92.182.28",null,"WAP");
				
		success.addData("appid", resultMap.get("appId").toString());
		success.addData("timestamp", resultMap.get("timeStamp").toString());
		success.addData("nonceStr", resultMap.get("nonceStr").toString());
		success.addData("package", resultMap.get("package").toString());
		success.addData("signType", "MD5");
		success.addData("paySign", resultMap.get("paySign").toString());
		
		logger.debug("getWECHARTPaySign will be return :"+success.getData());		
		// 检查库存
		Message goodsStoreMessage = tradeBaseService.checkGoodsStore(paySignVO.getTradeNo());
		if (goodsStoreMessage.getCode() != Message.SUCCESS) {
			return goodsStoreMessage;
		}		
		return success;
			
	}
	
	
	
}
