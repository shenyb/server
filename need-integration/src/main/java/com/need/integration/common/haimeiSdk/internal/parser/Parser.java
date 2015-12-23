package com.need.integration.common.haimeiSdk.internal.parser;

import com.need.integration.common.haimeiSdk.IscsException;
import com.need.integration.common.haimeiSdk.response.AbstractResponse;

public interface Parser {

	public abstract <T extends AbstractResponse> T parse(String s, Class<T> class1) throws IscsException;

	public abstract String writeValueAsString(Object object);
}
