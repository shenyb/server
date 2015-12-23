package com.need.integration.common.haimeiSdk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {
	private static final String format = "yyyy-MM-dd HH:mm:ss";

	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0) || (str == "");
	}
	
	
	public static boolean isNotEmpty(String str) {
		return (!(isEmpty(str)));
	}

	public static boolean isNumber(String arg0) {
		if (arg0 == null || arg0.length() == 0) return false;
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(arg0);
		return m.matches();
	}
	
	public static boolean isDouble(String arg0) {
		if (arg0 == null || arg0.length() == 0) return false;
		try{
			Double.parseDouble(arg0);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static String nullToEmpty(String arg) {
		if (arg==null || "null".equals(arg.toLowerCase())) {
			return "" ;
		} else {
			return arg;
		}
	}
	
	public static String joinString(String[] array, String separator) {
		String value = "";
		for (int i = 0; i < array.length; i++) {
			value += array[i] + separator;
		}
		if (value.length() > 1)
			value = value.substring(0, value.length() - separator.length());
		return value;
	}
	
	public static String joinString(List<String> list, String separator) {
		String value = "";
		for (int i = 0; i < list.size(); i++) {
			value += list.get(i) + separator;
		}
		if (value.length() > 1)
			value = value.substring(0, value.length() - separator.length());
		return value;
	}
	
	public static String joinString(Collection<String> coll, String separator) {
		String value = "";
		Iterator<String> iterator = coll.iterator();
		while (iterator.hasNext()) {
			value += iterator.next() + separator;
		}
		if (value.length() > 1)
			value = value.substring(0, value.length() - separator.length());
		return value;
	}
	
	public static String[] split(String str, String separator) {
		//return StringUtils.split(str, separator);
		//StringUtils.split  Bug 分隔后空值不返回
		List<String> list = splitToList(str,separator);
		return list.toArray(new String[list.size()]);
	}
	
	public static List<String> splitToList(String str, String separator) {
		List<String> list = new ArrayList<String>();
		if (StringUtil.isEmpty(str)) {
			return list;
		}
		
        if (StringUtil.isEmpty(separator)) {
        	list.add(str);
        	return list;
        }
        int lastIndex = -1;
        int index = str.indexOf(separator);
        if (-1 == index && str != null) {
        	list.add(str);
        	return list;
        }
        while (index >= 0) {
            if (index > lastIndex) {
            	list.add(str.substring(lastIndex + 1, index));
            } else {
            	list.add("");
            }
 
            lastIndex = index;
            index = str.indexOf(separator, index + 1);
            if (index == -1) {
            	list.add(str.substring(lastIndex + 1, str.length()));
            }
        }
        return list;
	}
	
	public static String lpad(String str,int length, String pad) {
		while (str.length() < length) {  
			str = pad + str;  
        }
		return str;
	}
	
	public static String rpad(String str, int length, String pad) {
		while (str.length() < length) {  
			str = str + pad;  
        }
		return str;
	}
	
	public static String formatDate(Date date) {
		String str = "" ;
		try {
			SimpleDateFormat f = new SimpleDateFormat(format);
			str = f.format(date);
		} catch (Exception e) {
		}
		return str;
	}
	
	public static Date toDate(String str){
		Date date = null ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date ;
	}
	
	
	private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
	/**
	 * 日期增加天数
	 * @param date
	 * @param amount 天数
	 * @return
	 */
	public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }
}
