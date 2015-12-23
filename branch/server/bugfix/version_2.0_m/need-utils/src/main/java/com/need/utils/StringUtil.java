package com.need.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.need.utils.wms.Constants;

public class StringUtil {
	/**
	 * 分割符
	 */
	private static final String split = ",";

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
	/*public static boolean isBlank(String text) {
		return text == null || "".equals(text);
	}*/

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
	 *         TODO @param url @return @throws
	 */
	public static String dealUrl(String url) {
		String regex = "(https?://)([\\d\\.:\\w]+)([/\\w\\d.-]+)";
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
	 *         获取URI @param
	 *         args @throwsnotify_url=http://101.200.182.96:10010/need-api/trade
	 *         /payNotify
	 *         refund_notify_url=http://101.200.182.96:10010/need-api/trade/
	 *         refundNotify
	 */
	
	/**
	 * @author Rylan 2015年8月9日 上午11:48:53 @Method: arrayToString @Description:
	 * TODO 数组格式化成指定字符串 @param method @return @throws
	 */
	public static String arrayToFormatString(Object[] array, String format) {
		StringBuffer buffer = new StringBuffer();
		if(isBlank(format)){
			format = split;
		}
		for (int i = 0; i < array.length; i++) {
			buffer.append(array[i].toString());
			if (i < array.length - 1) {// 最后一行不添加 格式如 1,2,3,4,5
				buffer.append(format);
			}
		}
		return buffer.toString();
	}

	/**
	 * 把指定的字符串转换成List
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static List<String> stringToList(String str, String format) {
		String[] arrayStr = str.split(format);
		List<String> list = new ArrayList<String>();
		Collections.addAll(list, arrayStr);
		return list;
	}

	/**
	 * 
	 * @author shenyb 2015年8月25日 下午2:51:35 @Method: stringToList @Description:
	 * TODO @param str @param format @return @throws
	 */
	public static String[] stringToArrayBySplit(String str) {
		return str==null?null:str.split(",");
	}
	

	/**
	 * @author shenyb 2015年8月2日 上午1:05:29
	 * @Method: getSimpleMethod 
	 * @Description: 获取类名.方法
	 * @param string public package.method(param)
	 * @return 
	 * @throws 
	 */
	
	public static String getSimpleMethod(String method) {
		String middle =  method.split(" ")[2];
		String fullMethod =  middle.substring(0, middle.indexOf("("));
		String[] strs = fullMethod.split("\\.");
		return strs[strs.length-2]+"."+strs[strs.length-1];
	}
	
	/**
	 * @author Rylan 2015年9月15日 上午12:29:39
	 * @Method: convertStreamToString 
	 * @Description: 读取流中的内容转换成字符串输出
	 * @param is ：输入流
	 * @return 
	 * @throws
	 */
	public static String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    } 
	
	/**
	 * @author Rylan 2015年11月5日 下午5:42:25
	 * @Method: subMobileCentre 
	 * @Description: 手机截取中间5位
	 * @param param
	 * @return 
	 * @throws
	 */
	public static String subMobileCentre(String param) {		
		if(isEmptyOrNull(param)){
			return param;
		}		
		if (param.length() < 11) {
			return param;
		}		
		return subCentreString(param,5);
	}
	
	/**
	 * @author Rylan 2015年11月5日 下午5:46:52
	 * @Method: subCertificationCardCentre 
	 * @Description: 身份证截取方法，截取中间11位
	 * @param param
	 * @return 
	 * @throws
	 */
	public static String subCertificationCardCentre(String param) {
		if(isEmptyOrNull(param)){
			return param;
		}		
		if (param.length() < 18) {
			return param;
		}	
		return subCentreString(param,11);
	}
    
    private static DecimalFormat percentFormat=new DecimalFormat(".00");
    public static String convertToPercent(float param) {
        return percentFormat.format(param * 100) + "%";
    }
	
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
		return text == null || "".equals(text.trim());
	}

	public static String dealWeight(String weight) {

		if (isBlank(weight)) {
			return null;
		}
		try {
			float result = Float.parseFloat(weight);
			return result <= 0 ? null : result + "";
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(filterSpecilCharators("「日本」资生堂 尿素10%乳液"));
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		String w = (Float.valueOf(1.23+"")) * 1 + "";
		String weight = Float.valueOf(1.23+"").floatValue()+ Constants.BOX_WEIGHT+"";//.floatValue() + Constants.BOX_WEIGHT + "";
		double f1=0.05d;
		double f2=0.01d;
		System.out.println(new BigDecimal(f1).add(new BigDecimal(f2)).floatValue());
	//	System.out.println(w);
	//	System.out.println(weight);
		// System.out.println(dealWeight("1.2"));
		// System.out.println(dealWeight("123ml"));
		// System.out.println(dealWeight("12.3kg"));
		// System.out.println(dealWeight("12.3"));
		// System.out.println(dealWeight("0.4g*32个"));
		// System.out.println(dealWeight("0.14"));
		// System.out.println(Float.valueOf("0.14").floatValue() * 1 +
		// Constants.BOX_WEIGHT + "");

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
	
	public static String arrayToFormatStringToSelect(Object[] array, String format) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append("'"+array[i].toString()+"'");
			if (i < array.length - 1) {// 最后一行不添加 格式如 1,2,3,4,5
				buffer.append(format);
			}
		}
		return buffer.toString();
	}
/*	public static void main(String[] args) {
		System.out.println(StringUtil.subCertificationCardCentre("120222199203140415"));
		System.out.println(StringUtil.subMobileCentre("15822060068"));
	}*/
	
	
}