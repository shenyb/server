package com.need.share.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    private static final Logger logger = Logger.getLogger(DateUtil.class);
    static SimpleDateFormat DF_DATETIME = new SimpleDateFormat("yyyy-MM-dd");

    
    /**
     * 默认格式转换
     * @param dateStr
     * @return
     */
    public static Date formatStrToDate(String dateStr){
        return formatStrToDate(dateStr, "yyyy-MM-dd");
    }
    
    /**
     * 将字符串转换成date类型
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date formatStrToDate(String dateStr , String pattern){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error(String.format("when str to date format error : %s",e.getMessage()));
        }
        return null;
    }
    
    
    
    public static String formatDateToStr(Date date){
        return formatDateToStr(date,"yyyy-MM-dd");
        
    }
    
    public static String formatDateToStr(Date date , String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
 
    /**
     * @Method: calculateTimeDiff 
     * @Description: 计算时间间隔
     * @param before
     * @param after
     * @return 
     * @throws
     */
    public static String calculateTimeDiff(Date before,Date after){
        long days,hours;
        long beforeMills = before.getTime();
        long afterMills = after.getTime();
        String result = "";
        days = (beforeMills-afterMills)/(1000*60*60*24);
        if(days>0)
            result = days+"天前";
        else{
            hours = (beforeMills-afterMills)/(1000*60*60);
            if(hours>0)
                result = hours+"小时前";
            else
                result = ((beforeMills-afterMills)/(1000*60))+"分钟前";
        }
        return result;
    }
    
    /**
     * @author Rylan 2015年9月5日 下午4:34:13
     * @Method: getYearMonthDaysByNow 
     * @Description: 返回当前时间的数组形式
     * @return 
     * @throws
     */
    public static String[] getYearMonthDaysByNow(){
        Date now = Calendar.getInstance().getTime();
        String ymd = DF_DATETIME.format(now);
        return ymd.split("-");
    }
    
    /**
     * @author Rylan 2015年9月5日 下午4:34:09
     * @Method: getYearMonthDaysStrByNow 
     * @Description: 返回当前时间的字符串形式 2015-09-18
     * @return 
     * @throws
     */
    public static String getYearMonthDaysStrByNow(){
        Date now = Calendar.getInstance().getTime();
        String ymd = DF_DATETIME.format(now);      
        return ymd;
    }
    /**
     * @author Rylan 2015年9月18日 下午4:26:29
     * @Method: getYearMonthDaysStrByNow 
     * @Description: 格式化当前时间输出
     * @param format
     * @return 
     * @throws
     */
    public static String getYearMonthDaysStrByNow(String format){
        Date now = Calendar.getInstance().getTime();
        return formatDateToStr(now, format);
    }
    
    public static Date getZeroOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
   public static void main(String[] args) {
	System.out.println(DateUtil.getYearMonthDaysStrByNow("yyyyMMdd"));
   }
    
}
