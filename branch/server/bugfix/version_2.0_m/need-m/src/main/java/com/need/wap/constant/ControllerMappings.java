package com.need.wap.constant;

/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年7月22日 下午7:45:16
 * @ClassName ControllerMappings
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月22日
 * @modify by reason:{方法名}:{原因}
 */
public class ControllerMappings {

	/**
	 * @Fields USER : TODO 用户文件夹
	 */
	public final static String USER = "user/";
	/**
	 * @Fields DEVICE :设备文件夹
	 */
	public final static String COMMON = "common/";
	
	/**
	 * @Fields DEVICE :设备文件夹
	 */
	public final static String WECHAT = "wechat/";
	
	
	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : TODO 发送验证码
	 */
	public final static String SEND_VALIDATION_CODE = COMMON + "sendValidationCode";

	
	/**
	 * @Fields GOODS :商品
	 */
	public final static String GOODS = "goods/";
	
	/**
	 * @Fields GOODS :商品评价
	 */
	public final static String GOODSCOMMENT = "goodsComment/";
	

	/**
	 * @Fields USER_LOGIN : 用户登录得接口
	 */
	public final static String USER_LOGIN = USER + "login";
	
	

	
	
	/**
	 * 交易
	 */
	public final static String TRADE = "trade";

	
	public final static String RETAILING = "retailing";
	
	
	public final static String TRADE_ADDRESS_LIST = TRADE + "/address/list";

	public final static String TRADE_ADD_ADDRESS = TRADE + "/address/add";

	public final static String TRADE_ADDRESS_UPDATE = TRADE + "/address/edit";
	// 组合购买
	public final static String TRADE_COMBINATION_CONFIRM_BUY = "combination/trade";

	/**
	 * 获取支付token
	 */
	public final static String GET_PAY_SIGN = TRADE + "/getPaySign";
	/**
	 * 获取微信支付token
	 */
	public final static String GET_WECHAT_PAY_SIGN = TRADE + "/getWeChatPaySign";
	/**
	 * 添加购物车
	 */
	public final static String TRADE_CART_ADD = TRADE + "/cart/add";
	/**
	 * 删除购物车
	 */
	public final static String TRADE_CART_DELETE = TRADE + "/cart/delete";
	/**
	 * 编辑购物车
	 */
	public final static String TRADE_CART_EDIT = TRADE + "/cart/edit";
	/**
	 * 购物车购买
	 */
	public final static String TRADE_CART_CREATE_ORDER = TRADE + "/cart/createTrade";

	/**
	 * 获取购物车列表
	 */
	public final static String TRADE_CART_LIST = TRADE + "/cart/list";

	/**
	 * 立即购买
	 */
	public final static String TRADE_GOODS_CREATE_TRADE = TRADE + "/goods/createTrade";
	/**
	 * 团便宜下单
	 */
	public final static String TRADE_CHEAP_CREATE = "cheap/trade";
	/**
	 * 订单列表
	 */
	public final static String TRADE_ORDER_LIST = TRADE + "/list";

	/**
	 * 订单详情
	 */
	public final static String GET_TRADE_ORDER_DETAIL = TRADE + "/detail"+"/{userId}/{tradeNo}";

	/**
	 * 订单支付信息
	 */
	public final static String GET_TRADE_PREPAY_INFO = TRADE + "/prepayInfo/{tradeNo}";
	/**
	 * 身份认证
	 */
	public final static String TRADE_CERTIFICATE = TRADE + "/certificate";

	/**
	 * 保税仓拆单后待支付的订单
	 */
	public final static String GET_TAX_TRADE_TOPAY_LIST = TRADE + "/getTaxTradeToPayList";
	/**
	 * 评价订单
	 */
	public final static String TRADE_ADD_JUDGEMENT = TRADE + "/judgement/add";

	/**
	 * 订单confirm确认收货
	 */
	public final static String TRADE_CONFIRM = TRADE + "/confirm"+"/{userId}/{tradeNo}";

	/**
	 * 确认购买
	 */
	public final static String TRADE_CONFIRM_BUY = TRADE + "/configTrade";
	
	
	/**
	 * 分销下单
	 */
	public final static String RETAILING_TRADE_CONFIRM_BUY = RETAILING + "/trade";
	
	/**
	 * 取消订单
	 */
	public final static String TRADE_CANCEL = TRADE + "/cancel"+"/{userId}/{tradeNo}";
	/**
	 * 支付宝回调payNotify
	 */
	public final static String PAY_NOTIFY = TRADE + "/payNotify";
	/**
	 * 微信回调payNotify
	 */
	public final static String WECHAT_PAY_NOTIFY = TRADE + "/wechat/payNotify";
	/**
	 * 支付宝退款
	 */
	public final static String REFUND = TRADE + "/refund";

	/**
	 * 支付宝退款回调接口
	 */
	public final static String REFUND_NOTIFY = TRADE + "/refundNotify";
	/**
	 * 获取运费标准
	 */
	public final static String GET_TRANSPORT_EXPENSE_LIMIT = TRADE + "/getTransportExpenseLimit";

	/**
	 * @Fields GET_TRADE_LIST : 交易列表
	 */
	public final static String GET_TRADE_LIST = TRADE + "/order/list";

	/**
	 * @Fields GET_TRADE_LIST : 交易列表
	 */
	public final static String TRADE_PAY_MARK = TRADE + "/payMark";

	/**
	 * @Fields TRADE_ORDER_ADD_COUPON : 订单绑定优惠券信息
	 */
	public final static String TRADE_ORDER_ADD_COUPON = TRADE + "/orderAddCoupon";
	/**
	 * 获取物流信息
	 */

	public final static String GET_ORDER_LOGISTICS_INFO = TRADE + "/getOrderLogisticsInfo/{userOrderNo}";
   
	/**
	 * 公共图片上传
	 */
	public final static String PUBLIC_IMAGE_UPLOAD = "publicImageUpload";
	/**
	 * wap站验证token是否有效
	 */
	public final static String CHECK_WAP_TOKEN_ISEFFECTIVE = COMMON+"checkToken";
	/**
	 * 验证token并且验证openId，绑定
	 */
	public final static String CHECK_TOKEN_AND_OPENID = "validationStatus";
	/**
	 * wap用户注销成功之后删除UserToken
	 */
	public final static String USER_OPERATE_USER_TOKEN = USER + "logout";
	
}
