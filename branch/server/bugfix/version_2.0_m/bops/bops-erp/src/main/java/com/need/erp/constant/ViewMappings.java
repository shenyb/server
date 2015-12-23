package com.need.erp.constant;

/**
 * <p></p>
 * @author Rylan 2015年8月23日 下午4:48:28
 * @ClassName ViewMappings 统一返回视图常量文件
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月23日
 * @modify by reason:{方法名}:{原因}
 */
public class ViewMappings {
	
	/**
	 * 商品列表
	 */
	public final static String GOODS_LIST = "/goods/goodsList";
	
	
	/**
	 * 商品详情
	 */
	public final static String GOODS_PROFILE = "/goods/goodsProfile";
	
	/**
	 * 组合商品详情
	 */
	public final static String GOODS_GROUP_PROFILE = "/goods/goodsGroupProfile";
	
	/**
	 * 组合商品编辑
	 */
	public final static String GOODS_GROUP_EDIT = "/goods/goodsGroupEdit";
	
	/**
	 * 商品编辑
	 */
	public final static String GOODS_EDIT = "/goods/goodsEdit";
	
	/***** start  商品价格修改  *****/
	public final static String GOODS_PRICE_BAD_LIST = "/goods/price/changeGoodsPriceBadListContent";
	
	public final static String GOODS_PRICE_LIST = "/goods/price/changeGoodsPriceListContent";
	
	public final static String GOODS_PRICE_DETAILS = "/goods/price/goodsPriceDetails";
	
	public final static String GOODS_PRICE_DETAIL_LIST = "/goods/price/goodsPriceDetailsList";
	
	public final static String GOODS_CHANGE_PRICE = "/goods/price/changePrice";
	
	public final static String GOODS_CHANGE_PRICE_LIST = "/goods/price/goodsChangePriceList";
	/***** end  商品价格修改  *****/
	
	
	/**
	 * 商品新增
	 */
	public final static String GOODS_ADD_PAGE = "/goods/goodsAdd";

	/**
	 * 商品库存列表
	 */
	public final static String GOODS_STORE_LIST = "/goods/storeList";
	
	public final static String GOODS_STORE_TO_EXPORT_PAGE = "/goods/goodsStoreExportPage";
	
	/**
	 * 库存历史记录列表
	 */
	public final static String STORE_LOG_LIST = "/goods/storeLogList";
	
	/**
	 * 库存
	 */
	public final static String STROE_DETAIL = "/goods/storeDetail";
	
	/**
	 * 交易列表
	 */
	public final static String TRADE_LIST = "/trade/tradeList";
	
	/**
	 * 交易导出页面
	 */
	public final static String TRADE_EXPORT_PAGE = "/trade/tradeExport";
	
	/**
	 * 订单批次列表
	 */
	public final static String BATCH_TRADE_LIST = "/trade/batchTradeList";
	
	/**
	 * 批量发货页面
	 */
	public final static String BATCH_TRADE_SEND = "/trade/batchTradeSend";
	
	/**
	 * 批量发货
	 */
	public final static String BATCH_SEND_TABLE_CONTENT = "/trade/batchSendTableContent";
	
	/**
	 * 权限列表
	 */
	public final static String AUTH_LIST =   "/auth/authList";
	
	/**
	 * 角色详细
	 */
	public final static String SYSTEM_ROLE_DETAIL = "/system/roleManager/roleDetail";
	
	/**
	 * 角色列表
	 */
	public final static String SYSTEM_ROLE_LIST =   "/system/roleList";
	/**
	 * 用户列表
	 */
	public final static String SYSTEM_USER_LIST =   "/system/userList";
	/**
	 * 交易详情
	 */

	public static final String TRADE_DETAIL = "/trade/tradeDetail";
	
	/**
	 * 用户登陆首页
	 */
	public final static String WEB_LOGIN_PAGE = "/login";
	

	/**
	 * 405页面
	 */
	public final static String WEB_REEOR_405_PAGE = "/error/405";
	
	/**
	 * 管理员首页
	 */
	public final static String WEB_ADMIN_INDEX_PAGE =   "/main";
	
	/**
	 * 分类列表
	 */
	public final static String CATEGORIES_LIST =   "/goods/categoriesList";
	
	/**
	 * 新增分类
	 */
	public final static String CATEGORIES_ADD =   "/goods/categoriesAdd";
	
	/**
	 * 编辑分类
	 */
	public final static String CATEGORIES_MOD =   "/goods/categoriesEdit";
	
	/**
	 * 分类详细
	 */
	public final static String CATEGORIES_DETAIL =   "/goods/categoriesDetail";
	
	/**
	 * 品牌列表
	 */
	public final static String BRAND_LIST =   "/goods/brandList";
	
	/**
	 * 新增品牌
	 */
	public final static String BRAND_ADD =   "/goods/brandAdd";
	
	/**
	 * 编辑品牌
	 */
	public final static String BRAND_MOD =   "/goods/brandEdit";
	
	/**
	 * 品牌详细
	 */
	public final static String BRAND_DETAIL =   "/goods/brandDetail";
	
	/**
	 * 采购单列表
	 */
	public final static String PURCHASE_LIST =   "/purchase/purchaseList";
	
	/**
	 * 新增采购单
	 */
	public final static String PURCHASE_ADD =   "/purchase/purchaseAdd";
	
	/**
	 * 采购单批量
	 */
	public final static String PURCHASE_BATCH = "/purchase/batchPurchaseResult";
	
	/**
	 * 采购单详情
	 */
	public final static String PURCHASE_DETAIL = "/purchase/purchaseDetail";
	
	/**
	 * 收货
	 */
	public final static String PURCHASE_EDIT = "/purchase/purchaseEdit";
	
	/**
	 *  供应商物流商列表
	 */
	public final static String VENDOR_LIST =   "/vendor/vendorList";
	
	/**
	 * 组合商品新增
	 */
	public final static String GOODS_GROUP_ADD = "/goods/goodsGroupAdd";
}

