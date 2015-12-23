package com.need.biz.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * <p>货币装换封装方法</p>
 * @author Rylan 2015年8月24日 下午5:22:21
 * @ClassName CurrencyUtil
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月24日
 * @modify by reason:{方法名}:{原因}
 */
public class CurrencyUtil {

	
    /**  
     * 元转换为分.    
     * @param  分 
     * @return 元  
     * @throws ParseException 
     */ 
    public static String fromYuanToFen(final String yuan)  { 
    	if(StringUtils.isEmpty(yuan)||yuan.equals("0")){
        	return yuan;
        } 
        String fen = "";  
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?{1}");  
        Matcher matcher = pattern.matcher(yuan);  

        NumberFormat format = NumberFormat.getInstance();  
        Number number=null;
		try {
			number = format.parse(yuan);
		} catch (ParseException e) {
			/** TODO Auto-generated catch block */
			e.printStackTrace();
			return "";
		}  
        double temp = number.doubleValue() * 100.0;  
        // 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012  
        format.setGroupingUsed(false);  
        // 设置返回数的小数部分所允许的最大位数  
        format.setMaximumFractionDigits(0);  
        fen = format.format(temp);  
             
          
        return fen;  
    } 
	
    /**  
     * 分转换为元.  
     * @param  分  
     * @return 元  
     */  
    public static String fromFenToYuan(final String fen) {  
        if(StringUtils.isEmpty(fen)||fen.equals("0")){
        	return fen;
        }   	
    	String yuan ="";  
        final int MULTIPLIER = 100;  
        Pattern pattern = Pattern.compile("^[1-9][0-9]*{1}");  
        Matcher matcher = pattern.matcher(fen);  
        if (matcher.matches()) {  
            yuan = new BigDecimal(fen).divide(new BigDecimal(MULTIPLIER)).setScale(2).toString();  
        } else {  
            System.out.println("参数格式不正确!");  
        }  
        return yuan;  
    }  
  
    /**
     * @author Rylan 2015年11月3日 下午7:09:54
     * @Method: subZeroAndDot 
     * @Description: 去掉多余的 .0   1.0 -> 1
     * @param s
     * @return 
     * @throws
     */
    public static String subZeroAndDot(String s){  
        if(s.indexOf(".") > 0){  	
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;  
    } 
    
    

    
  
}
