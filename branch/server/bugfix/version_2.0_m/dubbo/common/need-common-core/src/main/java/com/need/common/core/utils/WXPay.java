package com.need.common.core.utils;

import com.alibaba.fastjson.JSONArray;
import com.need.utils.MD5Util;
import com.need.utils.PropertiesUtil;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;

/**
 * 
 * <p></p>
 * 
 * @author shenyb 2015年8月20日 上午1:08:08
 * @ClassName WXPay
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年8月20日
 * @modify by reason:{方法名}:{原因}
 */
public class WXPay {

	private static final Logger logger = Logger.getLogger(WXPay.class);
	
	private String requestUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private String queryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
	private String notifyUrl = PropertiesUtil.getProperty("/properties/wechat.properties", "notify_url");
	private String appId = PropertiesUtil.getProperty("/properties/wechat.properties", "app_id");
	private String mchId = PropertiesUtil.getProperty("/properties/wechat.properties", "partner");
	private String partnerKey = PropertiesUtil.getProperty("/properties/wechat.properties", "private_key");

	//公众号信息 
	private String wapAppId = PropertiesUtil.getProperty("/properties/wechat.properties", "wap_app_id");
	private String wapMchId = PropertiesUtil.getProperty("/properties/wechat.properties", "wap_partner_id");
	private String wapPrivateKey = PropertiesUtil.getProperty("/properties/wechat.properties", "wap_private_key");
	private String wapNotifyUrl = PropertiesUtil.getProperty("/properties/wechat.properties", "wap_notify_url");
	
