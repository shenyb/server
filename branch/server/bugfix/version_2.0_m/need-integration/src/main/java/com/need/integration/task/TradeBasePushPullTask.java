package com.need.integration.task;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.need.biz.utils.CurrencyUtil;
import com.need.integration.common.enums.DeliverStatusEnum;
import com.need.integration.common.enums.PayChannelEnum;
import com.need.integration.common.enums.RetrieveStatusEnum;
import com.need.integration.common.enums.ServiceProviderEnum;
import com.need.integration.common.enums.TrackingCodeEnum;
import com.need.integration.common.enums.WarehouseTypeEnum;
import com.need.integration.common.haimeiSdk.DefaultClient;
import com.need.integration.common.haimeiSdk.IscsException;
import com.need.integration.common.haimeiSdk.domain.out.Order;
import com.need.integration.common.haimeiSdk.domain.out.OrderDet;
import com.need.integration.common.haimeiSdk.request.base.PushTradesRequest;
import com.need.integration.common.haimeiSdk.request.base.TradeStatusQueryRequest;
import com.need.integration.common.haimeiSdk.response.base.PushTradesResponse;
import com.need.integration.common.haimeiSdk.response.base.TradeStatusQueryResponse;
import com.need.integration.common.haimeiSdk.test.Constant;
import com.need.integration.constant.Constants;
import com.need.integration.dao.jdbc.api.coupon.CouponUserDAO;
import com.need.integration.dao.jdbc.api.coupon.po.CouponUserPO;
import com.need.integration.dao.jdbc.api.goods.GoodsDetailDAO;
import com.need.integration.dao.jdbc.api.goods.GoodsMainDAO;
import com.need.integration.dao.jdbc.api.goods.po.GoodsDetailPO;
import com.need.integration.dao.jdbc.api.goods.po.GoodsMainPO;
import com.need.integration.dao.jdbc.api.trade.TradeAddressDAO;
import com.need.integration.dao.jdbc.api.trade.TradeBaseDAO;
import com.need.integration.dao.jdbc.api.trade.TradePayDAO;
import com.need.integration.dao.jdbc.api.trade.TradePushPullDAO;
import com.need.integration.dao.jdbc.api.trade.TradeRetrieveStatusRecordDAO;
import com.need.integration.dao.jdbc.api.trade.po.TradeAddressPO;
import com.need.integration.dao.jdbc.api.trade.po.TradeBasePO;
import com.need.integration.dao.jdbc.api.trade.po.TradePayPO;
import com.need.integration.dao.jdbc.api.trade.po.TradePushPullPO;
import com.need.integration.dao.jdbc.api.trade.po.TradeRetrieveStatusRecord;
import com.need.integration.dao.jdbc.api.user.UserBaseDAO;
import com.need.integration.dao.jdbc.api.user.po.UserBase;
import com.need.integration.dao.jdbc.bops.trade.BopsTradeLogisticsDAO;
import com.need.integration.dao.jdbc.bops.trade.BopsTradePushPullDAO;
import com.need.integration.dao.jdbc.bops.trade.po.BopsTradeLogisticsPO;
import com.need.integration.dao.jdbc.bops.trade.po.BopsTradePushPullPO;
import com.need.integration.pub.Message;
import com.need.integration.pub.ResultData;
import com.need.integration.service.api.trade.PortalTradeService;
import com.need.integration.service.bops.BirdexTradeService;
import com.need.integration.service.vo.GoodsParamsVO;
import com.need.integration.service.vo.LogisticsInfoJsonVO;
import com.need.integration.service.vo.TradePushParamVO;
import com.need.integration.util.BatchNoUtil;
import com.need.integration.util.BizCodeUtil;
import com.need.integration.util.DateUtil;
import com.need.integration.util.MD5Util;
import com.need.integration.util.OrderClientUtil;
import com.need.integration.util.PropertiesUtil;
import com.need.integration.util.StringUtil;
import com.need.trade.enums.TradeStatus;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年11月12日 下午3:16:15
 * @ClassName TradeBasePushPullTask
 * @Description
 * @version V1.3
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年11月12日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class TradeBasePushPullTask {
	private static Logger logger = Logger.getLogger(TradeBasePushPullTask.class);
	@Autowired
	private PortalTradeService portalTradeService;
	@Autowired
	private TradeBaseDAO tradeBaseDAO;
	@Autowired
	private BopsTradePushPullDAO bopsTradePushPullDAO;
	@Autowired
	private TradeRetrieveStatusRecordDAO retrieveStatusRecordDAO;

	@Autowired
	private TradePushPullDAO apiTradePushPullDAO;
	@Autowired
	private UserBaseDAO userBaseDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private GoodsDetailDAO goodsDetailDAO;
	@Autowired
	private TradeAddressDAO tradeAddressDAO;
	@Autowired
	private TradePayDAO tradePayDAO;
	@Autowired
	private BopsTradeLogisticsDAO bopsTradeLogisticsDAO;
	@Autowired
	private BirdexTradeService birdexTradeService;
	
	@Autowired
	private CouponUserDAO couponUserDAO;
	private static int PAGE_NUM = 1;
	private static int PAGE_SIZE = 200;

	private String requestUrl = "https://api.mch.weixin.qq.com/cgi-bin/mch/customs/customdeclareorder";
//	private String appId = PropertiesUtil.getProperty("/properties/wechat.properties", "app_id");
	private String appId = "wxa2769733f861611f";
	private String mchId = PropertiesUtil.getProperty("/properties/wechat.properties", "partner");
	private String partnerKey = PropertiesUtil.getProperty("/properties/wechat.properties", "private_key");
	private String mchCustomsNo = PropertiesUtil.getProperty("/properties/companyInfo.properties", "cbeCode");
	
	// @Scheduled(cron = "${tradePushTime}")
	public void pushTradeBase() {
		PageHelper.startPage(PAGE_NUM, PAGE_SIZE);
		List<TradeBasePO> tradeList = tradeBaseDAO.queryGlobalTradeToPush();
		logger.info("pushTradeBasesize before:" + tradeList.size());
		// 获取需要重新发送的集合
		tradeList.addAll(getRepeatToSendList());
		logger.info("pushTradeBasesize after:" + tradeList.size());
		for (TradeBasePO trade : tradeList) {
			logger.info("pushTradeBase.tradeNo:" + trade.getUserTradeNo());
			// 1如果已经推送成功则不在推送
			String pushStatus = trade.getPushStatus();
			if (Boolean.TRUE.toString().toUpperCase().equals(pushStatus)) {
				continue;
			}
			// 只处理支付宝
			/**
			 * 现需要放开微信
			 */
//			if (PayChannelEnum.WECHAT.code.equals(trade.getPayChannel())) {
//				continue;
//			}
			// 2设置要推送的参数信息
			TradePushParamVO param = new TradePushParamVO();
			String userTradeNo = trade.getUserTradeNo();
			if (StringUtil.isBlank(userTradeNo)) {
				logger.error("push err:" + JSON.toJSONString(trade));
			}
			param.setOrderNo(userTradeNo);
			// 设置商品属性
			String goodsId = trade.getGoodsId();
			GoodsMainPO goods = goodsMainDAO.getByGoodsId(goodsId);
			GoodsDetailPO goodsDetail = goodsDetailDAO.selectByPrimaryKey(goodsId);
			if (goods == null) {
				logger.error(
						String.format("orderNO :%s ,goodsId :%s not exist", trade.getOrderNo(), trade.getGoodsId()));
				continue;
			}
			if (goodsDetail == null) {
				logger.error(String.format("orderNO :%s ,goodsId :%s detail  not exist", trade.getOrderNo(),
						trade.getGoodsId()));
				continue;
			}
			String warehouseType = trade.getWarehouseType();
			if (StringUtil.isBlank(warehouseType)) {
				warehouseType = goods.getWarehouseType();
			}
			param.setGoodsNo(goods.getGoodsBarcode());
			param.setShelfGoodsName(StringUtil.filterSpecilCharators(goods.getGoodsName()));
			// 商品计量单位和原产国
			if (StringUtil.isBlank(goods.getGuojianCount()) || StringUtil.isBlank(goods.getHaiguanCount())
					|| StringUtil.isBlank(goods.getGuojianCountryCode())
					|| StringUtil.isBlank(goods.getHaiguanCountryCode())) {
				logger.error(
						String.format("goodsid:%s UnitCiq,UnitCusparam,OriginCountryCiq ,OriginCountryCus,is null,%s ",
								goods.getGoodsId(), JSON.toJSONString(goods)));
				continue;
			}
			param.setUnitCiq(goods.getGuojianCount());
			param.setUnitCus(goods.haiguanCount);
			param.setOriginCountryCiq(goods.getGuojianCountryCode());
			param.setOriginCountryCus(goods.getHaiguanCountryCode());
			GoodsParamsVO goodsParamsVO = JSONObject.parseObject(goodsDetail.getGoodsParams(), GoodsParamsVO.class);
			String netWeight = StringUtil.dealWeight(goodsParamsVO.getWeight());
			if (netWeight == null) {
				logger.error(String.format("goods weight not right:weight:%s", goodsParamsVO.getWeight()));
				continue;
			}
			param.setNetWeight(netWeight);
			String weight = Double.valueOf(netWeight).doubleValue() + Constants.BOX_WEIGHT + "";
			logger.info("weight.......:" + weight);
			// 两个double相加会有误差，有多个小数
			if (weight.length() > 4) {
				weight = weight.substring(0, 4);
			}
			param.setWeight(weight);
			param.setQuantity(trade.getBuyCount() + "");
			// charge总费用
			param.setPayPrice(trade.getPayPrice() + "");
			param.setGoodsValue(param.getPayPrice());
			param.setCharge(trade.getPayPrice() + "");
			// 设置单价和总价
			setPriceAndTotalPrice(trade, param);
			param.setOptType("1");
		
			/**  15039595952
			 * 单独处理王勇订单 18610809424
			 */
			if("18610809424".equals(trade.getTelephone())){
				param.setCharge(String.valueOf(trade.getTotalPrice()));
				param.setGoodsValue(String.valueOf(trade.getTotalPrice()));
				param.setPrice(String.valueOf(trade.getTotalPrice()));
				param.setPriceTotal(String.valueOf(trade.getTotalPrice()));
				param.setPayPrice(String.valueOf(trade.getTotalPrice()));
//				param.setOptType("2");
			}
			
			//setPriceAndTotalPriceV2_0(trade, param);
			// 设置收获人以及地址信息
			String logisticsValue = trade.getLogisticsValue();
			param.setConsignee(trade.getReceiver());
			param.setConsigneeTelephone(trade.getTelephone());
			param.setConsigneeAddress(logisticsValue + trade.getAddress());
			param.setConsigneeProvince(trade.getLogisticsValue());
			param.setConsigneeProvince(StringUtil.getAddressProvince(logisticsValue));
			param.setConsigneeCity(StringUtil.getAddressCity(logisticsValue));
			param.setConsigneeZone(StringUtil.getAddressZone(logisticsValue));
			String idCard = trade.getCertificationCard();
			if (StringUtil.isBlank(idCard)) {
				logger.error(String.format("user idcard is null :tradeNO:%s,user_trade_no", trade.getTradeNo(),
						trade.getUserTradeNo()));
				continue;
			}
			param.setIdNumber(idCard);
			// 支付流水号
			TradePayPO tradePay = tradePayDAO.getByTradeNo(trade.getTradeNo());
			if (tradePay == null) {
				logger.error(String.format("orderNO :%s , paymentNo not exist", trade.getOrderNo()));
				continue;
			}
			param.setPaymentNo(tradePay.getOutPayNo());
			String payChannel = trade.getPayChannel();
			if (PayChannelEnum.ALIPAY.code.equals(payChannel)) {
				param.setPaymentName("支付宝（中国）网络技术有限公司");
				param.setPaymentCode("P0001");
			} else {
				param.setPaymentName("财付通支付科技有限公司");
				param.setPaymentCode("P0015");
			}
			BopsTradePushPullPO pushPO = bopsTradePushPullDAO.getByTradeNo(trade.getUserTradeNo());
			if (pushPO == null || StringUtil.isBlank(pushPO.getBatchNo())) {
				logger.info(String.format("TradeBasePushPullTask tradeNo:%s,first Push", trade.getUserTradeNo()));
				// 设置批次号
				param.setBatchNo(BatchNoUtil.getBatchNo());
			} else {
				param.setBatchNo(pushPO.getBatchNo());
			}
			if (trade.getPushTime() != null) {
				logger.info(String.format("TradeBasePushPullTask tradeNo:%s,has pushTime,repeat Push",
						trade.getUserTradeNo()));
			}
			logger.info("pushTradeBase.params:" + JSON.toJSONString(param));
			// 3调用e贸易接口
			ResultData result = OrderClientUtil.pushTrade(param);
			logger.info("pushTradeBase.result:" + JSON.toJSONString(result));
			BopsTradePushPullPO pull = createTradePushPull(trade, param, tradePay.getOutPayNo());
			pull.setWarehouseType(warehouseType);
			if (ResultData.SUCCESS == result.getCode()) {
				/**
				 * 1前台交易表改为已推送状态 2插入推送记录表，状态为成功
				 */
				tradeBaseDAO.updateByOrderNo(trade.getOrderNo(), Boolean.TRUE.toString().toUpperCase());
				pull.setPushReturnStatus(Boolean.TRUE.toString().toUpperCase());
				pull.setMemoPush(JSON.toJSONString(param) + "##" + JSON.toJSONString(result.getMsg()));
				logger.info(String.format("success:tradeNo:%s,return msg:%s", trade.getUserTradeNo(), result.getMsg()));
			} else {
				// 如果失败则memo存参数加失败消息
				pull.setMemoPush(JSON.toJSONString(param) + "##" + JSON.toJSONString(result.getMsg()));
				logger.error(String.format("err reason:%s", result.getMsg()));
				pull.setPushReturnStatus(Boolean.FALSE.toString().toUpperCase());
			}
			// 插入或更新推送记录表
			BopsTradePushPullPO bopsPull = bopsTradePushPullDAO.getByTradeNo(trade.getUserTradeNo());
			TradePushPullPO apiPull = new TradePushPullPO();
			BeanUtils.copyProperties(pull, apiPull);

			if (bopsPull == null) {
				bopsTradePushPullDAO.insert(pull);
				apiTradePushPullDAO.insert(apiPull);
			} else {
				bopsTradePushPullDAO.updateByOrderNo(pull);
				apiTradePushPullDAO.updateByOrderNo(apiPull);
			}
		}
		logger.info("pushTradeBase end.............");

	}

	/**
	 * 
	 * @author shenyb 2015年12月7日 下午3:22:15 @Method:
	 *         setPriceAndTotalPrice @Description: 使用原先方式 @param trade @param
	 *         param @throws
	 */
	private void setPriceAndTotalPrice(TradeBasePO trade, TradePushParamVO param) {
		int count = trade.getBuyCount();
		int value = 0;
		int price = 0;// 支付时商品的售价
		int totalPrice = 0;// 商品总价
		CouponUserPO coupon = couponUserDAO.getByTradeNo(trade.getTradeNo());
		if (coupon != null && coupon.getValue() > 0) {
			value = coupon.getValue();
			// 如果以后买多个商品，这里，price和totalPrice会有偏大的误差
			price = trade.getBuyPrice() - value / count;
			totalPrice = price * count;
			// edit add 算上运费
			if (trade.getTransportFee() != 0) {
				price = price + trade.getTransportFee() / count;
				totalPrice = price * count;
			}
			// 考虑上佣金
			if (trade.getCommission() != 0) {
				price = price + trade.getCommission() / count;
				totalPrice = price * count;
			}
		} else {
			// 如果优惠券不存在，则计算优惠金额,支付金额＝商品金额＋运费－优惠
			int discountFee = trade.getBuyPrice() * trade.getBuyCount() + trade.getTransportFee() - trade.getPayPrice();
			if (discountFee > 0) {
				price = trade.getBuyPrice() - discountFee / count;
				totalPrice = trade.getPayPrice() * count;
			} else {
				price = trade.getBuyPrice();
				totalPrice = price * count;
			}
			if (trade.getTransportFee() != 0) {
				price = price + trade.getTransportFee() / count;
				totalPrice = price * count;
			}
			// 考虑上佣金
			if (trade.getCommission() != 0) {
				price = price + trade.getCommission() / count;
				totalPrice = price * count;
			}
		}

		param.setPrice(price + "");
		param.setPriceTotal(totalPrice + "");
	}

	/**
	 * 
	 * @author shenyb 2015年12月7日 下午3:22:15 @Method:
	 *         setPriceAndTotalPrice @Description: 使用新方式 @param trade @param
	 *         param @throws
	 */
	private void setPriceAndTotalPriceV2_0(TradeBasePO trade, TradePushParamVO param) {
		// 支付时商品的售价,保税仓只购买一件，所以单价和总价一致
		int price = trade.getBuyPrice() + trade.getTransportFee() - trade.getDiscountAmount() - trade.getCommission();
		param.setPrice(price + "");
		param.setPriceTotal(price + "");
	}

	/**
	 * @author shenyb 2015年11月4日 下午4:43:09 @Method:
	 *         getRepeatToSendList @Description: @return @throws
	 */
	private List<TradeBasePO> getRepeatToSendList() {
		List<TradeBasePO> resultList = new ArrayList<TradeBasePO>();
		List<BopsTradePushPullPO> bopsPullList = bopsTradePushPullDAO.queryListToPull();
		for (BopsTradePushPullPO pull : bopsPullList) {
			List<TradeBasePO> tradeList = tradeBaseDAO.getByUserTradeNo(pull.getTradeNo());
			TradeBasePO trade = null;
			if (tradeList != null && tradeList.size() > 0) {
				trade = tradeList.get(0);
			}
			if (trade != null && TradeStatus.WAIT_PLATFORM_SEND.code.equals(trade.getTradeStatus())) {
				Date pushTime = trade.getPushTime();
				pushTime = (pushTime == null ? Calendar.getInstance().getTime() : pushTime);
				Date now = Calendar.getInstance().getTime();
				if ((now.getTime() - pushTime.getTime()) > Constants.REPEAT_TO_SEND_TIME) {
					trade.setPushStatus(Constants.FALSE);
					resultList.add(trade);
					//tradeBaseDAO.updateByOrderNo(trade.getOrderNo(), Constants.FALSE);
				}
			}
		}
		return resultList;
	}

	/**
	 * 
	 * @author shenyb 2015年10月31日 下午4:10:27 @Method:
	 *         recordRetrieveStatus @Description: @param record @throws
	 */
	private void recordRetrieveStatus(TradeRetrieveStatusRecord record) {
		List<TradeRetrieveStatusRecord> retrieveRecord = retrieveStatusRecordDAO
				.getByRetrieveStatusAndTradeNo(record.getTrackingCode(), record.getTradeNo());
		if (retrieveRecord == null || retrieveRecord.size() == 0) {
			logger.info("before insert record:userTradeNo" + record.getTradeNo() + ":" + JSON.toJSONString(record));
			retrieveStatusRecordDAO.insert(record);
		}
	}

	/**
	 * 
	 * @author shenyb 2015年9月23日 下午6:10:29 @Method: pushToApilay @Description:
	 *         推送给支付宝海关报关接口 @throws
	 */
	// @Scheduled(cron = "${pushToApilayTime}")
	public void pushToApilay() {
		PageHelper.startPage(PAGE_NUM, PAGE_SIZE);
		List<TradeBasePO> tradeList = tradeBaseDAO.queryListToPushToAlipay();
		for (TradeBasePO trade : tradeList) {
			// 已经推送成功不在推送
			if (Boolean.TRUE.toString().toUpperCase().equals(trade.getAlipayRetrieveStatus())) {
				continue;
			}
			String tradeNo = trade.getUserTradeNo();
			String outpayNo;
			String outRequestNo = BizCodeUtil.generateOutRequestNo();
			String amount = CurrencyUtil.fromFenToYuan(trade.getPayPrice() * trade.getBuyCount() + "");
			// 支付流水号
			TradePayPO tradePay = tradePayDAO.getByTradeNo(trade.getTradeNo());
			if (tradePay == null) {
				logger.error(String.format("orderNO :%s , paymentNo not exist", trade.getOrderNo()));
				continue;
			}
			outpayNo = tradePay.getOutPayNo();
			String customePlace = "";
			// 如果拿不到，则去商品表拿
			String warehouseType = trade.getWarehouseType();
			if (StringUtil.isBlank(warehouseType)) {
				GoodsMainPO goods = goodsMainDAO.getByGoodsId(trade.getGoodsId());
				warehouseType = (goods == null ? "" : goods.getWarehouseType());
			}
			if (WarehouseTypeEnum.TAX_WAREHOUSE.code.equals(warehouseType)) {
				customePlace = Constants.CUSTOM_PLACE_ZHENGZHOU;
			} else if (WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)) {
				/**
				 * 测试用，暂时改成深圳,后期正式的需要再改成广州
				 */
				customePlace = Constants.CUSTOM_PLACE_SHENZHEN;
			} else {
				continue;
			}
			ResultData result = OrderClientUtil.alipayDeclaration(tradeNo, outpayNo, outRequestNo, amount,
					customePlace);
			if (result.getCode() == ResultData.SUCCESS) {
				trade.setAlipayRetrieveStatus(Boolean.TRUE.toString().toUpperCase());
			}
			logger.info("result :=" + JSON.toJSONString(result));
			trade.setMemoAlipayRetrieve(JSON.toJSONString(result.getMsg()));
			int updateCount = tradeBaseDAO.updateAlipayRetrieveStatusByOrderNo(trade.getOrderNo(),
					Boolean.TRUE.toString().toUpperCase(), trade.getMemoAlipayRetrieve());
			// 清关记录表插入一条，表示，已经推送
			if (updateCount > 0) {
				TradeRetrieveStatusRecord record = new TradeRetrieveStatusRecord();
				record.setTradeNo(trade.getUserTradeNo());
				record.setOrderNo(trade.getUserOrderNo());
				// record.setRetrieveStatus(RetrieveStatusEnum.PAY_PUSH.code);
				record.setTrackingCode(TrackingCodeEnum.PAY_PUSH.code);
				record.setTrackingDesc(TrackingCodeEnum.PAY_PUSH.userDesc);
				recordRetrieveStatus(record);
			}
		}

	}
	
	/**
	 * @author xiehao 2015年11月30日 下午3:48:27
	 * @Method: pushToWechat  0 0/3 * * * ?
	 * @Description: TODO 推送微信支付单给海关
	 */
