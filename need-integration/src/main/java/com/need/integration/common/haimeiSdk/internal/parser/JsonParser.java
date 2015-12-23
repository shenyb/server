package com.need.integration.common.haimeiSdk.internal.parser;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.need.integration.common.haimeiSdk.IscsException;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;
import com.need.integration.common.haimeiSdk.util.StringUtil;

public class JsonParser implements Parser {

	private ObjectMapper mapper = new ObjectMapper();

	public JsonParser() {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public <T extends AbstractResponse> T parse(String json, Class<T> responseClass) throws IscsException {
		T response = null;
		try {
			response = responseClass.newInstance();
			if (StringUtil.isEmpty(json))
				throw new IscsException("response json is empty!");
			ObjectNode rootNode = (ObjectNode) mapper.readTree(json);
			if ("100".equals(rootNode.get("errorCode").textValue())) {
				if (rootNode.get("data").isArray()) {
					response = mapper.readValue(rootNode.toString(), responseClass);
				} else {
					response.setData(mapper.readValue(rootNode.get("data").toString(), responseClass));
				}
			} else {
				response.setErrorText(rootNode.get("errorText").textValue());
			}
			response.setRegLogGuid(rootNode.get("reqLogGuid").textValue());
			response.setErrorCode(rootNode.get("errorCode").textValue());

		} catch (Exception e) {
			throw new IscsException(e);
		}
		return response;
	}

	public String writeValueAsString(Object obj) {
		String value = null;
		try {
			value = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
		}
		return value;
	}

}
