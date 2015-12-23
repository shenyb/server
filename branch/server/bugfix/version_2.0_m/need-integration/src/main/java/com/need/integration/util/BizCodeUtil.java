package com.need.integration.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.math.RandomUtils;

public class BizCodeUtil {
	private static SimpleDateFormat DF_DATE = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public static String generateOutRequestNo(){
		return DF_DATE.format(Calendar.getInstance().getTime())+RandomUtils.nextInt(100);
	}
}
