package com.need.common.core.service.pay.impl;

import com.alibaba.fastjson.JSON;
import com.need.biz.utils.BizCodeUtil;
import com.need.biz.utils.CurrencyUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.distribution.UserCommissionAccountDAO;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.dao.jdbc.trade.TradePayDAO;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.pay.PayService;
import com.need.common.core.service.trade.TradeBaseService;
import com.need.common.core.thirdpartypay.alipay.config.AlipayConfig;
import com.need.common.core.thirdpartypay.alipay.sign.RSA;
import com.need.common.core.thirdpartypay.alipay.util.AlipayCore;
import com.need.common.domain.enums.PayChannelEnum;
import com.need.common.domain.enums.PayTypeEnum;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.po.api.trade.TradePayPO;
import com.need.common.domain.pub.Message;
import com.need.trade.enums.TradeStatus;
import com.need.utils.PropertiesUtil;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author david.tan
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private TradeBaseService tradeBaseService;
    @Autowired
    private TradeBaseDAO tradeBaseDAO;
    @Autowired
    private GoodsMainDAO goodsMainDAO;
    @Autowired
    private TradePayDAO tradePayDAO;
    @Autowired
    private CouponService couponService;
    @Autowired
    private UserCommissionAccountDAO userCommissionAccountDAO;
    private Logger logger = Logger.getLogger(PayServiceImpl.class);

    @Override
    @Transactional
    public Message savePayRecord(String tradeNo, String outPayNo, int payAmount, PayChannelEnum payChannel,
                                 PayTypeEnum payType, String memo) {
        TradePayPO tradePay = new TradePayPO();
        tradePay.setPayId(BizCodeUtil.generatePayId(tradeNo, outPayNo));
        tradePay.setTradeNo(tradeNo);
        tradePay.setOutPayNo(outPayNo);
        tradePay.setAmount(payAmount);
        tradePay.setPayChannel(payChannel.code);
        tradePay.setPayType(PayTypeEnum.TRADE_PAY.code);
        tradePay.setMemo(memo);
        int result = tradePayDAO.insert(tradePay);
        return result > 0 ? Message.success() : Message.error(BizReturnCode.TRADE_PAY_ADD_ERR);
    }

    @Override
    public Message getAlipaySign(Message success, String userId, String tradeNo) {
        List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
        if (trades == null || trades.size() < 0) {
            return Message.error(BizReturnCode.TRADE_NOT_EXIST);
        }
        TradeStatus status = trades.get(0).getTradeStatus();
        if (status != TradeStatus.WAIT_PAY) {
            return Message.error(BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY);
        }
        // 实时获取商品总价
        Object sum = 0;
        Message message = tradeBaseService.getTradeTotalPrice(tradeNo);
        if (message.getCode() == Message.SUCCESS) {
            sum = message.getData().get("totalPrice");
        }
        StringBuilder subject = new StringBuilder();
        try {
            for (TradeBasePO trade : trades) {
                subject.append(goodsMainDAO.selectByPrimaryKey(trade.getGoodsId()).getGoodsName() + ",");
            }
        } catch (Exception e) {
        }

        String partner = PropertiesUtil.getProperty("/properties/alipay.properties", "partner");
        String notifyUrl = PropertiesUtil.getProperty("/properties/alipay.properties", "notify_url");
        String privateKey = PropertiesUtil.getProperty("/properties/alipay.properties", "private_key");
        String sellerId = PropertiesUtil.getProperty("/properties/alipay.properties", "seller_email");
        String totalFee = CurrencyUtil.fromFenToYuan(sum + "");
        String paymentType = "1";
        Map<String, String> param = new HashMap<String, String>();
        param.put("partner", partner);
        param.put("seller_id", sellerId);
        param.put("out_trade_no", tradeNo);
        param.put("subject", subject.length() > 0 ? subject.substring(0, subject.length() - 1) : "noTitle");
        param.put("total_fee", totalFee);
        param.put("notify_url", notifyUrl);
        param.put("service", "mobile.securitypay.pay");
        param.put("payment_type", paymentType);
        param.put("_input_charset", AlipayConfig.input_charset);
        param.put("it_b_pay", "30m");
        param.put("show_url", "m.alipay.com");
        String content = AlipayCore.createLinkString(param);
        String rsaSign = RSA.sign(content, privateKey, AlipayConfig.input_charset);
        success.addData("content", content);
        success.addData("sign", rsaSign);
        return success;
    }

    @Override
    public Message getAlipaySignV1_1(Message success, String userId, String tradeNo, String couponNo) {
        List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
        if (trades == null || trades.size() < 0) {
            return Message.error(BizReturnCode.TRADE_NOT_EXIST);
        }
        TradeBasePO tradeOne = trades.get(0);
        if (tradeOne == null || StringUtil.isBlank(tradeOne.getAddressId())) {
            return Message.error(BizReturnCode.TRADE_CERTIFICATION_FAIL);
        }
        TradeStatus status = trades.get(0).getTradeStatus();
        if (status != TradeStatus.WAIT_PAY) {
            return Message.error(BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY);
        }
        // 实时获取商品总价
        int sum = 0;
        Message message = tradeBaseService.getTradeTotalPrice(tradeNo);
        if (message.getCode() == Message.SUCCESS) {
            sum = (int) message.getData().get("totalPrice");
        }
        // 如果有优惠券则减去优惠券面值
        if (!StringUtil.isBlank(couponNo)) {
            Message userCouponMessage = couponService.useCouponPrepay(couponNo, tradeNo, userId);
            if (userCouponMessage.getCode() != Message.SUCCESS) {
                return userCouponMessage;
            }
            int decrease = (int) userCouponMessage.getData().get("value");
            sum = sum - decrease;
        }
        StringBuilder subject = new StringBuilder();
        try {
            for (TradeBasePO trade : trades) {
                subject.append(goodsMainDAO.selectByPrimaryKey(trade.getGoodsId()).getGoodsName() + ",");
            }
        } catch (Exception e) {
        }

        String partner = PropertiesUtil.getProperty("/properties/alipay.properties", "partner");
        String notifyUrl = PropertiesUtil.getProperty("/properties/alipay.properties", "notify_url");
        String privateKey = PropertiesUtil.getProperty("/properties/alipay.properties", "private_key");
        String sellerId = PropertiesUtil.getProperty("/properties/alipay.properties", "seller_email");
        String totalFee = CurrencyUtil.fromFenToYuan(sum + "");
        String paymentType = "1";
        Map<String, String> param = new HashMap<String, String>();
        param.put("partner", partner);
        param.put("seller_id", sellerId);
        // param.put("out_trade_no", tradeNo);//modify by liyonrgan 20150923
        // 签名给支付宝14位
        param.put("out_trade_no", trades.get(0).getUserTradeNo());
        String title = subject.length() > 30 ? subject.substring(0, 30) : subject.toString();
        param.put("subject", StringUtil.isBlank(title) ? "noTitle" : title);
        param.put("total_fee", totalFee);
        param.put("notify_url", notifyUrl);
        param.put("service", "mobile.securitypay.pay");
        param.put("payment_type", paymentType);
        param.put("_input_charset", AlipayConfig.input_charset);
        param.put("it_b_pay", "30m");
        param.put("show_url", "m.alipay.com");
        String content = AlipayCore.createLinkString(param);
        String rsaSign = RSA.sign(content, privateKey, AlipayConfig.input_charset);

        System.out.println("content :" + content);
        System.out.println("rsaSign :" + rsaSign);
        success.addData("content", content);
        success.addData("sign", rsaSign);
        if (StringUtil.isBlank(rsaSign)) {
            logger.error(String.format("paysign error param:%s,privateKey:%s,content:%s", JSON.toJSONString(param),
                    privateKey, content));
        } else {
            // 签名成功后,修改交易表购买单价，总价，支付价格
            tradeBaseService.updatePriceByTradeNo(tradeNo, sum);
        }

        return success;
    }

    @Override
    @Transactional
    public Message saveAndGetAlipaySignV1_2(Message success, String userId, String tradeNo, String couponNo) {
        List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
        if (trades == null || trades.size() < 0) {
            return Message.error(BizReturnCode.TRADE_NOT_EXIST);
        }
        TradeStatus status = trades.get(0).getTradeStatus();
        if (status != TradeStatus.WAIT_PAY) {
            return Message.error(BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY);
        }
        // 检查余额
        TradeBasePO tradeBasePO = trades.get(0);
        Message checkMessage = checkUserBalance(tradeBasePO);
        if (checkMessage.getCode() != Message.SUCCESS) {
            return checkMessage;
        }
        int sum = tradeBasePO.getPayPrice();
        StringBuilder subject = new StringBuilder();
        try {
            for (TradeBasePO trade : trades) {
                subject.append(goodsMainDAO.selectByPrimaryKey(trade.getGoodsId()).getGoodsName() + ",");
            }
        } catch (Exception e) {
        }

        String partner = PropertiesUtil.getProperty("/properties/alipay.properties", "partner");
        String notifyUrl = PropertiesUtil.getProperty("/properties/alipay.properties", "notify_url");
        String privateKey = PropertiesUtil.getProperty("/properties/alipay.properties", "private_key");
        String sellerId = PropertiesUtil.getProperty("/properties/alipay.properties", "seller_email");
        String totalFee = CurrencyUtil.fromFenToYuan(sum + "");
        String paymentType = "1";
        Map<String, String> param = new HashMap<String, String>();
        param.put("partner", partner);
        param.put("seller_id", sellerId);
        // param.put("out_trade_no", tradeNo);//modify by liyonrgan 20150923
        // 签名给支付宝14位
        param.put("out_trade_no", trades.get(0).getUserTradeNo());
        String title = subject.length() > 30 ? subject.substring(0, 30) : subject.toString();
        param.put("subject", StringUtil.isBlank(title) ? "noTitle" : title);
        param.put("total_fee", totalFee);
        param.put("notify_url", notifyUrl);
        param.put("service", "mobile.securitypay.pay");
        param.put("payment_type", paymentType);
        param.put("_input_charset", AlipayConfig.input_charset);
        param.put("it_b_pay", "30m");
        param.put("show_url", "m.alipay.com");
        // 实名认证标示
        param.put("rn_check", "T");
        String content = AlipayCore.createLinkString(param);
        String rsaSign = RSA.sign(content, privateKey, AlipayConfig.input_charset);
        success.addData("content", content);
        success.addData("sign", rsaSign);
        if (StringUtil.isBlank(rsaSign)) {
            logger.error(String.format("paysign error param:%s,privateKey:%s,content:%s", JSON.toJSONString(param),
                    privateKey, content));
        } else {
            // 签名成功后,修改交易表购买单价，总价，支付价格
            tradeBaseService.updatePriceByTradeNo(tradeNo, sum);
        }

        return success;
    }

    /**
     * 检查余额账户金额
     *
     * @param trade
     * @return
     */
    @Override
    public Message checkUserBalance(TradeBasePO trade) {
        if (trade == null) {
            return Message.error(BizReturnCode.TRADE_NOT_EXIST);
        }
        Integer account = userCommissionAccountDAO.getAvailableByUserId(trade
                .getBuyerId());
        if (trade.getCommission() > 0 && account == null && account < trade
                .getCommission()) {
            return Message.error(BizReturnCode.TRADE_COMMISSION_BALANCE_NOT_ENOUGH);
        }
        return Message.success();
    }


    @Override
    @Transactional
    public Message saveAndGetAlipaySignForWap(Message success, String userId, String tradeNo, String couponNo, String returnUrl) {
        List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
        if (trades == null || trades.size() < 0) {
            return Message.error(BizReturnCode.TRADE_NOT_EXIST);
        }
        TradeStatus status = trades.get(0).getTradeStatus();
        if (status != TradeStatus.WAIT_PAY) {
            return Message.error(BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY);
        }
        // 检查余额
        TradeBasePO tradeBasePO = trades.get(0);
        Message checkMessage = checkUserBalance(tradeBasePO);
        if (checkMessage.getCode() != Message.SUCCESS) {
            return checkMessage;
        }
        int sum = trades.get(0).getPayPrice();
        StringBuilder subject = new StringBuilder();
        try {
            for (TradeBasePO trade : trades) {
                subject.append(goodsMainDAO.selectByPrimaryKey(trade.getGoodsId()).getGoodsName() + ",");
            }
        } catch (Exception e) {
        }

        String partner = PropertiesUtil.getProperty("/properties/alipay.properties", "partner");
        String notifyUrl = PropertiesUtil.getProperty("/properties/alipay.properties", "notify_url");
        String privateKey = PropertiesUtil.getProperty("/properties/alipay.properties", "private_key");
        String seller_email = PropertiesUtil.getProperty("/properties/alipay.properties", "seller_email");
        String totalFee = CurrencyUtil.fromFenToYuan(sum + "");
        String paymentType = "1";
        SortedMap<String, String> param = new TreeMap<String, String>();

        param.put("service", "alipay.wap.create.direct.pay.by.user");//手机端支付
        param.put("partner", partner);
        param.put("_input_charset", AlipayConfig.input_charset);
        param.put("payment_type", paymentType);
        param.put("notify_url", notifyUrl);
        param.put("return_url", returnUrl);
        param.put("seller_id", seller_email);
        param.put("out_trade_no", trades.get(0).getUserTradeNo());
        String title = subject.length() > 30 ? subject.substring(0, 30) : subject.toString();
        param.put("subject", StringUtil.isBlank(title) ? "noTitle" : title);
        param.put("total_fee", totalFee);
        // 实名认证标示
        param.put("rn_check", "T");
        String content = AlipayCore.createLinkStringOrigin(param);
        String rsaSign = RSA.sign(content, privateKey, AlipayConfig.input_charset);
        //success.addData("content",content+rsaSign+"sign_type=RSA");
        logger.debug("saveAndGetAlipaySignForWap .content :" + content + " sign: " + rsaSign + " sign_type " + "sign_type=RSA");

        if (StringUtil.isBlank(rsaSign)) {
            logger.error(String.format("paysign error param:%s,privateKey:%s,content:%s", JSON.toJSONString(param), privateKey, content));
        } else {
            success.addData("service", "alipay.wap.create.direct.pay.by.user");
            success.addData("partner", partner);
            success.addData("_input_charset", AlipayConfig.input_charset);
            success.addData("payment_type", paymentType);
            success.addData("notify_url", notifyUrl);
            success.addData("return_url", returnUrl);
            success.addData("seller_id", seller_email);
            success.addData("out_trade_no", trades.get(0).getUserTradeNo());
            success.addData("subject", StringUtil.isBlank(title) ? "noTitle" : title);
            success.addData("total_fee", totalFee);
            success.addData("rn_check", "T");
            success.addData("sign_type", "RSA");
            success.addData("sign", rsaSign);

            // 签名成功后,修改交易表购买单价，总价，支付价格
            tradeBaseService.updatePriceByTradeNo(tradeNo, sum);
        }

        return success;
    }

}