//	@Scheduled(cron = "*/10 * * * * ?")
	public void pushToWechat() {
		PageHelper.startPage(PAGE_NUM, PAGE_SIZE);
		List<TradeBasePO> tradeList = tradeBaseDAO.queryListToPushToWechat();
		for (TradeBasePO trade : tradeList) {
			//TODO 组装数据并调用微信接口
			HttpsURLConnection connection = null;
			try{
				connection = getHttpsURLConnection(requestUrl);
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
				connection.connect();
				OutputStream outputStream = connection.getOutputStream();
				byte[] data = buildPostData(trade).getBytes();
				if (data != null) {
					outputStream.write(data);
				}
				InputStream inputStream = connection.getInputStream();
				byte[] bytes = IOUtils.toByteArray(inputStream);
				String xmlResult = new String(bytes);
				logger.info("result:"+xmlResult);
				Map<String, String> resultMap = doXMLParse(xmlResult);
				logger.info(resultMap.get("result_code"));
				if(checkWechatIsSuccess(resultMap)){
					trade.setMemoWechatRetrieve(xmlResult);
					trade.setWechatRetrieveStatus(Boolean.TRUE.toString().toUpperCase());
					int updateCount = tradeBaseDAO.updateWechatRetrieveStatusByOrderNo(trade.getTradeNo(), trade.getWechatRetrieveStatus(),
							trade.getMemoWechatRetrieve());
					if(updateCount > 0){
						TradeRetrieveStatusRecord record = new TradeRetrieveStatusRecord();
						record.setTradeNo(trade.getUserTradeNo());
						record.setOrderNo(trade.getUserOrderNo());
						// record.setRetrieveStatus(RetrieveStatusEnum.PAY_PUSH.code);
						record.setTrackingCode(TrackingCodeEnum.PAY_PUSH.code);
						record.setTrackingDesc(TrackingCodeEnum.PAY_PUSH.userDesc);
						recordRetrieveStatus(record);
					}
				}
			}catch (IOException e) {
				e.printStackTrace();
			} catch (JDOMException e) {
				e.printStackTrace();
			} finally {
                if(connection != null) {
                    connection.disconnect();
                }
            }
		}

	}
	
	private boolean checkWechatIsSuccess(Map<String, String> resultMap){
		if("SUCCESS".equals(resultMap.get("return_code"))){
			if("SUCCESS".equals(resultMap.get("result_code"))){
				if("SUBMITTED".equals(resultMap.get("state"))){
					return true;
				}
				if("PROCESSING".equals(resultMap.get("state"))){
					return true;
				}
				if("SUCCESS".equals(resultMap.get("state"))){
					return true;
				}
			}
		}
		
		return false;
	}
	
	private String buildPostData(TradeBasePO trade) {
		SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>();
		sortedMap.put("sign_type", "MD5");
		sortedMap.put("appid", appId);
		sortedMap.put("mch_id", mchId);
		sortedMap.put("out_trade_no", trade.getUserTradeNo());
		sortedMap.put("transaction_id", tradePayDAO.getByTradeNo(trade.getTradeNo()).getOutPayNo());
//		sortedMap.put("sub_order_no", "");
//		sortedMap.put("fee_type", "CNY");
//		sortedMap.put("order_fee", trade.getPayPrice());
//		sortedMap.put("transport_fee", trade.getTransportFee());
//		sortedMap.put("product_fee", trade.getPayPrice() - trade.getTransportFee());
		sortedMap.put("customs", "ZHENGZHOU_BS");
		sortedMap.put("mch_customs_no", mchCustomsNo);
		String sign = createSign("UTF-8", sortedMap);
		logger.info("sign: " + sign);
		sortedMap.put("sign", sign);
		return mapToXml(sortedMap);
	}
	
	private String mapToXml(SortedMap<Object, Object> map) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		Iterator<?> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = String.valueOf(entry.getKey());
			sb.append("<" + key + ">").append(entry.getValue()).append("</" + key + ">");
		}
		sb.append("</xml>");
		logger.info(sb.toString());
		return sb.toString();
	}
	
	private String createSign(String charSet, SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set<?> es = parameters.entrySet();
		Iterator<?> it = es.iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) it.next();
			String k = String.valueOf(entry.getKey());
			String v = String.valueOf(entry.getValue());
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + partnerKey);
		logger.info(sb.toString());
		String sign = MD5Util.MD5Encode(sb.toString(), charSet).toUpperCase();
		return sign;
	}

	private HttpsURLConnection getHttpsURLConnection(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
		return httpsURLConnection;
	}
	
	
	private Map<String, String> doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map<String, String> m = new HashMap<String, String>();

		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List<?> list = root.getChildren();
		Iterator<?> it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List<?> children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}
		in.close();
		return m;
	}
	
	private String getChildrenText(List<?> children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator<?> it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List<?> list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}
	
	/**
	 * 
	 * @author shenyb 2015年9月12日 上午11:20:23
	 * @Method: pullTradeBase
	 * @Description: 拉取运单号
	 */
	// @Scheduled(cron = "${tradePullTime}")
	public void pullTradeBase() {
		PageHelper.startPage(PAGE_NUM, PAGE_SIZE);
		// 已经推送成功，并且没有拿到运单号的订单
		List<BopsTradePushPullPO> bopsPullList = bopsTradePushPullDAO.queryListToPull();
		for (BopsTradePushPullPO pull : bopsPullList) {
			String tradeNo = pull.getTradeNo();
			// 过滤掉不是代发货的订单
			if (!isNeedToDeal(tradeNo)) {
				continue;
			}
			ResultData result = OrderClientUtil.queryLogisticsNoByOrderNo(tradeNo);
			// 如果拿到运单号就存入到推送记录表
			if (ResultData.SUCCESS == result.getCode()) {
				String logisticsNo = (String) result.getData();
				pull.setLogisticsNo(logisticsNo);
			} else {
				// 记录失败信息
				logger.error(String.format("pullTradeBase,tradeNo:%s,%s", tradeNo, result.getMsg()));
				// pull.setMemoPush(result.getMsg());
			}
			bopsTradePushPullDAO.updateByOrderNo(pull);
			TradePushPullPO apiPull = new TradePushPullPO();
			BeanUtils.copyProperties(pull, apiPull);
			apiTradePushPullDAO.updateByOrderNo(apiPull);
			if (!StringUtil.isBlank(pull.getLogisticsNo())) {
				TradeRetrieveStatusRecord record = new TradeRetrieveStatusRecord();
				record.setOrderNo(pull.getOrderNo());
				record.setTradeNo(pull.getTradeNo());
				// record.setRetrieveStatus(RetrieveStatusEnum.LOGISTICS_PUSH.code);
				record.setTrackingCode(TrackingCodeEnum.LOGISTICS_PUSH.code);
				record.setTrackingDesc(TrackingCodeEnum.LOGISTICS_PUSH.userDesc);
				recordRetrieveStatus(record);
			}

		}

	}

	// @Scheduled(cron = "${tradeRetrieveTime}")
	public void retrieveInfo() {
		PageHelper.startPage(PAGE_NUM, PAGE_SIZE);
		// 拿到运单号的进行获取清关状态
		List<BopsTradePushPullPO> bopsPullList = bopsTradePushPullDAO.queryListToRetrieve();
		for (BopsTradePushPullPO pull : bopsPullList) {
			String tradeNo = pull.getTradeNo();
			// 过滤掉不是代发货的订单
			if (!isNeedToDeal(tradeNo)) {
				continue;
			}

			// 已经结关或退单的不再获取状态，sql已经过滤了这两个状态
			if (RetrieveStatusEnum.END.code.equals(pull.getRetrieveStatus())
					|| RetrieveStatusEnum.BACK.code.equals(pull.getRetrieveStatus())) {
				continue;
			}
			// String waybillNo = pull.getLogisticsNo();
			// 传入时返回结果结构不对
			String waybillNo = "";
			ResultData result = OrderClientUtil.getRetrieveResult(tradeNo, waybillNo);
			// 将清关状态存入数据库
			if (ResultData.SUCCESS == result.getCode()) {
				String status = (String) result.getData();
				pull.setMemoRetrieve(result.getMsg());
				logger.info("retrieveInfo status:" + status);
				for (RetrieveStatusEnum disStatusEnum : RetrieveStatusEnum.values()) {
					if (disStatusEnum.desc.equals(status)) {
						pull.setRetrieveStatus(disStatusEnum.code);
						break;
					}
				}
			} else {
				// 记录失败信息
				pull.setMemoRetrieve(result.getMsg());
			}
			bopsTradePushPullDAO.updateByOrderNo(pull);
			TradePushPullPO apiPull = new TradePushPullPO();
			BeanUtils.copyProperties(pull, apiPull);
			apiTradePushPullDAO.updateByOrderNo(apiPull);
			if (!StringUtil.isBlank(pull.getRetrieveStatus())) {
				TradeRetrieveStatusRecord record = new TradeRetrieveStatusRecord();
				record.setOrderNo(pull.getOrderNo());
				record.setTradeNo(pull.getTradeNo());
				// record.setRetrieveStatus(pull.getRetrieveStatus());
				record.setTrackingCode(pull.getRetrieveStatus());
				record.setTrackingDesc(TrackingCodeEnum.getUserDesc(pull.getRetrieveStatus()));
				recordRetrieveStatus(record);
			}
		}
	}

	/**
	 * 查看api交易表订单的状态，如果不是待发货，返回false,待发货，返回true
	 * 
	 * @author shenyb 2015年10月17日 下午9:54:25 @Method: isToDeal @Description:
	 * @param userTradeNo
	 * @return @throws
	 */
	public boolean isNeedToDeal(String userTradeNo) {
		if (StringUtil.isBlank(userTradeNo)) {
			return Boolean.FALSE;
		}
		List<TradeBasePO> tradePOList = tradeBaseDAO.getByUserTradeNo(userTradeNo);
		TradeBasePO tradePO = null;
		if (tradePOList == null || tradePOList.size() == 0) {
			return Boolean.FALSE;
		} else {
			tradePO = tradePOList.get(0);
		}
		// 过滤掉不是待发货的订单
		if (tradePO == null) {
			return Boolean.FALSE;
		}
		if (TradeStatus.WAIT_PLATFORM_SEND.code.equals(tradePO.getTradeStatus())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 给海美下单接口,通知海美发货 @author shenyb 2015年10月26日 下午9:33:42 @Method:
	 * createDeliverTrade @Description: @throws
	 */
	// @Scheduled(cron = "${createDeliverTrade}")
	@SuppressWarnings("unchecked")
	public void createDeliverTrade() {
		PageHelper.startPage(PAGE_NUM, PAGE_SIZE);
		// 要拿取发货信息的订单,使用清关状态标示是保税还是笨鸟
		List<BopsTradePushPullPO> tradeList = bopsTradePushPullDAO.queryListToDeliver();
		if (tradeList == null) {
			logger.info("tradeList null..........");
			return;
		}
		// 现在是一个一个推
		for (BopsTradePushPullPO bopsTradePushPull : tradeList) {

			DefaultClient client = new DefaultClient(OrderClientUtil.tradeDeliverBaseUrl, OrderClientUtil.appKey,
					OrderClientUtil.appSecret, Constant.FORMAT);
			PushTradesRequest request = new PushTradesRequest();
			List<TradeBasePO> list = tradeBaseDAO.getByUserTradeNo(bopsTradePushPull.getTradeNo());
			TradeBasePO tradeBasePO = list != null ? list.get(0) : null;
			if (tradeBasePO == null) {
				logger.error("trade no not exists:" + bopsTradePushPull.getTradeNo());
				continue;
			}
			List<Order> trades = request.getTrades();
			request.setCount(1L);
			Order order = new Order();
			String tradeNo = bopsTradePushPull.getTradeNo();
			order.setOrderNo(tradeNo);
			GoodsMainPO goods = goodsMainDAO.getByGoodsId(tradeBasePO.getGoodsId());
			order.setTransporterFlag("1");// 已经指定快递公司
			String isCheap = tradeBasePO.getIsCheap();
			int discountFee = 0;
			int value = 0;
			int postFee = tradeBasePO.getTransportFee();
			int totalFee = tradeBasePO.getBuyPrice() * tradeBasePO.getBuyCount() + postFee;
			Double payment = Double.valueOf(CurrencyUtil.fromFenToYuan(tradeBasePO.getPayPrice() + ""));
			OrderDet sku = new OrderDet();
			if (Constants.TRUE.equals(isCheap)) {
				int totalPrice = tradeBasePO.getBuyPrice() * tradeBasePO.getBuyCount();
				int payPrice = tradeBasePO.getPayPrice();
				int transportFee = tradeBasePO.getTransportFee();
				discountFee = totalPrice + transportFee - payPrice;
				if (discountFee > 0) {
					order.setDiscountFee(Double.valueOf(CurrencyUtil.fromFenToYuan(discountFee + "")));// 优惠金额
					sku.setDiscountFee(Double.valueOf(CurrencyUtil.fromFenToYuan(discountFee + "")));
				} else {
					order.setDiscountFee(0d);// 优惠金额
					sku.setDiscountFee(0d);
				}
			} else {
				CouponUserPO coupon = couponUserDAO.getByTradeNo(tradeBasePO.getTradeNo());
				if (coupon != null) {
					value = coupon.getValue();
					if (value > 0) {
						order.setDiscountFee(Double.valueOf(CurrencyUtil.fromFenToYuan(value + "")));// 优惠金额
						sku.setDiscountFee(Double.valueOf(CurrencyUtil.fromFenToYuan(value + "")));

					} else {
						order.setDiscountFee(0d);
						sku.setDiscountFee(0d);
					}
				} else {

					// 如果优惠券不存在，则计算优惠金额,支付金额＝商品金额＋运费－优惠
					discountFee = tradeBasePO.getBuyPrice() * tradeBasePO.getBuyCount() + tradeBasePO.getTransportFee()
							- tradeBasePO.getPayPrice();
					if (discountFee > 0) {
						order.setDiscountFee(Double.valueOf(CurrencyUtil.fromFenToYuan(discountFee + "")));// 优惠金额
						sku.setDiscountFee(Double.valueOf(CurrencyUtil.fromFenToYuan(discountFee + "")));
					} else {
						order.setDiscountFee(0d);
						sku.setDiscountFee(0d);

					}
				}
			}
			sku.setPayment(payment);
			sku.setSellPrice(Double.valueOf(CurrencyUtil.fromFenToYuan(tradeBasePO.getBuyPrice() + "")));
			order.setPayment(payment);// 支付金额
			if (totalFee != 0) {
				order.setTotalFee(Double.valueOf(CurrencyUtil.fromFenToYuan(String.valueOf(totalFee))));//
			} else {
				order.setTotalFee(0d);
			}
			if (postFee != 0) {
				order.setPostFee(Double.valueOf(CurrencyUtil.fromFenToYuan(String.valueOf(postFee))));//
			} else {
				order.setPostFee(0d);
			}
			order.setOrderStatus("0");
			order.setTransporterId(Constants.YUANTONG_TRANSPORT_ID);
			order.setOrderCreateTime(DateUtil.formatDateToStr(tradeBasePO.getCreateTime()));
			order.setOrderPayTime(DateUtil.formatDateToStr(tradeBasePO.getPayTime()));

			order.setBuyerNick(tradeBasePO.getReceiver());
			order.setReceiverCountry(Constants.CHINA);
			String logisticsValue = tradeBasePO.getLogisticsValue();
			order.setReceiverProvince(StringUtil.getAddressProvince(logisticsValue));
			order.setReceiverCity(StringUtil.getAddressCity(logisticsValue));
			order.setReceiverCounty(StringUtil.getAddressZone(logisticsValue));
			order.setReceiverAddress(tradeBasePO.getAddress());
			order.setReceiverName(tradeBasePO.getReceiver());
			order.setReceiverMobile(tradeBasePO.getTelephone());
			order.setShopId(Long.valueOf(OrderClientUtil.shopId));
			order.setStockId(Long.valueOf(OrderClientUtil.stockId));

			order.setOutSid(bopsTradePushPull.getLogisticsNo());
			order.setNeedInvoice(0L);
			order.setNeedCard(0L);
			List<OrderDet> skus = order.getSkus();
			sku.setProductCode(goods.getGoodsBarcode());
			sku.setOwnerId(Long.valueOf(OrderClientUtil.ownerId));
			sku.setShopId(Long.valueOf(OrderClientUtil.shopId));
			sku.setQty(1L);
			skus.add(sku);
			trades.add(order);
			PushTradesResponse response = null;
			try {
				response = (PushTradesResponse) client.execute(request);
			} catch (IscsException e) {
				logger.error(String.format("createDeliverTrade request:%s response:%s fail", JSON.toJSONString(request),
						JSON.toJSONString(response)));
				e.printStackTrace();
				continue;
			}
			if (response == null) {
				logger.info(String.format("createDeliverTrade trade:%s fail", JSON.toJSONString(request)));
			}
			logger.info("createDeliverTrade request: response:" + JSON.toJSONString(request) + "&&"
					+ JSON.toJSONString(response));
			PushTradesResponse data = response.getData();
			if (data != null && data.getSuccessCount() == 1) {
				bopsTradePushPull.setDeliverPushStatus(Constants.TRUE);
			} else {
				bopsTradePushPull.setDeliverPushStatus(Constants.FALSE);
			}
			bopsTradePushPull.setMemoDeliverPush(JSON.toJSONString(response));
			bopsTradePushPullDAO.updateByTradeNo(bopsTradePushPull);
		}
	}

	/**
	 * 查询海美的发货状态，如果完成，则我们的系统就自动发货 @author shenyb 2015年10月27日 下午2:02:13 @Method:
	 * getTradeDeliverStatus @Description: @throws
	 */
	// @Scheduled(cron = "${tradeDeliverTime}")
	public void getDeliverTradeListToPullStatus() {
		PageHelper.startPage(PAGE_NUM, PAGE_SIZE);
		// 要拿取发货信息的订单
		List<BopsTradePushPullPO> tradeList = bopsTradePushPullDAO.getDeliverTradeListToPullStatus();
		for (BopsTradePushPullPO bopsTradePushPull : tradeList) {

			DefaultClient client = new DefaultClient(OrderClientUtil.tradeDeliverBaseUrl, OrderClientUtil.appKey,
					OrderClientUtil.appSecret, Constant.FORMAT);
			TradeStatusQueryRequest request = new TradeStatusQueryRequest();
			request.setStockId(Long.valueOf(OrderClientUtil.stockId));
			request.setShopId(Long.valueOf(OrderClientUtil.shopId));
			request.setOrderNo(bopsTradePushPull.getTradeNo());
			try {
				@SuppressWarnings("unchecked")
				TradeStatusQueryResponse response = (TradeStatusQueryResponse) client.execute(request);
				if (response.getErrorCode().equals("100")) {
					Order trade = response.getData().getTrades().get(0);
					String orderNo = trade.getOrderNo();
					long status = trade.getStatus();
					logger.info(String.format("getDeliverTradeListToPullStatus:orderNo:%s,status:%s", orderNo, status));
					String transporterId = trade.getTransporterId();// 运输商
					if (Constants.YUANTONG_TRANSPORT_ID.equals(transporterId)) {
						transporterId = ServiceProviderEnum.YUAN_TONG.code;
					}
					String outIds = bopsTradePushPull.getLogisticsNo();// 运单号
					if (Long.valueOf(DeliverStatusEnum.OK.code) == status) {
						// 自动发货,推送给E贸易的orderNo为我们交易表的userTradeNo
						logger.info(String.format("send:ordrNo:%s,transporterId:%s,outIds:%s", orderNo, transporterId,
								outIds));
						birdexTradeService.send(orderNo, transporterId, outIds);
						bopsTradePushPull.setOrderStatus(TradeStatus.WAIT_RECEIVE.code);
						bopsTradePushPull.setTradeStatus(TradeStatus.WAIT_RECEIVE.code);
						bopsTradePushPull.setDeliverStatus(DeliverStatusEnum.OK.code);
						// 发货后插入清关记录表一条数据
						if (!StringUtil.isBlank(bopsTradePushPull.getLogisticsNo())) {
							TradeRetrieveStatusRecord record = new TradeRetrieveStatusRecord();
							record.setOrderNo(bopsTradePushPull.getOrderNo());
							record.setTradeNo(bopsTradePushPull.getTradeNo());
							// record.setRetrieveStatus(RetrieveStatusEnum.DELIVER_DONE.code);
							record.setTrackingCode(TrackingCodeEnum.DELIVER_DONE.code);
							record.setTrackingDesc(TrackingCodeEnum.DELIVER_DONE.userDesc + ",单号:" + outIds);
							recordRetrieveStatus(record);
						}
					}
					bopsTradePushPull.setMemoDeliver(JSON.toJSONString(response));
					String pull = bopsTradePushPull.getDeliverPullStatus();
					if (StringUtil.isBlank(pull)) {
						bopsTradePushPull.setDeliverPullStatus(status + "");
					} else if (!pull.contains(status + "")) {
						bopsTradePushPull.setDeliverPullStatus(status + ",");
					}
					bopsTradePushPullDAO.updateByOrderNo(bopsTradePushPull);
				}

			} catch (IscsException e) {
				logger.error("IscsException..." + e.getMessage());
			}

		}

	}

	private BopsTradePushPullPO createTradePushPull(TradeBasePO trade, TradePushParamVO param, String paymentNo) {
		BopsTradePushPullPO pull = new BopsTradePushPullPO();
		pull.setOrderNo(trade.getUserOrderNo());
		pull.setTradeNo(trade.getUserTradeNo());
		pull.setOrderStatus(trade.getTradeStatus());
		pull.setOutTradeNo(paymentNo);
		pull.setTradeStatus(trade.getTradeStatus());
		pull.setMemoPush(JSON.toJSONString(param));
		pull.setBatchNo(param.getBatchNo());
		return pull;
	}
}
