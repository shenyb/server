package com.need.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	/**
	 * 默认格式转换
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date formatStrToDate(String dateStr) throws ParseException {
		return formatStrToDate(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 将字符串转换成date类型
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date formatStrToDate(String dateStr, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateStr);

	}

	public static String formatDateToStr(Date date) {
		return formatDateToStr(date, "yyyy-MM-dd");

	}

	public static String formatDateToStr(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date getZeroOfToday() {
		return getStartOfDay(Calendar.getInstance().getTime());
	}

	public static Date getStartOfDay(Date day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getEndOfDay(Date day) {
		Date result = getStartOfDay(day);
		result.setTime(result.getTime() + 24 * 3600 * 1000 - 1);
		return result;
	}

	/**
	 * @author xiehao 2015年10月31日 下午3:09:08
	 * @Method: dateOfPlus
	 * @Description: TODO 把某天的日期加几天或者减几天
	 * @param day
	 * @param value
	 *            正数表示加几天，负数相反
	 */
	public static Date dateOfPlus(Date day, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) +  value);

		return calendar.getTime();
	}
	
	/**
	 * 
	 * @author xiehao 2015年10月31日 下午3:20:16
	 * @Method: hoursOfPlus 
	 * @Description: TODO 小时相加减
	 * @param day
	 * @param value  正数表示加，负数相反
	 */
	public static Date hoursOfPlus(Date day, int value){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) +  value);
		
		return calendar.getTime();
	}
	
	/*public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date day = new Date();
		System.out.println("date: " + sdf.format(day));
		System.out.println("date: " + sdf.format(hoursOfPlus(day, 9)));
		System.out.println("date: " + sdf.format(hoursOfPlus(day, -16)));
		
	}*/

    /**
     * 毫秒转换成小时
     * @param milliSecond
     * @return hour
     */

    
    public static Integer milliSecondToHour(Integer milliSecond) {
        return milliSecond / 3600000;
    }

}