	/**
	 * 支付 返回的字符串 可以直接交给客户端进行支付
	 *
	 * @param title
	 *            支付标题
	 * @param price
	 *            支付价格(分)
	 * @param orderId
	 *            内部订单ID
	 * @param ip
	 *            ip
	 * @return
	 */
	public String pay(String title, int price, String orderId, String ip) {
		if (price < 0) {
			return null;
		}
		HttpsURLConnection connection;
		try {
			connection = getHttpsURLConnection(requestUrl);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.connect();
			OutputStream outputStream = connection.getOutputStream();
			byte[] data = buildPostData(title, price, orderId, ip).getBytes();
			if (data != null) {
				outputStream.write(data);
			}
			InputStream inputStream = connection.getInputStream();
			byte[] bytes = IOUtils.toByteArray(inputStream);
			String xmlResult = new String(bytes);
			System.out.println("result:"+xmlResult);
			return buildRes(doXMLParse(xmlResult));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return null;
	}

	public  Map<Object, Object> payByJSAPI(String title, int price, String orderId, String ip,String openId,String tradeType) {
		if (price < 0) {
			return null;
		}
		HttpsURLConnection connection;
		try {
			connection = getHttpsURLConnection(requestUrl);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.connect();
			OutputStream outputStream = connection.getOutputStream();
			byte[] data = buildPostDataByWAP(title, price, orderId, ip,openId,tradeType).getBytes();
			if (data != null) {
				outputStream.write(data);
			}
			InputStream inputStream = connection.getInputStream();
			byte[] bytes = IOUtils.toByteArray(inputStream);
			String xmlResult = new String(bytes);
			logger.info("payByJSAPI result: "+xmlResult);
			return buildResForWap(doXMLParse(xmlResult));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 接到订单支付成功通知后,进行订单的真实性校验
	 * TODO isWXPay
	 *
	 * @param order
	 *            订单数据对象
	 * @param transactionId
	 *            微信的流水号
	 * @return
	 */
	public boolean orderQuery(Order order, String transactionId) {
		HttpsURLConnection connection;
		try {
			connection = getHttpsURLConnection(queryUrl);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.connect();
			OutputStream outputStream = connection.getOutputStream();
			byte[] data = buildQueryData(order, transactionId).getBytes();
			if (data != null) {
				outputStream.write(data);
			}
			InputStream inputStream = connection.getInputStream();
			byte[] bytes = IOUtils.toByteArray(inputStream);
			String xmlResult = new String(bytes);
			Map<String, String> map = doXMLParse(xmlResult);
			int totalPrice = Integer.parseInt(String.valueOf(map.get("total_fee")));

			int tp = order.getPrice();
			if (tp == totalPrice) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author Rylan 2015年12月11日 上午1:38:57
	 * @Method: orderQueryForWap 
	 * @Description: 订单校验
	 * @param order
	 * @param transactionId
	 * @return 
	 * @throws
	 */
	public boolean orderQueryForWap(Order order, String transactionId) {
		logger.debug("orderQueryForWap in ..");
		HttpsURLConnection connection;
		try {
			connection = getHttpsURLConnection(queryUrl);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.connect();
			OutputStream outputStream = connection.getOutputStream();
			byte[] data = buildQueryDataForWap(order, transactionId).getBytes();
			if (data != null) {
				outputStream.write(data);
			}
			InputStream inputStream = connection.getInputStream();
			byte[] bytes = IOUtils.toByteArray(inputStream);
			String xmlResult = new String(bytes);
			logger.debug("xmlResult :"+xmlResult);
			Map<String, String> map = doXMLParse(xmlResult);
			int totalPrice = Integer.parseInt(String.valueOf(map.get("total_fee")));

			int tp = order.getPrice();
			if (tp == totalPrice) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static class Order {
		private String id;
		private int price;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public Order(String id, int price) {
			super();
			this.id = id;
			this.price = price;
		}
		public Order(){
		}
		
	}

	private HttpsURLConnection getHttpsURLConnection(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
		return httpsURLConnection;
	}

	private String buildPostData(String title, int price, String orderId, String ip) {
		SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>();
		sortedMap.put("appid", appId);
		sortedMap.put("mch_id", mchId);
		sortedMap.put("nonce_str", getNonceStr());
		sortedMap.put("body", title);
		sortedMap.put("out_trade_no", orderId);
		sortedMap.put("total_fee", price);
		sortedMap.put("spbill_create_ip", ip);
		sortedMap.put("notify_url", notifyUrl);
		sortedMap.put("trade_type", "APP");
		String sign = createSign("UTF-8", sortedMap);
		sortedMap.put("sign", sign);
		return mapToXml(sortedMap);
	}

	private String buildPostDataByWAP(String title, int price, String orderId, String ip,String openId,String tradeType) {
		SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>();
		sortedMap.put("appid", wapAppId);
		sortedMap.put("body", title);
		sortedMap.put("mch_id", wapMchId);
		sortedMap.put("nonce_str", getNonceStr());
		
		sortedMap.put("notify_url", wapNotifyUrl);
		sortedMap.put("out_trade_no", orderId);
		if(openId!=null){
			sortedMap.put("openid", openId);
		}		
		sortedMap.put("spbill_create_ip", ip);
		sortedMap.put("total_fee", price);	
		sortedMap.put("trade_type", tradeType);
		String sign = createSignForWAP("UTF-8", sortedMap);
		sortedMap.put("sign", sign);
		logger.info("mapToXml(sortedMap) :"+mapToXml(sortedMap));
		return mapToXml(sortedMap);
	}
	
	private String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}

	private String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
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
		String sign = MD5Util.MD5Encode(sb.toString(), charSet).toUpperCase();
		return sign;
	}
	
	
	private String createSignForWAP(String charSet, SortedMap<Object, Object> parameters) {
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
		sb.append("key=" + wapPrivateKey);
		String sign = MD5Util.MD5Encode(sb.toString(), charSet).toUpperCase();
		return sign;
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
		return sb.toString();
	}

	

	private String buildRes(Map<?, ?> resultMap) {
		SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>();
		sortedMap.put("appid", resultMap.get("appid"));
		sortedMap.put("partnerid", resultMap.get("mch_id"));
		sortedMap.put("prepayid", resultMap.get("prepay_id"));
		sortedMap.put("package", "Sign=WXPay");
		sortedMap.put("noncestr", getNonceStr());
		sortedMap.put("timestamp", getTimeStamp());
		String sign = createSign("UTF-8", sortedMap);
		sortedMap.put("sign", sign);
		return JSONArray.toJSONString(sortedMap);
		// return mapToXml(sortedMap);
	}
	
	private SortedMap<Object, Object> buildResForWap(Map<?, ?> resultMap) {
		SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>();		
		sortedMap.put("appId", resultMap.get("appid"));
		sortedMap.put("timeStamp", getTimeStamp());
		sortedMap.put("nonceStr", getNonceStr());
		sortedMap.put("package", "prepay_id="+resultMap.get("prepay_id"));
		sortedMap.put("signType", "MD5");		
		String sign = createSignForWAP("UTF-8", sortedMap);
		sortedMap.put("paySign", sign);
		return sortedMap;
		// return mapToXml(sortedMap);
	}
	

	private String buildQueryData(Order order, String transactionId) {
		SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>();
		sortedMap.put("appid", appId);
		sortedMap.put("mch_id", mchId);
		sortedMap.put("nonce_str", getNonceStr());
		sortedMap.put("transaction_id", transactionId);
		sortedMap.put("out_trade_no", order.getId());
		String sign = createSign("UTF-8", sortedMap);
		sortedMap.put("sign", sign);
		return mapToXml(sortedMap);
	}
	
	private String buildQueryDataForWap(Order order, String transactionId) {
		SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>();
		sortedMap.put("appid", wapAppId);
		sortedMap.put("mch_id", wapMchId);
		sortedMap.put("nonce_str", getNonceStr());
		sortedMap.put("transaction_id", transactionId);
		sortedMap.put("out_trade_no", order.getId());
		String sign = createSignForWAP("UTF-8", sortedMap);
		sortedMap.put("sign", sign);
		return mapToXml(sortedMap);
	}

	private Map<String, String> doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        logger.debug("doXMLParse.strxml :"+strxml);
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
	
	public static void main(String[] args) {
		System.out.println(MD5Util.MD5Encode("a147eb89d460c12ad7925").toUpperCase());
	}
	
}

