package com.need.erp.wms.integration.constant;

public class ERPBizReturnCode {

	/**
	 * 商品的参数不能为空
	 */
	public static final int GOODS_3011 = 3011;
	/**
	 * 商品重量字段有误
	 */
	public static final int GOODS_3020 = 3020;
	/**
	 * 商品的折扣价不能大于原价
	 */
	public static final int GOODS_3008 = 3008;
	/**
	 * 此商品名称已经添加过
	 */
	public static final int GOODS_3001 = 3001;
	/**
	 * 此商品编号已添加过
	 */
	public static final int GOODS_3002 = 3002;
	/**
	 * 商品国际条形码重复
	 */
	public static final int GOODS_3023 = 3023;
	/**
	 * 商品图片不能为空,请添加商品图片
	 */
	public static final int GOODS_3022 = 3022;
	/**
	 * 库存为0，不能上架，请先添加库存
	 */
	public static final int GOODS_3012 = 3012;
	/**
	 * 只有审核通过的商品才能进行上下架操作
	 */
	public static final int GOODS_3013 = 3013;
	/**
	 * 添加库存数不能为空
	 */
	public static final int GOODS_3010 = 3010;
	/**
	 * 库存数量只能为整数 
	 */
	public static final int GOODS_3021 = 3021;
	
	/**
	 * 分类名称不能为空
	 */
	public static final int CATEGORY_3024 = 3024;
	/**
	 * 分类名称不能重复
	 */
	public static final int CATEGORY_3025 = 3025;
	/**
	 * 请选择分类
	 */
	public static final int CATEGORY_3026 = 3026;
	
}
