package com.need.integration.common.birdex;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.need.integration.common.birdex.bean.Order;
/* *
 *类名：Birdex
 *功能：Birdex相关功能
 *详细：处理Birdex数据
 *版本：3.3
 *日期：2015-10-22
 *说明：
 */
public class Birdex {
	
	public static String create(String url, Order order,String sign) throws IOException{ 
		
        return sendPostRequest(url, order, sign, "utf-8");
	}
	
	private static String sendPostRequest(String reqURL, Order params, String sign, String encodeCharset) throws IOException {
		String reseContent = "通信失败";
		if (encodeCharset == null || encodeCharset.equals("")) {
			encodeCharset = "utf-8";
		}
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
		HttpPost httpPost = new HttpPost(reqURL);
		httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=" + encodeCharset);
		httpPost.addHeader("Sign",sign);
		try {
			if (null != params) {
                httpPost.setEntity(new StringEntity(com.alibaba.fastjson.JSONObject.toJSONString(params), encodeCharset));
			}
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				Charset charset = ContentType.getOrDefault(entity).getCharset();
				// response有可能没设置编码格式，应该按参数设置的编码格式编码
				if (charset == null) {
					charset = Charset.forName(encodeCharset);
				}
				reseContent = EntityUtils.toString(entity, charset);
				EntityUtils.consume(entity);
			}
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return reseContent;
	}
}
