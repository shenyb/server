package com.need.common.core.service.trade.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.distribution.DistributionGoodsDAO;
import com.need.common.core.dao.jdbc.distribution.UserCommissionDAO;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.RetrieveStatusRecordDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.dao.jdbc.trade.TradeJudgementDAO;
import com.need.common.core.dao.jdbc.trade.TradePayDAO;
import com.need.common.core.service.coupon.CouponService;
import com.need.common.core.service.distribution.UserCommissionAccountService;
import com.need.common.core.service.pay.PayService;
import com.need.common.core.service.trade.TradeManager;
import com.need.common.core.thirdpartypay.alipay.sign.MD5;
import com.need.common.core.thirdpartypay.alipay.util.AlipayCore;
import com.need.common.core.thirdpartypay.alipay.util.AlipayNotify;
import com.need.common.core.thirdpartypay.alipay.util.AlipaySubmit;
import com.need.common.core.thirdpartypay.alipay.util.UtilDate;
import com.need.common.core.utils.WXPay;
import com.need.common.core.utils.WXPay.Order;
import com.need.common.domain.enums.*;
import com.need.common.domain.po.api.coupon.CouponUserPO;
import com.need.common.domain.po.api.distribution.DistributionGoodsPO;
import com.need.common.domain.po.api.distribution.UserCommissionPO;
import com.need.common.domain.po.api.goods.GoodsLockCountTempPO;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.po.api.trade.TradeJudgementPO;
import com.need.common.domain.po.api.trade.TradePayPO;
import com.need.common.domain.po.api.trade.TradeRetrieveStatusRecord;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.OrderBaseVO;
import com.need.common.domain.vo.trade.PayNotifyVO;
import com.need.common.domain.vo.trade.RefundNotifyVO;
import com.need.common.domain.vo.trade.TradeListVO;
import com.need.trade.TradeStatusProcess;
import com.need.trade.enums.TradeStatus;
import com.need.utils.PropertiesUtil;
import com.need.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * </p>
 *
 * @author david.tan 2015年8月7日 下午7:13:26
 * @version V1.0
 * @ClassName TradeManagerImpl
 * @Description TODO
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class TradeManagerImpl implements TradeManager {
    @Autowired
    private TradeBaseDAO tradeBaseDAO;
    @Autowired
    private TradePayDAO tradePayDAO;
    @Autowired
    private GoodsMainDAO goodsMainDAO;
    @Autowired
    private TradeJudgementDAO tradeJudgementDAO;
    @Autowired
    private PayService payService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private RetrieveStatusRecordDAO retrieveStatusRecordDAO;
    @Autowired
    private UserCommissionDAO userCommissionDAO;
    @Autowired
    private DistributionGoodsDAO distributionGoodsDAO;
    @Autowired
    private UserCommissionAccountService userCommissionAccountService;
//    @Autowired
//    private GoodsLockCountTempDAO goodsLockCountTempDAO;

    private static final Logger logger = Logger.getLogger(TradeManagerImpl.class);

    @Override
    @Transactional
    public String paySuccess(String tradeNo, PayNotifyVO vo) {
        logger.info(String.format("tradeNo:%s,PayNotifyVO:%s",tradeNo,JSON.toJSONString(vo)));
        List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
        if (trades == null || trades.size() == 0) {
            // 系统无此订单，则不需要第三方重复回调
            return Constants.NOTIFY_FAIL;
        }
        tradeNo = trades.get(0).getTradeNo();// modify by liyongran 重新赋值tradeNo
        // 判断是否重复支付
        TradeStatus currentTradeStatus = trades.get(0).getTradeStatus();
        if (currentTradeStatus != TradeStatus.WAIT_PAY) {
            logger.info("##################:repeat pay !!! tradeNo:" + tradeNo + "current tradestatus:"
                    + currentTradeStatus.code + ",payChannel:" + vo.getPayChannel());
            return Constants.NOTIFY_SUCCESS;
        }
        for (TradeBasePO trade : trades) {
            TradeStatus processTradeStatus = TradeStatusProcess.tradeProcess(currentTradeStatus);
            TradeStatus tradeStatus = trade.getTradeStatus();
            // 1交易主表状态变更
            if (TradeStatus.WAIT_PAY == tradeStatus) {
                trade.setTradeStatus(processTradeStatus);
                trade.setOrderStatus(processTradeStatus);
                trade.setPayTime(Calendar.getInstance().getTime());
                trade.setTradeTime(Calendar.getInstance().getTime());
                trade.setPayChannel(vo.getPayChannel().code);
                tradeBaseDAO.updateByOrderNo(trade);
            }
            // 2 锁库，库存减少，锁定数添加
            String goodsId = trade.getGoodsId();
            GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(goodsId);
            int storeCount = goods.getStoreCount();
            int buyCount = trade.getBuyCount();
            if (storeCount < buyCount) {
                /**
                 * 判断库存是否充足,需要转退款或者补货 TODO
                 */
                tradeBaseDAO.updateISNormalByTradeNo(tradeNo, StoreStatusEnums.OUT_STORE.code);
                logger.error("tradeNo:" + tradeNo + ",goodsId" + goodsId + ",storeCount is not enough");
                logger.error("支付宝交易成功后在等待支付宝返回的时候库存不足,已把交易改为缺货。。。");

                // 判断库存是否充足,需要转退款或者补货
                logger.error("tradeNo:" + tradeNo + ",goodsId" + goodsId + ",storeCount is not enough");
            } else {
                goodsMainDAO.updateStoreCountAndLockCountByGoodsId(goodsId, buyCount);
                // 添加临时库存表
              //  addToGoodsLockCountTemp(trade);
            }
        }
        // 3交易支付流水表
        Message result = payService.savePayRecord(tradeNo, vo.getOutTradeNo(), vo.getTotalFee(), vo.getPayChannel(),
                PayTypeEnum.TRADE_PAY, vo.getMemo());
        if (result.getCode() != Message.SUCCESS) {
            logger.error("trade pay insert error:" + result.getMsg());
        }
        // 4所有支付完成后，如果有使用优惠券，则生成优惠券给分享者
        couponService.useCouponPayedSuccess(tradeNo);
        //5 处理分销
        //5.1如果使用了佣金，扣除用户佣金账户 5.2如果是分销下单则生成佣金给分享者
        logger.info(String.format("distribution info before: tradeNo:%s",
                tradeNo));
        dealDistributionAccount(trades);
        logger.info(String.format("distribution info after: tradeNo:%s",
               tradeNo));
        // 6物流信息，记录已经受理
        recordLogisticsTracing(trades.get(0));
        logger.info("tradeNo notify success:" + tradeNo);
        return Constants.NOTIFY_SUCCESS;
    }

//    private void addToGoodsLockCountTemp(TradeBasePO trade) {
//        GoodsLockCountTempPO goodsLockTemp = new GoodsLockCountTempPO();
//        goodsLockTemp.setBuyCount(trade.getBuyCount());
//        goodsLockTemp.setGoodsId(trade.getGoodsId());
//        goodsLockTemp.setOrderNo(trade.getOrderNo());
//        goodsLockTemp.setSynchFlag(Constants.FALSE);
//        goodsLockCountTempDAO.insert(goodsLockTemp);
//        logger.info(String.format("addToGoodsLockCountTemp:%s,trade:%s",JSON
//                .toJSONString
//                (goodsLockTemp),JSON.toJSON(trade)));
//    }

    /**
     * @author shenyb 2015年12月4日 下午2:03:44 @Method:
     * recordLogisticsTracing @Description: 记录物流信息 @param trade @throws
     */

    protected void recordLogisticsTracing(TradeBasePO trade) {
        if (trade != null) {
            TradeRetrieveStatusRecord record = new TradeRetrieveStatusRecord();
            record.setOrderNo(trade.getUserOrderNo());
            record.setTradeNo(trade.getUserTradeNo());
            record.setTrackingCode(TrackingCodeEnum.RECEIVED.code);
            record.setTrackingDesc(TrackingCodeEnum.RECEIVED.userDesc);
            List<TradeRetrieveStatusRecord> retrieveRecord = retrieveStatusRecordDAO
                    .getByRetrieveStatusAndTradeNo(record.getTrackingCode(), record.getTradeNo());
            if (retrieveRecord == null || retrieveRecord.size() == 0) {
                logger.info("before insert record:userTradeNo" + record.getTradeNo() + ":" + JSON.toJSONString(record));
                retrieveStatusRecordDAO.insert(record);
            }
        }
    }

    /**
     * @author shenyb 2015年12月4日 下午1:52:34 @Method:
     * dealDistributionAccount @Description:
     * 支付成功后，减去用户账户佣金，分享佣金给分享者
     * userTradeNo @throws
     */

    protected void dealDistributionAccount(List<TradeBasePO> trades) {
        logger.info(String.format("distribution info in: trades:%s,",JSON.toJSONString(trades)));
        //1扣除用户余额,放到下单成功
        //2分享者获取佣金
        TradeBasePO trade = trades.get(0);
        if (OrderTypeEnum.DISTRIBUTION.code.equals(trade.getOrderType())) {
            String tradeNo = trade.getTradeNo();
            String userTradeNo = trade.getUserTradeNo();
            String shareId = trade.getDistributionShareId();
            UserCommissionPO userCommissionPO = new UserCommissionPO();
            userCommissionPO.setUserId(shareId);
            userCommissionPO.setUserTradeNo(userTradeNo);
            userCommissionPO.setTradeNo(tradeNo);
            userCommissionPO.setPayPrice(trade.getPayPrice());
            userCommissionPO.setGoodsId(trade.getGoodsId());
            GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(trade.getGoodsId());
            userCommissionPO.setCommissionStatus(UserCommissionStatusEnum.WAIT_TO_IN.status);
            userCommissionPO.setGoodsName(goods.getGoodsName());
            userCommissionPO.setTopPicKey(StringUtil.stringToArrayBySplit(goods.getTopPicKeys())[0]);
            DistributionGoodsPO distributionGoodsPO = distributionGoodsDAO.getByGoodsIdAndDistributionStatus(
                    trade.getGoodsId(), DistributionStatusEnums.ALLOW_USE.code);
            logger.info(String.format("distribution info: goodsId:%s,userTradeNo:%s,distributionGoodsPO:%s",
                    trade.getGoodsId(), userTradeNo, JSON.toJSONString(distributionGoodsPO)));
            if (distributionGoodsPO != null &&
                    distributionGoodsPO.getCommission() > 0) {
                int commission = distributionGoodsPO.getCommission();
                userCommissionPO.setCommission(commission);
                // 5.2则生成佣金给分享者
                int insertResult = userCommissionDAO.insert(userCommissionPO);
                if (insertResult>0) {
                    logger.info(String.format("buyerId:%s,increasePrice:%s,success", trade.getBuyerId(), commission));
                } else {
                    logger.info(String.format("buyerId:%s,increasePrice:%s,error", trade.getBuyerId(), commission));
                }
            }
        }
    }

    /**
     * @param tradeNo
     * @param userId
     * @return
     * @author shenyb 2015年8月10日 下午3:17:56
     * @Method: cancel
     * @Description: 取消订单
     * @see
     */
    @Override
    @Transactional
    public int cancel(String tradeNo, String userId) {
        List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
        TradeStatus tradeStatus = TradeStatus.WAIT_PAY;
        TradeStatus processTradeStatus = TradeStatusProcess.cancelTradeProcess(tradeStatus);
        if (trades != null && trades.size() > 0) {
            int result = BizReturnCode.SUCCESS;
            for (TradeBasePO trade : trades) {
                if (trade.getTradeStatus() == tradeStatus) {
                    tradeBaseDAO.updateTradeStatusByTradeNo(tradeNo, processTradeStatus.code, processTradeStatus.code);
                } else {
                    result = BizReturnCode.TRADE_STATUS_NOT_WAIT_PAY;
                    break;
                }
            }
            if (result == BizReturnCode.SUCCESS) {
                couponService.useCouponPayedFail(tradeNo);
                userCommissionAccountService.returnCommissionToUserAccount(trades, UserCommissionAccountOperateTypeEnum.TRADE_CANCEL_SUCCESS);
            }
        } else {
            return BizReturnCode.TRADE_NOT_EXIST;
        }
        return Message.SUCCESS;
    }

    /**
     * @param tradeNo
     * @return
     * @author shenyb 2015年8月10日 下午3:18:13
     * @Method: confirm
     * @Description: 确认订单
     */
    @Override
    @Transactional
    public int confirm(String tradeNo) {
        List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
        TradeStatus currentTradeStatus = TradeStatus.WAIT_RECEIVE;
        TradeStatus processTradeStatus = TradeStatusProcess.tradeProcess(currentTradeStatus);

        if (trades == null || trades.size() == 0) {
            return BizReturnCode.TRADE_NOT_EXIST;
        }

        for (TradeBasePO trade : trades) {
            if (trade.getTradeStatus().code.equals(currentTradeStatus.code)) {
                trade.setOrderStatus(processTradeStatus);
                trade.setTradeStatus(processTradeStatus);
                tradeBaseDAO.updateByOrderNo(trade);
            } else {
                return BizReturnCode.TRADE_STATUS_NOT_WAIT_RECEIVE;
            }
        }

        return Message.SUCCESS;
    }

    @Override
    public List<TradeListVO> getUserOrderlist(String buyerId, Integer pageNum, Integer pageSize, String orderStatus,
                                              Message m) {
        /** TODO Auto-generated method stub */
        Page<TradeBasePO> pags = null;
        // 区分状态 modify by liyongran 20150918
        // if (orderStatus != null &&
        // orderStatus.equals(TradeStatus.WAIT_PLATFORM_SEND.code)) { //
        // WAIT_PLATFORM_SEND
        // // 返回2种状态信息
        // pags = tradeBaseDAO.queryTradeSendAndReceiveBybuyerId(buyerId);
        // } else {
        // pags = tradeBaseDAO.queryBaseTradeBybuyerId(buyerId, orderStatus);
        // }
        pags = tradeBaseDAO.queryBaseTradeBybuyerId(buyerId, orderStatus);
        List<TradeBasePO> baseList = pags.getResult();
        if (baseList == null) {
            return null;
        }
        m.addData("totalCount", pags.getTotal());
        List<TradeListVO> tradeList = new ArrayList<TradeListVO>();

        for (TradeBasePO tradeBasePO : baseList) {
            // 基本交易
            TradeListVO trade = new TradeListVO();
            trade.setTradeNo(tradeBasePO.getTradeNo());
            trade.setUserTradeNo(tradeBasePO.getUserTradeNo());
            trade.setTradeStatus(tradeBasePO.getTradeStatus());
            trade.setTotalPrice(tradeBasePO.getTotalPrice());
            trade.setTradeTime(tradeBasePO.getCreateTime().getTime());
            List<OrderBaseVO> orderList = tradeBaseDAO.queryOrderBaseByTradeNo(trade.getTradeNo());// 获取交易下的订单信息
            // Addy liyongran 20150920 返回优惠券信息
            CouponUserPO couponUserPO = couponService.getCouponUserByTradeNo(trade.getTradeNo());
            if (couponUserPO != null) {
                trade.setCoupon(couponUserPO);
            }
            // Addy liyongran 20150920 返回优惠券信息
            trade.setOrderList(orderList);
            tradeList.add(trade);
        }

        return tradeList;
    }

    @Override
    public List<TradeListVO> getUserOrderlistV1_3(String buyerId, Integer pageNum, Integer pageSize, String orderStatus,
                                                  Message m) {
        /** TODO Auto-generated method stub */
        Page<TradeBasePO> pags = null;
        // 区分状态 modify by liyongran 20150918
        // if (orderStatus != null &&
        // orderStatus.equals(TradeStatus.WAIT_PLATFORM_SEND.code)) { //
        // WAIT_PLATFORM_SEND
        // // 返回2种状态信息
        // pags = tradeBaseDAO.queryTradeSendAndReceiveBybuyerId(buyerId);
        // } else {
        // pags = tradeBaseDAO.queryBaseTradeBybuyerId(buyerId, orderStatus);
        // }
        pags = tradeBaseDAO.queryBaseTradeBybuyerId(buyerId, orderStatus);
        List<TradeBasePO> baseList = pags.getResult();
        if (baseList == null) {
            return null;
        }
        m.addData("totalCount", pags.getTotal());
        List<TradeListVO> tradeList = new ArrayList<TradeListVO>();
        for (TradeBasePO tradeBasePO : baseList) {
            // 基本交易
            TradeListVO trade = new TradeListVO();
            trade.setTradeNo(tradeBasePO.getTradeNo());
            trade.setUserTradeNo(tradeBasePO.getUserTradeNo());
            trade.setTradeStatus(tradeBasePO.getTradeStatus());
            // 改为支付价格
            trade.setTotalPrice(tradeBasePO.getPayPrice());
            trade.setTradeTime(tradeBasePO.getCreateTime().getTime());
            List<OrderBaseVO> orderList = tradeBaseDAO.queryOrderBaseByTradeNo(trade.getTradeNo());// 获取交易下的订单信息
            // Addy liyongran 20150920 返回优惠券信息
            CouponUserPO couponUserPO = couponService.getCouponUserByTradeNo(trade.getTradeNo());
            if (couponUserPO != null) {
                trade.setCoupon(couponUserPO);
            }
            // Addy liyongran 20150920 返回优惠券信息
            trade.setOrderList(orderList);
            tradeList.add(trade);
        }

        return tradeList;
    }

    @Override
    @Deprecated
    public int refund(String tradeNo, Float money) {
        // 服务器异步通知页面路径
        String notifyUrl = PropertiesUtil.getProperty("/properties/alipay.properties", "refund_notify_url");
        // 需http://格式的完整路径，不允许加?id=123这类自定义参数
        // 卖家支付宝帐户
        String sellerEmail = PropertiesUtil.getProperty("/properties/alipay.properties", "seller_email");
        // 卖家支付宝帐户
        String partner = PropertiesUtil.getProperty("/properties/alipay.properties", "partner");
        String inputCharset = PropertiesUtil.getProperty("/properties/alipay.properties", "input_charset");
        String privateKey = PropertiesUtil.getProperty("/properties/alipay.properties", "private_key");
        DateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat smBatch = new SimpleDateFormat("yyyyMMdd");
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", inputCharset);
        sParaTemp.put("sign_type", "MD5");
        sParaTemp.put("refund_notify_url", notifyUrl);
        sParaTemp.put("notify_url", notifyUrl);
        String date = smBatch.format(new Date());
        sParaTemp.put("seller_email", sellerEmail);
        sParaTemp.put("seller_user_id", partner);
        sParaTemp.put("refund_date", sm.format(new Date()));
        sParaTemp.put("batch_no", date + UtilDate.getOrderNum());
        sParaTemp.put("batch_num", "1");
        sParaTemp.put("detail_data", tradeNo + "^" + money + "^协商退款");
        String content = AlipayCore.createOriginLinkString(sParaTemp);
        String sign = MD5.sign(content, privateKey, inputCharset);
        sParaTemp.put("sign", sign);
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
        logger.info("!!!!!!!!!!!!!!!!:" + sHtmlText + ":!!!!!!!!!!!!!!!!!!!!!!");
        return 0;
    }

    public void addtradeComment(TradeJudgementPO judgementPO) {
        tradeJudgementDAO.insert(judgementPO);
    }

    /**
     * @param tradeNo
     * @author shenyb 2015年8月12日 上午12:24:24
     * @Method: refundSuccess
     * @Description: 退款成功
     */
    @Override
    @Transactional
    public void refundSuccess(String tradeNo, RefundNotifyVO vo) {
        logger.info("com.need.api.service.trade.TradeManager#refundSuccess........................");
        List<TradeBasePO> trades = tradeBaseDAO.getByTradeNo(tradeNo);
        if (trades != null && trades.size() > 0) {

            for (TradeBasePO trade : trades) {
                // // 1交易支付表
                TradePayPO tradePay = new TradePayPO();
                tradePay.setPayId(BizCodeUtil.generatePayId(tradeNo, vo.getOutPayNo()));
                tradePay.setTradeNo(tradeNo);
                tradePay.setOutPayNo(vo.getOutPayNo());
                tradePay.setAmount(Integer.valueOf(vo.getTotalFee()));
                tradePay.setPayChannel(PayChannelEnum.ALIPAY.code);
                tradePay.setPayTime(new Date());
                tradePay.setPayType(PayTypeEnum.TRADE_REFUND.code);
                tradePayDAO.insert(tradePay);
                // 2交易主表
                TradeStatus tradeStatus = TradeStatus.WAIT_RECEIVE;
                TradeStatus processTradeStatus = TradeStatusProcess.tradeProcess(tradeStatus);
                if (trade.getTradeStatus().code.equals(tradeStatus.code)) {
                    trade.setTradeStatus(processTradeStatus);
                    trade.setOrderStatus(processTradeStatus);
                    trade.setTradeTime(new Date());
                    tradeBaseDAO.updateByOrderNo(trade);
                } else {
                    logger.error("@#@#@#@#@###################:tradeNo:" + tradeNo + ":status is not wait_receive");
                    continue;
                }
                trade.setTradeStatus(processTradeStatus);
                tradeBaseDAO.updateByOrderNo(trade);
            }

        }

    }

    @Override
    @Transactional
    public String dealWechatNotify(Map<String, String> resultMap) {

        Integer totalFee = Integer.valueOf(resultMap.get("total_fee"));
        String outTradeNo = resultMap.get("transaction_id");
        String tradeNo = resultMap.get("out_trade_no");
        WXPay wxPay = new WXPay();
        Order order = new WXPay.Order(tradeNo, totalFee);
        if (wxPay.orderQuery(order, outTradeNo)) {
            PayNotifyVO vo = new PayNotifyVO();
            vo.setTotalFee(totalFee);
            vo.setOutTradeNo(outTradeNo);
            vo.setTradeNo(tradeNo);
            vo.setPayChannel(PayChannelEnum.WECHAT);
            vo.setMemo(JSON.toJSONString(resultMap));
            return paySuccess(tradeNo, vo);
        } else {
            logger.info("tradeNo :" + tradeNo + "verify fail");
            return Constants.NOTIFY_FAIL;
        }
    }

    /**
     * @param resultMap
     * @return
     * @author Rylan 2015年12月11日 上午1:45:28
     * @Method: dealWechatNotify
     * @Description: TODO
     * @see com.need.common.core.service.trade.TradeManager#dealWechatNotify(java.util.Map)
     */
    @Override
    @Transactional
    public String dealWechatNotifyForWap(Map<String, String> resultMap) {
        logger.debug(" dealWechatNotifyForWap in..");
        Integer totalFee = Integer.valueOf(resultMap.get("total_fee"));
        String outTradeNo = resultMap.get("transaction_id");
        String tradeNo = resultMap.get("out_trade_no");
        WXPay wxPay = new WXPay();
        Order order = new WXPay.Order(tradeNo, totalFee);
        if (wxPay.orderQueryForWap(order, outTradeNo)) {
            PayNotifyVO vo = new PayNotifyVO();
            vo.setTotalFee(totalFee);
            vo.setOutTradeNo(outTradeNo);
            vo.setTradeNo(tradeNo);
            vo.setPayChannel(PayChannelEnum.WECHAT);
            logger.info("resultMap:%s"+resultMap);
            try {
                vo.setMemo(JSON.toJSONString(resultMap));
            }catch (Exception e){
                e.printStackTrace();
            }
            return paySuccess(tradeNo, vo);
        } else {
            logger.info("tradeNo :" + tradeNo + "verify fail");
            return Constants.NOTIFY_FAIL;
        }
    }

    @Override
    @Transactional
    public String dealAlipayNotify(Map<String, String> params) {
        logger.info(String.format("params::%s", JSON.toJSON(params)));
        // 商户订单号
        String tradeNo = params.get("out_trade_no");
        // 支付宝交易号
        String outTradeNo = params.get("trade_no");
        // 交易状态
        String tradeStatus = params.get("trade_status");

        // 交易金额
        String totalFee = params.get("total_fee");

        // 买家支付宝账户
        String buyerId = params.get("buyer_id");
        // 商品单价
        String price = params.get("price");
        Integer tempTotalFee = new BigDecimal(totalFee).multiply(BigDecimal.valueOf(100L)).intValue();
        PayNotifyVO aliPayNotifyVO = new PayNotifyVO();
        aliPayNotifyVO.setPayChannel(PayChannelEnum.ALIPAY);
        aliPayNotifyVO.setTradeNo(tradeNo);
        aliPayNotifyVO.setTradeStatus(tradeStatus);
        aliPayNotifyVO.setOutTradeNo(outTradeNo);
        aliPayNotifyVO.setTotalFee(tempTotalFee);
        aliPayNotifyVO.setBuyerId(buyerId);
        aliPayNotifyVO.setPrice(price);
        aliPayNotifyVO.setMemo(JSON.toJSONString(params));
        logger.info("AlipayNotify.verify(params):" + AlipayNotify.verify(params));
        if (AlipayNotify.verify(params)) {// 验证成功
            if (tradeStatus.equals("TRADE_SUCCESS") || tradeStatus.equals("TRADE_FINISHED")) {
                System.out.println("aliPayNotifyVO:" + aliPayNotifyVO);
                logger.info(String.format("********************支付成功*****************************tradeNO:%s", tradeNo));
                // 查询订单，修改状态
                /** record all as memo TODO */
                return paySuccess(tradeNo, aliPayNotifyVO);
            }
        }
        return Constants.NOTIFY_FAIL;
    }
}
