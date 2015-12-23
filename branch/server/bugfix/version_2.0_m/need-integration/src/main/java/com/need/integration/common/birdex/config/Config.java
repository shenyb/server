package com.need.integration.common.birdex.config;

import java.util.HashMap;
import java.util.Map;

public class Config {
	/**
	 * <p>
	 * </p>
	 * 
	 * @author LV 2015年10月23日 上午11:42:59
	 * @ClassName Config
	 * @Description http://openapi.birdex.cn/help/Api/POST/order/create
	 * @version V1.0
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: LV 2015年10月23日
	 * @modify by reason:{方法名}:{原因} http://openapi.birdex.cn/
	 */
	public static final String order_create = "http://openapi.birdex.cn/order/create";
	public static final String orderResult = "http://openapi.birdex.cn/order/orderResult";
	public static final String orderInfo = "http://openapi.birdex.cn/tracking/orderInfo";
	
	public static final String order_create_test = "http://birdexcn.imwork.net:8019/order/create";
	public static final String orderResult_test = "http://birdexcn.imwork.net:8019/order/orderResult";
	public static final String orderInfo_test = "http://birdexcn.imwork.net:8019/tracking/orderInfo";
	
	//错误码
	public static Map<Integer,String> errors = new HashMap<Integer,String>();
	public static Map<Integer,String> fix_thread = new HashMap<Integer,String>();
	static{
		//初始化错误输出
		errors.put(0, "请求成功");
		errors.put(1, "失败");
		errors.put(5, "处理中");
		errors.put(400, "无效");
		errors.put(999, "未知错误");
		errors.put(1000, "需要权限");
		errors.put(1001, "资源不存在");
		errors.put(1002, "参数验证不通");
		errors.put(1003, "缺少参数appKey");
		errors.put(1004, "请求数据格式不对");
		errors.put(1005, "参数appKey不存在");
		errors.put(1006, "请求数据签名校验失败");
		errors.put(1007, "请求数据保存失败");
		errors.put(1008, "其它原因");
		errors.put(41001, "预报号不存在");
		errors.put(41002, "取消预报失败");
		errors.put(41003, "包裹未入库");
		errors.put(42001, "订单号不存在");
		errors.put(42002, "取消订单失败");
		errors.put(42003, "库存不足");
		errors.put(42101, "物流不支持的收货地区");
		errors.put(43001, "身份证审核不通过");
		
		//初始化处理类
		fix_thread.put(0, "Message#succ");
		fix_thread.put(1, "失败");
		fix_thread.put(5, "处理中");
		fix_thread.put(400, "无效");
		fix_thread.put(999, "未知错误");
		fix_thread.put(1000, "需要权限");
		fix_thread.put(1001, "资源不存在");
		fix_thread.put(1002, "参数验证不通");
		fix_thread.put(1003, "缺少参数appKey");
		fix_thread.put(1004, "请求数据格式不对");
		fix_thread.put(1005, "参数appKey不存在");
		fix_thread.put(1006, "请求数据签名校验失败");
		fix_thread.put(1007, "请求数据保存失败");
		fix_thread.put(1008, "其它原因");
		fix_thread.put(41001, "预报号不存在");
		fix_thread.put(41002, "取消预报失败");
		fix_thread.put(41003, "包裹未入库");
		fix_thread.put(42001, "订单号不存在");
		fix_thread.put(42002, "取消订单失败");
		fix_thread.put(42003, "库存不足");
		fix_thread.put(42101, "物流不支持的收货地区");
		fix_thread.put(43001, "身份证审核不通过");
		
	}
}
