package com.need.integration.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author Rylan 2015年7月25日 上午11:11:11
 * @ClassName DateUtil
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public class DateUtil {
	/** 时间串，精确到分 */
	static SimpleDateFormat DF_DATETIME = new SimpleDateFormat("yyyyMMddHHmm");
	/** 时间串，精确小时 */
	static SimpleDateFormat DF_DATETIME_HOUR = new SimpleDateFormat("yyyyMMddHH");
	/** 时间串，精确到秒 */
	static SimpleDateFormat DF_LONG_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final Logger logger = Logger.getLogger(DateUtil.class);

	/**
	 * 默认格式转换
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date formatStrToDate(String dateStr) {
		return formatStrToDate(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 将字符串转换成date类型
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date formatStrToDate(String dateStr, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error(String.format("when str to date format error : %s", e.getMessage()));
		}
		return null;
	}

	/**
	 * 
	 * @author shenyb 2015年9月12日 上午11:16:01 @Method:
	 *         formatDateToStr @Description:YYYY-MM-DD hhmmss @param
	 *         date @return @throws
	 */
	public static String formatDateToStr(Date date) {
		return DF_LONG_DATETIME.format(date);
	}

	/**
	 * 获取时间，精确到小时
	 * @author shenyb 2015年9月15日 下午2:30:03 @Method:
	 * formatDateTimeHourToStr @Description:  @param date @return @throws
	 */
	public static String formatDateTimeHourToStr(Date date) {
		return DF_DATETIME_HOUR.format(date);
	}

	public static void main(String[] args) {
		System.out.println(formatDateToStr(new Date()));
	}

	public static String formatDateToStr(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

}
