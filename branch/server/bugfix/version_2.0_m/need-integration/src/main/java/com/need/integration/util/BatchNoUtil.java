package com.need.integration.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <p>
 * 第一个批次：当天 9:00-当天12:59 第二个批次：当天13:00-当天15:59 第三个批次：当天16：00-当天 17:29
 * 第四个批次：当天17:30-次日8:59 批次号：Need年月日+3位数。 比如： Need20150917001。
 * </p>
 * 
 * @author shenyb 2015年9月18日 上午9:57:14
 * @ClassName BatchNoUtil
 * @Description
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年9月18日
 * @modify by reason:{方法名}:{原因}
 */
public class BatchNoUtil {
	private static String BATCH_NO_PREFIX = "Need";
	private static String BATCH_ONE = "001";
	private static String BATCH_TWO = "002";
	private static String BATCH_THREE = "003";
	private static String BATCH_FOUR = "004";

	/** 时间串，精确天 */
	private static SimpleDateFormat DF_DATETIME_DATE = new SimpleDateFormat("yyyyMMdd");

	public static String getBatchNo() {
		String batchNo;
		Calendar current = Calendar.getInstance();

		Calendar clock9 = getCalendarByHour(9, 0, 0, 0);

		Calendar clock13 = getCalendarByHour(13, 0, 0, 0);

		Calendar clock16 = getCalendarByHour(16, 0, 0, 0);

		Calendar clock18_5 = getCalendarByHour(18, 30, 0, 0);
		Calendar clock0 = getCalendarByHour(0, 0, 0, 0);
		// 第二天的零点
		clock0.add(Calendar.DAY_OF_MONTH, 1);
		if (current.getTime().after(clock9.getTime()) && (current.getTime().before(clock13.getTime()))) {
			batchNo = BATCH_NO_PREFIX + DF_DATETIME_DATE.format(current.getTime()) + BATCH_ONE;
			return batchNo;
		}
		if ((current.getTime().after(clock13.getTime()) || (current.getTimeInMillis() == clock13.getTimeInMillis()))
				&& current.getTime().before(clock16.getTime())) {
			batchNo = BATCH_NO_PREFIX + DF_DATETIME_DATE.format(current.getTime()) + BATCH_TWO;
			return batchNo;
		}
		if ((current.getTime().after(clock16.getTime()) || (current.getTimeInMillis() == clock16.getTimeInMillis()))
				&& current.getTime().before(clock18_5.getTime())) {
			batchNo = BATCH_NO_PREFIX + DF_DATETIME_DATE.format(current.getTime()) + BATCH_THREE;
			return batchNo;
		}
		// 第四个批次，如果到了第二天，时间要减一天
		if ((current.getTime().after(clock18_5.getTime()) || (current.getTimeInMillis() == clock18_5.getTimeInMillis()))
				&& current.getTime().before(clock0.getTime())) {
			batchNo = BATCH_NO_PREFIX + DF_DATETIME_DATE.format(current.getTime()) + BATCH_FOUR;
		} else {
			current.add(Calendar.DAY_OF_MONTH, -1);
			batchNo = BATCH_NO_PREFIX + DF_DATETIME_DATE.format(current.getTime()) + BATCH_FOUR;
		}
		return batchNo;
	}

	private static Calendar getCalendarByHour(int hour, int minute, int second, int millsecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MILLISECOND, millsecond);
		return cal;
	}

	public static void main(String[] args) {
		// Calendar current = Calendar.getInstance();
		// System.out.println(DF_DATETIME_TIME.format(current.getTime()));
		// System.out.println("current:"+getBatchNo(current));
		//
		// current.set(Calendar.HOUR, 7);
		// System.out.println(DF_DATETIME_TIME.format(current.getTime()));
		// System.out.println("current7:"+getBatchNo(current));
		//
		// current.set(Calendar.HOUR, 9);
		// current.set(Calendar.MINUTE, 0);
		// System.out.println(DF_DATETIME_TIME.format(current.getTime()));
		// System.out.println("current9:"+getBatchNo(current));
		//
		// current.set(Calendar.HOUR, 12);
		// System.out.println(DF_DATETIME_TIME.format(current.getTime()));
		// System.out.println("current12:"+getBatchNo(current));

		Calendar clock14 = Calendar.getInstance();
		clock14.set(Calendar.HOUR_OF_DAY, 18);
		// System.out.println(DF_DATETIME_TIME.format(clock14.getTime()));
		// System.out.println("current13:"+getBatchNo(clock14));
		//
		// current.set(Calendar.HOUR, 17);
		// System.out.println(DF_DATETIME_TIME.format(current.getTime()));
		// System.out.println("current17:"+getBatchNo(current));
		//
		// current.set(Calendar.HOUR, 19);
		// System.out.println(DF_DATETIME_TIME.format(current.getTime()));
		// System.out.println("current19:"+getBatchNo(current));
		//
		// current.set(Calendar.HOUR, 22);
		// System.out.println(DF_DATETIME_TIME.format(current.getTime()));
		// System.out.println("current22:"+getBatchNo(current));
		//
		// current.set(Calendar.HOUR, 4);
		// System.out.println(DF_DATETIME_TIME.format(current.getTime()));
		// System.out.println("current4:"+getBatchNo(current));
	}

}
