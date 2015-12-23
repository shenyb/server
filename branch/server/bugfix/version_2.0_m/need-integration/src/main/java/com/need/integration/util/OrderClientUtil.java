package com.need.integration.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentHelper;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.need.biz.utils.CurrencyUtil;
import com.need.integration.common.alipay.config.AlipayConfig;
import com.need.integration.common.alipay.sign.MD5;
import com.need.integration.common.alipay.util.AlipayCore;
import com.need.integration.common.alipay.util.AlipaySubmit;
import com.need.integration.pub.ResultData;
import com.need.integration.service.vo.CreateDeliverTradeParamVO;
import com.need.integration.service.vo.CreateDeliverTradeResult;
import com.need.integration.service.vo.QueryDeliverTradeStatusResult;
import com.need.integration.service.vo.QueryTradeDeliverStatusParamVO;
import com.need.integration.service.vo.TradePushParamVO;

public class OrderClientUtil {
	private static Logger logger = Logger.getLogger(OrderClientUtil.class);
	// 192.168.107.166:8080//192.168.108.25:8080，9092是测试地址,9090是正式环境
	private static String pushOrderUrl = PropertiesUtil.getProperty("/properties/constants.properties", "pushOrderUrl");// "http://218.28.185.212:9092/BIService/service/order/pushOrder";//
																														// "http://192.168.107.166:8080/BIService/service/order/pushOrder";
	private static String queryLogisticsUrl = PropertiesUtil.getProperty("/properties/constants.properties",
			"queryLogisticsUrl");// "http://218.28.185.212:9092/BIService/service/query/logisticsMate";//
									// "http://192.168.107.166:8080/BIService/service/query/logisticsMate";
	private static String retrieveInfoUrl = PropertiesUtil.getProperty("/properties/constants.properties",
			"retrieveInfoUrl");// "http://218.28.185.212:8080/BDIService/ws/retrieveInfo/orderStatus";//
								// 只有正式地址

	private static final String companyCode = PropertiesUtil.getProperty("/properties/companyInfo.properties",
			"companyCode");
	private static final String companyName = PropertiesUtil.getProperty("/properties/companyInfo.properties",
			"companyName");
	private static final String ecCode = PropertiesUtil.getProperty("/properties/companyInfo.properties", "ecCode");
	private static final String ecName = PropertiesUtil.getProperty("/properties/companyInfo.properties", "ecName");
	private static final String cbeCode = PropertiesUtil.getProperty("/properties/companyInfo.properties", "cbeCode");
	private static final String cbeName = PropertiesUtil.getProperty("/properties/companyInfo.properties", "cbeName");
	private static final String ecpCodeCiq = PropertiesUtil.getProperty("/properties/companyInfo.properties",
			"ecpCodeCiq");
	private static final String ecpNameCiq = PropertiesUtil.getProperty("/properties/companyInfo.properties",
			"ecpNameCiq");
	private static final String ecpCodeCus = PropertiesUtil.getProperty("/properties/companyInfo.properties",
			"ecpCodeCus");
	private static final String ecpNameCus = PropertiesUtil.getProperty("/properties/companyInfo.properties",
			"ecpNameCus");
	private static final String publicKey = PropertiesUtil.getProperty("/properties/companyInfo.properties",
			"publicKey");
	private static final String companyKey = PropertiesUtil.getProperty("/properties/companyInfo.properties",
			"companyKey");
	// 支付宝
	private static final String partner = PropertiesUtil.getProperty("/properties/alipay.properties", "partner");
	private static final String key = PropertiesUtil.getProperty("/properties/alipay.properties", "key");

	// 海美配置

	public static String tradeDeliverBaseUrl = PropertiesUtil
			.getProperty("/properties/deliverServiceProvider.properties", "tradeDeliverBaseUrl");// "http://testapi.iscs.com.cn/openapi/do";

	public static final String appKey = PropertiesUtil.getProperty("/properties/deliverServiceProvider.properties",
			"appKey");
	public static final String appSecret = PropertiesUtil.getProperty("/properties/deliverServiceProvider.properties",
			"appSecret");
	public static final String ownerId = PropertiesUtil.getProperty("/properties/deliverServiceProvider.properties",
			"ownerId");
	public static final String stockId = PropertiesUtil.getProperty("/properties/deliverServiceProvider.properties",
			"stockId");
	public static final String shopId = PropertiesUtil.getProperty("/properties/deliverServiceProvider.properties",
			"shopId");

