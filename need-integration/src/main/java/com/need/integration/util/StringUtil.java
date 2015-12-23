package com.need.integration.util;

import java.math.BigDecimal;

import com.need.integration.constant.Constants;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年9月11日 下午4:27:30
 * @ClassName StringUtil
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年9月11日
 * @modify by reason:{方法名}:{原因}
 */
public class StringUtil {
	private static final float MAX_WEIGHT = 5f;

	public static String getAddressProvince(String address) {
		if (address == null) {
			return "";
		}
		return address.split("-")[0];
	}

	public static String getAddressCity(String address) {
		if (address == null) {
			return "";
		}
		String[] addressArr = address.split("-");
		return addressArr.length > 1 ? addressArr[1] : "";
	}

	public static String getAddressZone(String address) {
		if (address == null) {
			return "";
		}
		String[] addressArr = address.split("-");
		return addressArr.length > 2 ? addressArr[2] : "";
	}

	/**
	 * 
	 * @author shenyb 2015年7月25日 上午10:44:06 @Method:
	 *         isBlank @Description: @param text @return @throws
	 */
	public static boolean isBlank(String text) {
		return text == null || "".equals(text);
	}

	public static String dealWeight(String weight) {

		if (isBlank(weight)) {
			return null;
		}
		try {
			double result = Double.parseDouble(weight);
			return result <= 0 ? null : result + "";
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * 过滤 % < > &
	 * 
	 * @author shenyb 2015年10月13日 下午3:15:50 @Method:
	 *         filterSpecilCharators @Description: @param
	 *         goodsName @return @throws
	 */
	public static String filterSpecilCharators(String goodsName) {
		if (isBlank(goodsName)) {
			return "";
		}
		return goodsName.replaceAll("[%<>&]", "");
	}
}
