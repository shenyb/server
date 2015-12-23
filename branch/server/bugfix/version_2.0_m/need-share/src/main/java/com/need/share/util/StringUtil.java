package com.need.share.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * @author Rylan 2015年3月31日 下午9:37:45 @Method: subString @Description:
	 *         ****添加字符串中间几位 (默认前3位显示) 例如 隐藏手机号中间几位
	 *         StringUtil.subCentreString(phone2, 5)=159*****888 @param
	 *         param @param count @return @throws
	 */
	public static String subCentreString(String param, Integer count) {
		if (param == null) {
			return null;
		}
		if (count == 0 || count + 3 > param.length()) {
			return param;
		}
		StringBuffer buf = new StringBuffer();
		buf.append(param.substring(0, 3));// 默认3位 也可以指定
		for (int i = 0; i < count; i++) {
			buf.append("*");
		}
		buf.append(param.substring(3 + count, param.length()));
		return buf.toString();
	}

	/**
	 * @author JinXue 2015年6月16日 下午5:37:32 @Method: isEmptyOrNull @Description:
	 *         判断字符串是否为空字符串 @param arg @return @throws
	 */
	public static boolean isEmptyOrNull(String arg) {
		if (arg == null) {
			return true;
		}
		arg = arg.trim();
		if ("".equals(arg)) {
			return true;
		}
		return false;
	}

	/**
	 * @author Rylan 2015年6月30日 下午8:42:10 @Method: random @Description:
	 *         获得验证码 @param length @return @throws
	 */
	public static String random(int length) {
		int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random random = new Random();
		String result = "";
		for (int i = 0; i < length; i++) {
			result += numbers[random.nextInt(10)];
		}
		return result;
	}

	/**
	 * 
	 * @author shenyb 2015年7月25日 上午10:44:06 @Method:
	 *         isBlank @Description: @param text @return @throws
	 */
	public static boolean isBlank(String text) {
		return text == null || "".equals(text);
	}

	/**
	 * 
	 * @author xiehao 2015年7月30日 下午6:49:20 @Method: getApiVersion @Description:
	 *         TODO 从字符串中取出版本号，版本号为数字，且字符串中有且只有一个数字 @param apiVersionStr @return
	 *         字符串中包含的版本号 @throws
	 */
	public static String getApiVersion(String apiVersionStr) {
		int beginIndex = -1;
		int endIndex = -1;
		int i, j;
		beginIndex: for (i = 0; i < apiVersionStr.length(); i++) {
			for (j = 0; j < 10; j++) {
				if (String.valueOf(j).equals(String.valueOf(apiVersionStr.charAt(i)))) {
					beginIndex = i;
					break beginIndex;
				}
			}
		}
		endIndex: for (i = apiVersionStr.length() - 1; i > -1; i--) {
			for (j = 0; j < 10; j++) {
				if (String.valueOf(j).equals(String.valueOf(apiVersionStr.charAt(i)))) {
					endIndex = i;
					break endIndex;
				}
			}
		}

		return apiVersionStr.substring(beginIndex, endIndex + 1).trim();
	}

	/**
	 * 
	 * @author shenyb 2015年8月10日 下午10:29:19 @Method: dealUrl @Description:
	 * TODO @param url @return @throws
	 */
	public static String dealUrl(String url) {
		String regex = "(http://)([\\d\\.:\\w]+)([/\\w\\d.-]+)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(url);
		if (m.find()) {
			return m.group(3);
		}
		return "";
	}

	/**
	 * 
	 * @author shenyb 2015年8月10日 下午10:29:29 @Method: main @Description:
	 * 获取URI @param args @throwsnotify_url=http://101.200.182.96:10010/need-api/trade/payNotify
refund_notify_url=http://101.200.182.96:10010/need-api/trade/refundNotify
	 */
	public static void main(String[] args) {
		System.out.println(dealUrl("http://101.200.182.96:10010/need-api/trade/payNotify"));
		System.out.println(dealUrl("http://101.200.182.96:10010/need-api/trade/refundNotify"));

	}

	/**
	 * @author Rylan 2015年8月9日 上午11:48:53
	 * @Method: arrayToString 
	 * @Description: TODO 数组格式化成指定字符串
	 * @param method
	 * @return 
	 * @throws
	 */
	public static String arrayToFormatString(Object[] array,String format) {
		StringBuffer buffer=new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(array[i].toString());
			if(i<array.length-1){//最后一行不添加  格式如  1,2,3,4,5
 				buffer.append(format);
			}
		}		
		return buffer.toString();
	}
}