	public static ResultData pushTrade(TradePushParamVO param) {
		ResultData result = new ResultData();
		// 初始化企业信息
		HashMap<String, String> companyInfo = initCompanyInfo();
		// 准备订单信息
		String dataInfo = getOrderXml(companyInfo, param);
		logger.info("paramXML: " + dataInfo);
		// RSA加密
		String encryptDataInfo = EncreptAndDecreptUtil.encreptData(dataInfo, publicKey);
		// 32位MD5签名
		String signature = null;
		try {
			signature = RSAUtil.getEncode32(encryptDataInfo);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 生成请求报文
		String xml = generateXml(companyInfo, encryptDataInfo, signature, param.getOptType());
		logger.info("requestXML: " + xml);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xml", xml);
		// 调用webservice服务
		String PostResult = sendHttpPOSTRequest(pushOrderUrl, map);
		try {
			Map<String, String> mapResult = doXMLParse(PostResult);
			// 入库失败
			if (!"0".equals(mapResult.get("Status"))) {
				result.setCode(ResultData.ERROR);
				result.setMsg(JSON.toJSONString(mapResult));
			} else {
				result.setMsg(PostResult);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 查询运单号 @author shenyb 2015年9月15日 下午12:18:46 @Method:
	 * queryLogisticsNoByOrderNo @Description: @param orderNo @return @throws
	 */
	public static ResultData queryLogisticsNoByOrderNo(String orderNo) {
		ResultData result = new ResultData();
		// 生成请求报文
		String xml = generateQueryLogisticsNoXml(orderNo);
		String getResult = sendHttpPOSTRequest(queryLogisticsUrl, xml);
		org.dom4j.Document doc = null;
		try {
			doc = DocumentHelper.parseText(getResult);
		} catch (Exception e) {
			logger.error(String.format("orderNo:%s,parse xml error:getResult:%s", orderNo, getResult));
			result.setCode(ResultData.ERROR);
			result.setMsg("parse xml error:" + getResult);
			return result;
		}
		if (doc == null) {
			result.setCode(ResultData.ERROR);
			result.setMsg(String.format("orderNo:%s,解析xml出错", orderNo));
			return result;
		}
		org.dom4j.Element root = doc.getRootElement();// 获取根节点
		String success = "";
		String errorMsg = "";
		String logisticsNo = "";
		for (Iterator<?> i = root.elementIterator("pub"); i.hasNext();) {
			org.dom4j.Element ele = (org.dom4j.Element) i.next();
			success = ele.elementText("success");
			errorMsg = ele.elementText("errorMsg");
		}
		if ("0".equals(success)) {
			// 获取运单号
			try {
				logisticsNo = root.element("orders").element("order").element("logisticsNo").getText();
				result.setData(logisticsNo);
			} catch (Exception e) {
				logger.error(String.format("queryLogisticsNoByOrderNo err:orderNo:%s,result:%s", orderNo,
						JSON.toJSONString(result)));
				result.setCode(ResultData.ERROR);
				result.setMsg(errorMsg);
				return result;
			}
		} else {
			result.setMsg(errorMsg);
		}
		return result;
	}

	private static String generateQueryLogisticsNoXml(String orderNo) {
		String requestTime = DateUtil.formatDateToStr(Calendar.getInstance().getTime());
		StringBuffer xmlSb = new StringBuffer();
		xmlSb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xmlSb.append("<root>\n");
		xmlSb.append("	<pub>\n");
		xmlSb.append("			<companyCode>" + companyCode + "</companyCode>\n");
		xmlSb.append("			<companyKey>" + companyKey + "</companyKey>\n");
		xmlSb.append("			<requestTime>" + requestTime + "</requestTime>\n");
		xmlSb.append("	</pub>\n");
		xmlSb.append("	<orders>\n");
		xmlSb.append("	<order>\n");
		xmlSb.append("			<ecpCode>" + companyCode + "</ecpCode>\n");
		xmlSb.append("			<orderNo>" + orderNo + "</orderNo>\n");
		xmlSb.append("	</order>\n");
		xmlSb.append("	</orders>\n");
		xmlSb.append("</root>\n");
		return xmlSb.toString();
	}

	/**
	 * 获取清关状态 @author shenyb 2015年9月15日 下午12:18:20 @Method:
	 * getRetrieveResult @Description: @param orderNo @param
	 * waybillNo @return @throws
	 */
	public static ResultData getRetrieveResult(String orderNo, String waybillNo) {
		ResultData result = new ResultData();
		Map<String, String> param = new HashMap<String, String>();
		param.put("requestTime", DateUtil.formatDateToStr(Calendar.getInstance().getTime()));
		param.put("orderNo", orderNo);
		param.put("waybillNo", waybillNo);

		// 生成请求报文
		String xml = generateRetrieveXml(param);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xml", xml);
		// 调用webservice服务
		String postResult = sendHttpPOSTRequest(retrieveInfoUrl, map);
		org.dom4j.Document doc = null;
		try {
			doc = DocumentHelper.parseText(postResult);
		} catch (Exception e) {
			logger.error(String.format("parse RequestResult error :postResult:%s", postResult));
			result.setCode(ResultData.ERROR);
			result.setMsg("parse RequestResult error :postResult:" + postResult);
			return result;
		}
		if (doc == null) {
			result.setCode(ResultData.ERROR);
			result.setMsg("parse RequestResult error ");
			return result;
		}
		org.dom4j.Element root = doc.getRootElement();// 获取根节点
		String returnStatus = "";// 接口请求状态 0，失败1成功
		String status = "";// 清关状态
		try {
			returnStatus = root.element("pub").elementText("status");
			status = root.element("orders").element("order").element("out").elementText("status");
		} catch (Exception e) {
			result.setCode(ResultData.ERROR);
			result.setMsg(JSON.toJSONString(postResult));
			return result;
		}
		if (!"1".equals(returnStatus)) {
			result.setCode(ResultData.ERROR);
		}
		result.setMsg(JSON.toJSONString(postResult));
		result.setData(status);
		return result;
	}

	/**
	 * 
	 * @author shenyb 2015年9月21日 下午5:35:50 @Method:
	 * alipayDeclaration @Description: @param orderNo 订单号 @param outpayNo 支付宝
	 * 支付流水号 @param outRequestNo 报关流水号 @param amount 金额 @return @throws
	 * Exception @throws
	 */
	public static ResultData alipayDeclaration(String orderNo, String outpayNo, String outRequestNo, String amount,String customsPlace) {
		ResultData result = new ResultData();
		SortedMap<String, String> param = new TreeMap<String, String>(); // 支付流水号
		param.put("service", "alipay.acquire.customs");
		param.put("partner", partner);
		param.put("_input_charset", AlipayConfig.input_charset);
		param.put("sign_type", AlipayConfig.sign_type);
		param.put("out_request_no", outRequestNo);// 报关流水号
		param.put("trade_no", outpayNo);
		param.put("merchant_customs_code", companyCode);// 电商平台代码
		param.put("amount", amount);
		param.put("customs_place", customsPlace);
		param.put("merchant_customs_name", "稻田（北京）科技有限公司");
		String content = AlipayCore.createLinkString(param);
		String sign = MD5.sign(content, key, AlipayConfig.input_charset);
		param.put("sign", sign);
		String postResult = "";
		String paramString = JSON.toJSONString(param);
		try {
			logger.info("into alipayDeclaration : param : " + paramString);
			postResult = AlipaySubmit.buildRequest("", "", param);
		} catch (Exception e1) {
			logger.error("error: param: " + paramString);
			e1.printStackTrace();
		}
		org.dom4j.Document doc = null;
		try {
			doc = DocumentHelper.parseText(postResult);
		} catch (Exception e) {
			logger.error(String.format("alipayDeclaration parse RequestResult error,trade_no:%s :postResult:%s",
					orderNo, postResult));
			result.setCode(ResultData.ERROR);
			result.setMsg("parse RequestResult error :postResult:" + postResult);
			return result;
		}
		if (doc == null) {
			result.setCode(ResultData.ERROR);
			result.setMsg("parse RequestResult error:" + postResult);
			return result;
		}
		org.dom4j.Element root = doc.getRootElement();// 获取根节点
		String returnResult = root.elementText("is_success");
		if ("T".equals(returnResult)) {
			result.setData(Boolean.TRUE.toString().toUpperCase());
		} else {
			result.setCode(ResultData.ERROR);
			result.setData(Boolean.FALSE.toString().toUpperCase());
		}
		result.setMsg(postResult);
		return result;
	}

	public static CreateDeliverTradeResult createDeliverTrade(CreateDeliverTradeParamVO param) {
		// 订单参数信息
		String dataInfo = JSON.toJSONString(param);
		HashMap<String, String> map = new HashMap<String, String>();
		String timeStamp = DateUtil.formatDateToStr(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss");
		String content = appKey+appSecret+timeStamp;
		String appSign=AuthUtil.getMD5(content);
		map.put("v_data", dataInfo);
//		map.put("v_appkey", appKey);
//		map.put("v_appsign", appSign);
//		map.put("v_method", "pushTrades");
//		map.put("v_timestamp", timeStamp);
//		map.put("v_format", "json");
		String params="";
		params = "v_appkey="+appKey+"&v_appsign="+appSign+"&v_method=pushTrades"+"&v_timestamp="+timeStamp;
		String postResultTmp = sendHttpPOSTRequest(tradeDeliverBaseUrl,params);
		String postResult = sendHttpPOSTRequest(tradeDeliverBaseUrl, map);
		try {
		String res=	FileUtil.doPost(tradeDeliverBaseUrl+"?"+params, map, "UTF-8", 15000, 60000);
		System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("createDeliverTrade.result:" + postResult);
		CreateDeliverTradeResult result = JSONObject.parseObject(postResult, CreateDeliverTradeResult.class);
		return result;
	}
	public static String buildQuery(Map<String, String> params, String charset)
			throws Exception {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuilder query = new StringBuilder();
		for (String name : params.keySet()) {
			String value = params.get(name);
			if (!StringUtil.isBlank(name) && !StringUtil.isBlank(value)) {
				if (query.length() > 0) {
					query.append("&");
				}

				query.append(name).append("=").append(
						URLEncoder.encode(value, charset));
			}
		}

		return query.toString();
	}

	/**
	 * 
	 * @author shenyb 2015年10月27日 下午2:19:22 @Method:
	 * tradeDeliverStatusQuery @Description: TODO @param orderNo @return @throws
	 */
	public static QueryDeliverTradeStatusResult tradeDeliverStatusQuery(String orderNo) {
		QueryTradeDeliverStatusParamVO param = new QueryTradeDeliverStatusParamVO();
		Map<String, String> map = new HashMap<String, String>();
		param.setOrderNo(orderNo);
		param.setShopId(shopId);
		param.setStockId(stockId);
		map.put("v_data", JSON.toJSONString(param));
		// 调用webservice服务
		String postResult = sendHttpPOSTRequest(tradeDeliverBaseUrl, map);
		QueryDeliverTradeStatusResult reuslt = JSONObject.parseObject(postResult, QueryDeliverTradeStatusResult.class);
		return reuslt;
	}

	/**
	 * 由于使用的是rest风格的webservice。所以所有的webservice，其客户端是通用的。
	 * 只要能发送http请求即可作为webservice的客户端。
	 * 
	 * @param httpUrl
	 *            客户端请求的URL地址
	 * @param params
	 *            客户端需要传递的post参数
	 * @return
	 */
	public static String sendHttpPOSTRequest(String httpUrl, String xml) {
		String result = null;
		DefaultHttpClient httpClient = new DefaultHttpClient();// 创建httpClient对象
		try {
			HttpPost httpPost = new HttpPost(httpUrl);
			if (null != xml) {
				StringEntity FormEntity = new StringEntity(xml);
				httpPost.setEntity(FormEntity);
			}
			HttpResponse response = httpClient.execute(httpPost);// 获得response对象
			int resStatu = response.getStatusLine().getStatusCode();// 返回码
			if (resStatu == HttpStatus.SC_OK) {// 200正常 其它就不合错误
				HttpEntity entity = response.getEntity(); // 获得响应实体
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");// 获得http相应body
				}
			} else {
				result = String.valueOf(resStatu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

	/**
	 * 准备企业信息
	 * 
	 * @return
	 */
	private static HashMap<String, String> initCompanyInfo() {
		// 准备企业信息
		HashMap<String, String> companyInfo = new HashMap<String, String>();
		// 企业编号,E贸易中注册的编号
		companyInfo.put("companyCode", companyCode);
		// 企业名称,E贸易中注册的名称
		companyInfo.put("companyName", companyName);
		// 国检备案的企业编码
		companyInfo.put("ecCode", ecCode);
		// 国检备案的企业名称
		companyInfo.put("ecName", ecName);
		// 海关备案的企业编码
		companyInfo.put("cbeCode", cbeCode);
		// 海关备案的企业名称
		companyInfo.put("cbeName", cbeName);
		// 电商平台国检备案编码
		companyInfo.put("ecpCodeCiq", ecpCodeCiq);
		// 电商平台国检备案名称
		companyInfo.put("ecpNameCiq", ecpNameCiq);
		// 电商平台海关备案编码
		companyInfo.put("ecpCodeCus", ecpCodeCus);
		// 电商平台海关备案名称
		companyInfo.put("ecpNameCus", ecpNameCus);
		// 企业公钥串,由E贸易提供
		companyInfo.put("publicKey", publicKey);
		// 企业key，由E贸易提供
		companyInfo.put("companyKey", companyKey);
		return companyInfo;
	}

	/**
	 * 生成请求报文数据
	 * 
	 * @param encryptDataInfo
	 * @return
	 */
	private static String generateXml(HashMap<String, String> companyInfo, String encryptDataInfo, String signature,
			String optType) {
		StringBuffer xmlSb = new StringBuffer();
		xmlSb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xmlSb.append("<Root>\n");
		xmlSb.append("	<PubInfo>\n");
		xmlSb.append("			<Version>1.0</Version>\n");
		xmlSb.append("			<CompanyCode>").append(companyInfo.get("companyCode")).append("</CompanyCode>\n");
		xmlSb.append("			<Key>").append(companyInfo.get("companyKey")).append("</Key>\n");
		xmlSb.append("			<MsgType>O</MsgType>\n");
		xmlSb.append("			<OptType>").append(optType).append("</OptType>\n");
		xmlSb.append("			<Signature>").append(signature).append("</Signature>\n");
		String currentTime = DateUtil.formatDateToStr(Calendar.getInstance().getTime());
		xmlSb.append("			<CreatTime>" + currentTime + "</CreatTime>\n");
		xmlSb.append("	</PubInfo>\n");
		xmlSb.append("	<DataInfo>").append(encryptDataInfo).append("</DataInfo>\n");
		xmlSb.append("</Root>\n");
		return xmlSb.toString();
	}

	/**
	 * 清关，生成请求报文数据
	 * 
	 * @param encryptDataInfo
	 * @return
	 */
	private static String generateRetrieveXml(Map<String, String> param) {
		String requestTime = param.get("requestTime");
		String orderNo = param.get("orderNo");
		String waybillNo = param.get("waybillNo");
		StringBuffer xmlSb = new StringBuffer();
		xmlSb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		xmlSb.append("<root>\n");
		xmlSb.append("	<pub>\n");
		xmlSb.append("			<requestTime>" + requestTime + "</requestTime>\n");
		xmlSb.append("			<cbeCode>" + cbeCode + "</cbeCode>\n");
		xmlSb.append("			<type>" + 1 + "</type>\n");
		xmlSb.append("	</pub>\n");
		xmlSb.append("	<orders>\n");
		xmlSb.append("	<order>\n");
		xmlSb.append("			<orderNo/>\n");
		xmlSb.append("			<waybillNo>" + waybillNo + "</waybillNo>\n");
		xmlSb.append("	</order>\n");
		xmlSb.append("	<order>\n");
		xmlSb.append("			<orderNo>" + orderNo + "</orderNo>\n");
		xmlSb.append("			<waybillNo/>\n");
		xmlSb.append("	</order>\n");
		xmlSb.append("	</orders>\n");
		xmlSb.append("</root>\n");
		return xmlSb.toString();
	}

	/**
	 * 获取发货状态，生成请求报文数据
	 * 
	 * @param encryptDataInfo
	 * @return
	 */
	private static String generateDeliverXml(Map<String, String> param) {
		String stockId = param.get("srockId");
		String shopId = param.get("shopId");
		String orderNo = param.get("order_no");
		StringBuffer xmlSb = new StringBuffer();
		xmlSb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xmlSb.append("<root>\n");
		xmlSb.append("	<tradeDeliverQuery>\n");
		xmlSb.append("			<stock_id>" + stockId + "</stock_id>\n");
		xmlSb.append("			<shop_id>" + shopId + "</shop_id>\n");
		xmlSb.append("			<order_no>" + orderNo + "</order_no>\n");
		xmlSb.append("	</tradeDeliverQuery>\n");
		xmlSb.append("</root>\n");
		return xmlSb.toString();
	}

	public static String sendHttpPOSTRequest(String httpUrl, Map<String, String> params) {
		String result = null;
		DefaultHttpClient httpClient = new DefaultHttpClient();// 创建httpClient对象
		try {
			HttpPost httpPost = new HttpPost(httpUrl);
			List<NameValuePair> httpParams = new ArrayList<NameValuePair>();
			if (null != params) {
				Set<String> keys = params.keySet();
				for (String key : keys) {
					String value = params.get(key);
					httpParams.add(new BasicNameValuePair(key, value));
				}
				UrlEncodedFormEntity FormEntity = new UrlEncodedFormEntity(httpParams, "utf-8");
				httpPost.setEntity(FormEntity);

			}

			HttpResponse response = httpClient.execute(httpPost);// 获得response对象
			int resStatu = response.getStatusLine().getStatusCode();// 返回码
			// System.out.println(resStatu);
			if (resStatu == HttpStatus.SC_OK) {// 200正常 其它就不合错误
				System.out.println("200");
				HttpEntity entity = response.getEntity(); // 获得响应实体
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");// 获得http相应body
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

	private static String getOrderXml(HashMap<String, String> companyInfo, TradePushParamVO param) {
		StringBuffer orderXmlSb = new StringBuffer();
		orderXmlSb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		orderXmlSb.append("<Order>\n");
		orderXmlSb.append("<OrderHead>\n");
		orderXmlSb.append("<ecCode>" + ecCode + "</ecCode>\n");
		orderXmlSb.append("<cbeCode>" + cbeCode + "</cbeCode>\n");
		orderXmlSb.append("<ecName>" + ecName + "</ecName>\n");
		orderXmlSb.append("<cbeName>" + cbeName + "</cbeName>\n");
		orderXmlSb.append("<ecpCodeCiq>" + ecpCodeCiq + "</ecpCodeCiq>\n");
		// 找金山已确认过
		orderXmlSb.append("<ecpCodeCus>" + companyCode + "</ecpCodeCus>\n");
		orderXmlSb.append("<ecpNameCiq>" + ecpNameCiq + "</ecpNameCiq>\n");
		orderXmlSb.append("<ecpNameCus>" + ecpNameCus + "</ecpNameCus>\n");
		orderXmlSb.append("<orderNo>" + param.getOrderNo() + "</orderNo>\n");
		orderXmlSb.append("<charge>" + CurrencyUtil.fromFenToYuan(param.getGoodsValue()) + "</charge>\n");
		orderXmlSb.append("<goodsValue>" + CurrencyUtil.fromFenToYuan(param.getGoodsValue()) + "</goodsValue>\n");
		orderXmlSb.append("<freight></freight>\n");
		orderXmlSb.append("<other></other>\n");
		orderXmlSb.append("<tax></tax>\n");
		orderXmlSb.append("<customer></customer>\n");
		orderXmlSb.append("<shipper>稻田（北京）科技有限公司</shipper>\n");
		orderXmlSb.append("<shipperAddress>河南省郑州市经开区航海东路1508号河南保税物流中心</shipperAddress>\n");
		orderXmlSb.append("<shipperTelephone>400-026-6655</shipperTelephone>\n");
		orderXmlSb.append("<shipperCountryCiq>156</shipperCountryCiq>\n");
		orderXmlSb.append("<shipperCountryCus>142</shipperCountryCus>\n");
		orderXmlSb.append("<consignee>" + param.getConsignee() + "</consignee>\n");
		orderXmlSb.append("<consigneeProvince>" + param.getConsigneeProvince() + "</consigneeProvince>\n");
		orderXmlSb.append("<consigneeCity>" + param.getConsigneeCity() + "</consigneeCity>\n");
		orderXmlSb.append("<consigneeZone>" + param.getConsigneeZone() + "</consigneeZone>\n");
		orderXmlSb.append("<consigneeAddress>" + param.getConsigneeAddress() + "</consigneeAddress>\n");
		orderXmlSb.append("<consigneeTelephone>" + param.getConsigneeTelephone() + "</consigneeTelephone>\n");
		orderXmlSb.append("<consigneeCountryCiq>156</consigneeCountryCiq>\n");
		orderXmlSb.append("<consigneeCountryCus>142</consigneeCountryCus>\n");
		orderXmlSb.append("<idType>1</idType>\n");
		orderXmlSb.append("<idNumber>" + param.getIdNumber() + "</idNumber>\n");
		orderXmlSb.append("<ieType>I</ieType>\n");
		orderXmlSb.append("<stockFlag>2</stockFlag>\n");
		// 批次号
		orderXmlSb.append("<batchNumbers>" + param.getBatchNo() + "</batchNumbers>\n");
		// 进口集货必填，我们是进口备货
		orderXmlSb.append("<totalLogisticsNo>35656458412</totalLogisticsNo>\n");
		// 进口时，发货人国家,咱们是出口，写中国
		orderXmlSb.append("<tradeCountryCiq>156</tradeCountryCiq>\n");
		orderXmlSb.append("<tradeCountryCus>142</tradeCountryCus>\n");
		orderXmlSb.append("<agentCodeCiq>4100300119</agentCodeCiq>\n");
		orderXmlSb.append("<agentCodeCus>4101983436</agentCodeCus>\n");
		orderXmlSb.append("<agentNameCiq>河南中启保税报关有限公司</agentNameCiq>\n");
		orderXmlSb.append("<agentNameCus>河南中启保税报关有限公司</agentNameCus>\n");
		orderXmlSb.append("<packageTypeCiq>4M</packageTypeCiq>\n");
		orderXmlSb.append("<packageTypeCus>2</packageTypeCus>\n");
		orderXmlSb.append("<modifyMark>1</modifyMark>\n");
		orderXmlSb.append("<note></note>\n");
		orderXmlSb.append("</OrderHead>\n");
		orderXmlSb.append("<OrderList>\n");
		orderXmlSb.append("<itemNoCiq></itemNoCiq>\n");
		orderXmlSb.append("<itemNoCus></itemNoCus>\n");
		orderXmlSb.append("<goodsNo>" + param.getGoodsNo() + "</goodsNo>\n");
		orderXmlSb.append("<shelfGoodsName>" + param.getShelfGoodsName() + "</shelfGoodsName>\n");
		orderXmlSb.append("<goodsName></goodsName>\n");
		orderXmlSb.append("<describe></describe>\n");
		orderXmlSb.append("<codeTs></codeTs>\n");
		orderXmlSb.append("<ciqCode></ciqCode>\n");
		orderXmlSb.append("<goodsModel></goodsModel>\n");
		orderXmlSb.append("<taxCode></taxCode>\n");
		orderXmlSb.append("<price>" + CurrencyUtil.fromFenToYuan(param.getPrice()) + "</price>\n");
		orderXmlSb.append("<currencyCiq>156</currencyCiq>\n");
		orderXmlSb.append("<currencyCus>142</currencyCus>\n");
		orderXmlSb.append("<quantity>" + param.getQuantity() + "</quantity>\n");
		orderXmlSb.append("<priceTotal>" + CurrencyUtil.fromFenToYuan(param.getPriceTotal()) + "</priceTotal>\n");
		// 计量单位，采购提供
		orderXmlSb.append("<unitCiq>" + param.getUnitCiq() + "</unitCiq>\n");
		orderXmlSb.append("<unitCus>" + param.getUnitCus() + "</unitCus>\n");
		orderXmlSb.append("<discount></discount>\n");
		orderXmlSb.append("<giftsFlag></giftsFlag>\n");
		// 原产国，采购提供
		orderXmlSb.append("<originCountryCiq>" + param.getOriginCountryCiq() + "</originCountryCiq>\n");// 美国
		orderXmlSb.append("<originCountryCus>" + param.getOriginCountryCus() + "</originCountryCus>\n");

		orderXmlSb.append("<usage></usage>\n");
		orderXmlSb.append("<wasteMaterials>1</wasteMaterials>\n");
		orderXmlSb.append("<wrapTypeCiq>4M</wrapTypeCiq>\n");
		orderXmlSb.append("<wrapTypeCus>2</wrapTypeCus>\n");
		orderXmlSb.append("<packNum>1</packNum>\n");
		orderXmlSb.append("</OrderList>\n");
		// 第三方支付公司的信息，需要code，name，out_trade_no
		orderXmlSb.append("<OrderPaymentLogistics>\n");
		orderXmlSb.append("<paymentCode>" + param.getPaymentCode() + "</paymentCode>\n");
		orderXmlSb.append("<paymentName>" + param.getPaymentName() + "</paymentName>\n");
		orderXmlSb.append("<paymentType></paymentType>\n");
		orderXmlSb.append("<paymentNo>" + param.getPaymentNo() + "</paymentNo>\n");
		// 物流
		orderXmlSb.append("<logisticsCodeCiq>4100300005</logisticsCodeCiq>\n");
		orderXmlSb.append("<logisticsCodeCus>L0007</logisticsCodeCus>\n");
		orderXmlSb.append("<logisticsNameCiq>河南圆通快递有限公司</logisticsNameCiq>	\n");
		orderXmlSb.append("<logisticsNameCus>河南圆通快递有限公司</logisticsNameCus>\n");
		orderXmlSb.append("<totalLogisticsNo>35656458412</totalLogisticsNo>\n");
		orderXmlSb.append("<subLogisticsNo></subLogisticsNo>\n");
		// 运单号是第二个接口查询到的，所以这里随便写
		orderXmlSb.append("<logisticsNo></logisticsNo>\n");
		orderXmlSb.append("<trackNo></trackNo>\n");
		orderXmlSb.append("<trackStatus></trackStatus>\n");
		orderXmlSb.append("<crossFreight></crossFreight>\n");
		orderXmlSb.append("<supportValue></supportValue>\n");
		// 毛重
		orderXmlSb.append("<weight>" + param.getWeight() + "</weight>\n");
		orderXmlSb.append("<netWeight>" + param.getNetWeight() + "</netWeight>\n");
		orderXmlSb.append("<quantity></quantity>\n");
		orderXmlSb.append("<deliveryWay></deliveryWay>\n");
		orderXmlSb.append("<transportationWay>7</transportationWay>\n");// 保税区
		orderXmlSb.append("<shipCode>39</shipCode>\n");// 陆运集装箱
		orderXmlSb.append("<shipName></shipName>\n");
		orderXmlSb.append("<destinationPort></destinationPort>\n");
		orderXmlSb.append("</OrderPaymentLogistics>\n");
		orderXmlSb.append("</Order>\n");
		return orderXmlSb.toString();
	}

	// private static String getOrderXmlStr(HashMap<String, String> companyInfo)
	// {
	// StringBuffer orderXmlSb = new StringBuffer();
	// orderXmlSb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
	// orderXmlSb.append("<Order>\n");
	// orderXmlSb.append("<OrderHead>\n");
	// orderXmlSb.append("<ecCode>").append(companyInfo.get("ecCode")).append("</ecCode>\n");
	// orderXmlSb.append("<cbeCode>").append(companyInfo.get("cbeCode")).append("</cbeCode>\n");
	// orderXmlSb.append("<ecName>").append(companyInfo.get("ecName")).append("</ecName>\n");
	// orderXmlSb.append("<cbeName>").append(companyInfo.get("cbeName")).append("</cbeName>\n");
	// orderXmlSb.append("<ecpCodeCiq>").append(companyInfo.get("ecpCodeCiq")).append("</ecpCodeCiq>\n");
	// orderXmlSb.append("<ecpCodeCus>").append(companyInfo.get("ecpCodeCus")).append("</ecpCodeCus>\n");
	// orderXmlSb.append("<ecpNameCiq>").append(companyInfo.get("ecpNameCiq")).append("</ecpNameCiq>\n");
	// orderXmlSb.append("<ecpNameCus>").append(companyInfo.get("ecpNameCus")).append("</ecpNameCus>\n");
	// orderXmlSb.append("<orderNo>12345</orderNo>\n");
	// orderXmlSb.append("<charge>253.56</charge>\n");
	// orderXmlSb.append("<goodsValue>123</goodsValue>\n");
	// orderXmlSb.append("<freight>10</freight>\n");
	// orderXmlSb.append("<other></other>\n");
	// orderXmlSb.append("<tax></tax>\n");
	// orderXmlSb.append("<customer></customer>\n");
	// orderXmlSb.append("<shipper>稻田（北京）科技有限公司</shipper>\n");
	// orderXmlSb.append("<shipperAddress>河南省郑州市经开区航海东路1508号河南保税物流中心</shipperAddress>\n");
	// orderXmlSb.append("<shipperTelephone>400-026-6655</shipperTelephone>\n");
	// orderXmlSb.append("<shipperCountryCiq>156</shipperCountryCiq>\n");
	// orderXmlSb.append("<shipperCountryCus>142</shipperCountryCus>\n");
	// orderXmlSb.append("<consignee>范丽萍</consignee>\n收货人姓名");
	// orderXmlSb.append("<consigneeProvince>河南省</consigneeProvince>\n");
	// orderXmlSb.append("<consigneeCity>郑州市</consigneeCity>\n");
	// orderXmlSb.append("<consigneeZone>惠济区</consigneeZone>\n");
	// orderXmlSb.append("<consigneeAddress>河南省郑州市惠济区秦岭路12345号</consigneeAddress>\n");
	// orderXmlSb.append("<consigneeTelephone>15937165081</consigneeTelephone>\n");
	// orderXmlSb.append("<consigneeCountryCiq>156</consigneeCountryCiq>\n");
	// orderXmlSb.append("<consigneeCountryCus>142</consigneeCountryCus>\n");
	// orderXmlSb.append("<idType>1</idType>\n");
	// orderXmlSb.append("<idNumber>312064198609152578</idNumber>\n");
	// orderXmlSb.append("<ieType>I</ieType>\n");
	// orderXmlSb.append("<stockFlag>2</stockFlag>\n");
	// orderXmlSb.append("<batchNumbers>LY20150504001</batchNumbers>\n");
	// orderXmlSb.append("<totalLogisticsNo>35656458412</totalLogisticsNo>\n");
	// orderXmlSb.append("<tradeCountryCiq>156</tradeCountryCiq>\n");
	// orderXmlSb.append("<tradeCountryCus>142</tradeCountryCus>\n");
	// orderXmlSb.append("<agentCodeCiq>4100300119</agentCodeCiq>\n");
	// orderXmlSb.append("<agentCodeCus>4101983436</agentCodeCus>\n");
	// orderXmlSb.append("<agentNameCiq>河南中启保税报关有限公司</agentNameCiq>\n");
	// orderXmlSb.append("<agentNameCus>河南中启保税报关有限公司</agentNameCus>\n");
	// orderXmlSb.append("<packageTypeCiq>4M</packageTypeCiq>\n");
	// orderXmlSb.append("<packageTypeCus>2</packageTypeCus>\n");
	// orderXmlSb.append("<modifyMark>1</modifyMark>\n");
	// orderXmlSb.append("<note></note>\n");
	// orderXmlSb.append("</OrderHead>\n");
	// orderXmlSb.append("<OrderList>\n");
	// orderXmlSb.append("<itemNoCiq></itemNoCiq>\n");
	// orderXmlSb.append("<itemNoCus></itemNoCus>\n");
	// orderXmlSb.append("<goodsNo>HN235645</goodsNo>\n");
	// orderXmlSb.append("<shelfGoodsName>test</shelfGoodsName>\n");
	// orderXmlSb.append("<goodsName></goodsName>\n");
	// orderXmlSb.append("<describe></describe>\n");
	// orderXmlSb.append("<codeTs></codeTs>\n");
	// orderXmlSb.append("<ciqCode></ciqCode>\n");
	// orderXmlSb.append("<goodsModel></goodsModel>\n");
	// orderXmlSb.append("<taxCode></taxCode>\n");
	// orderXmlSb.append("<price></price>\n");
	// orderXmlSb.append("<currencyCiq>156</currencyCiq>\n");
	// orderXmlSb.append("<currencyCus>142</currencyCus>\n");
	// orderXmlSb.append("<quantity>3</quantity>\n");
	// orderXmlSb.append("<priceTotal>243.56</priceTotal>\n");
	// orderXmlSb.append("<unitCiq>122</unitCiq>\n");
	// orderXmlSb.append("<unitCus>122</unitCus>\n");
	// orderXmlSb.append("<discount></discount>\n");
	// orderXmlSb.append("<giftsFlag></giftsFlag>\n");
	// orderXmlSb.append("<originCountryCiq>840</originCountryCiq>\n");
	// orderXmlSb.append("<originCountryCus>502</originCountryCus>\n");
	// orderXmlSb.append("<usage></usage>\n");
	// orderXmlSb.append("<wasteMaterials>1</wasteMaterials>\n");
	// orderXmlSb.append("<wrapTypeCiq>4M</wrapTypeCiq>\n");
	// orderXmlSb.append("<wrapTypeCus>2</wrapTypeCus>\n");
	// orderXmlSb.append("<packNum>1</packNum>\n");
	// orderXmlSb.append("</OrderList>\n");
	// orderXmlSb.append("<OrderPaymentLogistics>\n");
	// orderXmlSb.append("<paymentCode>P0001</paymentCode>\n");
	// orderXmlSb.append("<paymentName>支付宝（中国）网络技术有限公司</paymentName>\n");
	// orderXmlSb.append("<paymentType></paymentType>\n");
	// orderXmlSb.append("<paymentNo>45451254</paymentNo>\n");
	// orderXmlSb.append("<logisticsCodeCiq>4100300069</logisticsCodeCiq>\n");
	// orderXmlSb.append("<logisticsCodeCus>L0004</logisticsCodeCus>\n");
	// orderXmlSb.append("<logisticsNameCiq>河南中通快递服务有限公司</logisticsNameCiq>
	// \n");
	// orderXmlSb.append("<logisticsNameCus>河南中通快递服务有限公司</logisticsNameCus>\n");
	// orderXmlSb.append("<totalLogisticsNo>35656458412</totalLogisticsNo>\n");
	// orderXmlSb.append("<subLogisticsNo></subLogisticsNo>\n");
	// orderXmlSb.append("<logisticsNo></logisticsNo>\n");
	// orderXmlSb.append("<trackNo></trackNo>\n");
	// orderXmlSb.append("<trackStatus></trackStatus>\n");
	// orderXmlSb.append("<crossFreight></crossFreight>\n");
	// orderXmlSb.append("<supportValue></supportValue>\n");
	// orderXmlSb.append("<weight>906</weight>\n");
	// orderXmlSb.append("<netWeight></netWeight>\n");
	// orderXmlSb.append("<quantity>3</quantity>\n");
	// orderXmlSb.append("<deliveryWay></deliveryWay>\n");
	// orderXmlSb.append("<transportationWay>4</transportationWay>\n");
	// orderXmlSb.append("<shipCode>32</shipCode>\n");
	// orderXmlSb.append("<shipName>汽车</shipName>\n");
	// orderXmlSb.append("<destinationPort></destinationPort>\n");
	// orderXmlSb.append("</OrderPaymentLogistics>\n");
	// orderXmlSb.append("</Order>\n");
	// return orderXmlSb.toString();
	// }

	public static Map<String, String> doXMLParse(String strxml) throws JDOMException, IOException {
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

	private static String getChildrenText(List<?> children) {
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

	// public static void main(String[] args) throws Exception {
	// // 初始化企业信息
	// HashMap<String, String> companyInfo = initCompanyInfo();
	// // 准备订单信息
	// String dataInfo = getOrderXmlStr(companyInfo);
	// System.out.println("======订单数据:======\n" + dataInfo);
	// // RSA加密
	// String encryptDataInfo = EncreptAndDecreptUtil.encreptData(dataInfo,
	// companyInfo.get("publicKey"));
	// System.out.println("======RSA加密后:======\n" + encryptDataInfo);
	// // 32位MD5签名
	// String signature = RSAUtil.getEncode32(encryptDataInfo);
	// System.out.println("======签名信息:======\n" + signature);
	// // 生成请求报文
	// String xml = generateXml(companyInfo, encryptDataInfo, signature);
	// System.out.println("======最终报文数据:======\n" + xml);
	// HashMap<String, String> map = new HashMap<String, String>();
	// map.put("xml", xml);
	// // 调用webservice服务
	// String PostResult =
	// sendHttpPOSTRequest("http://218.28.185.212:9092/BIService/service/order/pushOrder",
	// map);
	// // 查看响应回执
	// System.out.println("======报文回执:======\n" + PostResult);
	// try {
	// Map<String, String> result = doXMLParse(PostResult);
	// // for(String key:result.keySet()){
	// // System.out.println("key:"+key+",val:"+result.get(key));
	// // }
	// System.out.println("result status:" + result.get("Status"));
	// } catch (JDOMException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	  public static void readContentFromPost(String url,String param) throws IOException { 
	        // Post请求的url，与get不同的是不需要带参数  
	        URL postUrl = new URL(url);  
	        // 打开连接  
	        HttpURLConnection connection = (HttpURLConnection) postUrl  
	                .openConnection();  
	        // Output to the connection. Default is  
	        // false, set to true because post  
	        // method must write something to the  
	        // connection  
	        // 设置是否向connection输出，因为这个是post请求，参数要放在  
	        // http正文内，因此需要设为true  
	        connection.setDoOutput(true);  
	        // Read from the connection. Default is true.  
	        connection.setDoInput(true);  
	        // Set the post method. Default is GET  
	        connection.setRequestMethod("POST");  
	        // Post cannot use caches  
	        // Post 请求不能使用缓存  
	        connection.setUseCaches(false);  
	        // This method takes effects to  
	        // every instances of this class.  
	        // URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。  
	        // connection.setFollowRedirects(true);  
	  
	        // This methods only  
	        // takes effacts to this  
	        // instance.  
	        // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数  
	        connection.setInstanceFollowRedirects(true);  
	        // Set the content type to urlencoded,  
	        // because we will write  
	        // some URL-encoded content to the  
	        // connection. Settings above must be set before connect!  
	        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的  
	        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode  
	        // 进行编码  
	        connection.setRequestProperty("Content-Type",  
	                "application/x-www-form-urlencoded");  
	        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，  
	        // 要注意的是connection.getOutputStream会隐含的进行connect。  
	        connection.connect();  
	        DataOutputStream out = new DataOutputStream(connection  
	                .getOutputStream());  
	        // The URL-encoded contend  
	        // 正文，正文内容其实跟get的URL中'?'后的参数字符串一致  
	        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面  
	        out.writeBytes(param);   
	  
	        out.flush();  
	        out.close(); // flush and close  
	        BufferedReader reader = new BufferedReader(new InputStreamReader(  
	                connection.getInputStream()));  
	        String line;  
	        System.out.println("=============================");  
	        System.out.println("Contents of post request");  
	        System.out.println("=============================");  
	        while ((line = reader.readLine()) != null) {  
	            System.out.println(line);  
	        }  
	        System.out.println("=============================");  
	        System.out.println("Contents of post request ends");  
	        System.out.println("=============================");  
	        reader.close();  
	        connection.disconnect();  
	    }  
}
