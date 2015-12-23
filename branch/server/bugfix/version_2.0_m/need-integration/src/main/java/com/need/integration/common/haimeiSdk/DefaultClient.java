package com.need.integration.common.haimeiSdk;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.need.integration.common.haimeiSdk.internal.parser.Parser;
import com.need.integration.common.haimeiSdk.internal.parser.ParserFactory;
import com.need.integration.common.haimeiSdk.request.IscsRequest;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;
import com.need.integration.common.haimeiSdk.util.AuthUtil;
import com.need.integration.common.haimeiSdk.util.FileUtil;
import com.need.integration.common.haimeiSdk.util.StringUtil;

public class DefaultClient {
	public  final String CHARSET_UTF8 = "UTF-8";
	private String serverUrl;
	private int connectTimeout;
	private int readTimeout;
	private String appKey;
	private String format;
	private String appSecret;
	private static transient Log log = LogFactory.getLog(DefaultClient.class);

	public DefaultClient(String serverUrl, String appKey, String appSecret,String format) {
		connectTimeout = 0;
		readTimeout = 0;
		this.serverUrl = serverUrl;
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.format = format;
	}

	public DefaultClient(String serverUrl, String appKey, String appSecret,String format, int connectTimeout, int readTimeout) {
		this(serverUrl, appKey, appSecret, format);
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
	}

	public <T extends AbstractResponse> T execute(IscsRequest<T> request) throws IscsException {
		try {
			String url = buildUrl(request);
			String data = null; 
			if ("json".equals(this.format)) {
				data = ParserFactory.getJsonParser().writeValueAsString(request); 
			} else if ("xml".equals(this.format)) {
//				data = .toJson(request.getAppJsonParams());
			} else {
				throw new IscsException("未知的格式,只支持xml,json");
			}

			Map<String, String> params = new HashMap<String, String>();
			params.put("v_data", data);
			log.debug("request data : " + data);
			String rsp = FileUtil.doPost(url, params, this.CHARSET_UTF8);
			log.debug("response data : " + rsp);
			T resp = parse(rsp, request.getResponseClass());
			StringBuffer sb = new StringBuffer();
			sb.append(url).append("&").append("data").append("=").append(data);
			return resp;
		} catch (Exception e) {
			throw new IscsException(e);
		}
	}

	private String buildUrl(IscsRequest request) throws Exception {
		Map<String,String> sysParams = new TreeMap<String,String>();
		String v_timestamp = StringUtil.formatDate(new Date());
		sysParams.put("v_appsign", AuthUtil.getMD5(this.appKey + this.appSecret + v_timestamp));
		sysParams.put("v_method", request.getApiMethod());
		sysParams.put("v_appkey", this.appKey);
		sysParams.put("v_format", this.format);
		sysParams.put("v_timestamp", v_timestamp);
		
		StringBuilder sb = new StringBuilder(serverUrl);
		sb.append("?");
		sb.append(FileUtil.buildQuery(sysParams, "UTF-8"));
		return sb.toString();
	}

	private <T extends AbstractResponse> T parse(String rsp, Class<T> responseClass) throws IscsException {
		Parser parser;
		if (format.equals("json")) {
			parser = ParserFactory.getJsonParser();
		} else {
			parser = ParserFactory.getXmlParser();
		}
		return parser.parse(rsp, responseClass);
	}
}
