package com.need.utils;

import java.math.BigDecimal;

public class NumberUtil {
	
	public final static BigDecimal HUNDRED = new BigDecimal("100");

	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是浮点数
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains(".")) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是数字
	 */
	public static boolean isNumber(String value) {
		return isInteger(value) || isDouble(value);
	}
	
	public static BigDecimal fen2Yuan(BigDecimal value){
		return value.divide(HUNDRED);
	}
	
	public static BigDecimal fen2Yuan(String value){
		return new BigDecimal(value).divide(HUNDRED);
	}
	
	public static BigDecimal fen2Yuan(double value){
		return new BigDecimal(value).divide(HUNDRED);
	}
	
	public static BigDecimal fen2Yuan(int value){
		return new BigDecimal(value).divide(HUNDRED);
	}
	
	public static BigDecimal fen2Yuan(long value){
		return new BigDecimal(value).divide(HUNDRED);
	}
	
	public static BigDecimal yuan2Fen(BigDecimal value){
		return value.multiply(HUNDRED);
	}
	
	public static BigDecimal yuan2Fen(String value){
		return new BigDecimal(value).multiply(HUNDRED);
	}
	
	public static BigDecimal yuan2Fen(double value){
		return new BigDecimal(value).multiply(HUNDRED);
	}
	
	public static BigDecimal yuan2Fen(int value){
		return new BigDecimal(value).multiply(HUNDRED);
	}
	
	public static BigDecimal yuan2Fen(long value){
		return new BigDecimal(value).multiply(HUNDRED);
	}
}
