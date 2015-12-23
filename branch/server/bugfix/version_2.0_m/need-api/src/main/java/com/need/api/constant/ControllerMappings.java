package com.need.api.constant;

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
	 * @Fields USER : TODO 用户文件夹
	 */
	public final static String CHEAP = "cheap";

	/**
	 * @Fields DEVICE :设备文件夹
	 */
	public final static String DEVICE = "device/";

	/**
	 * @Fields DEVICE :设备文件夹
	 */
	public final static String COMMON = "common/";

	/**
	 * @Fields NEED :NEED文件夹
	 */
	public final static String NEED = "need/";

	/**
	 * @Fields KOL :KOL文件夹
	 */
	public final static String KOL = "kol/";
	/**
	 * @Fields PUB：公共
	 */
	public final static String PUB = "/pub/";

	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : TODO 根据用户id获得用户信息列表
	 */
	public final static String USERINFO_GET_USERINFOLIST = USER + "getUserInfolist";

	/**
	 * @Fields USER_REGISTER : 用户注册得接口
	 */
	public final static String USER_REGISTER = USER + "register";
	/**
	 * @Fields USER_LOGIN : 用户登录得接口
	 */
	public final static String USER_LOGIN = USER + "login";
	/**
	 * @Fields USER_LOGIN : 用户第三方登录接口
	 */
	public final static String USER_LOGIN_SNSLOGIN = USER + "login/SNSLogin";
	/**
	 * @Fields USER_LOGIN : 用户登录后在app内绑定第三方账号
	 */
	public final static String USER_SNS_BINDING = USER + "sns/binding";
	/**
	 * @Fields USER_LOGIN : 用户登录后获取第三代登录列表
	 */
	public final static String USER_SNS_BOUNDLIST = USER + "sns/boundList";
	/**
	 * @Fields USER_LOGIN : 获得手机通讯录中的need的用户列表
	 */
	public final static String USER_CONTACTSBOOK= USER + "contactsBook";
	/**
	 * @Fields USER_LOGIN : 用户登录后在app内解除绑定第三方账号
	 */
	public final static String USER_SNS_UNBIND = USER + "sns/unbind";
	/**
	 * @Fields USER_LOGIN : 用户第三方登录绑定手机号接口
	 */
	public final static String USER_LOGIN_BOUND_MOBILE = USER + "register/SNSRegister";
	/**
	 * @Fields USER_LOGIN : 用户快捷登录得接口
	 */
	public final static String USER_FAST_LOGIN = USER + "login/fastLoginBySMS";
	/**
	 * @Fields USER_CHECK_VALIDATE_CODE : 用户登录得接口
	 */
	public final static String USER_CHECK_VALIDATE_CODE = USER + "register/checkValidateCode";

	/**
	 * 用户更新密码
	 */
	public final static String USER_UPDATE_LOGIN_PWD = USER + "updateLoginPwd";

	/**
	 * 用户找回密码
	 */
	public final static String USER_FIND_LOGIN_PWD = USER + "resetPwd";

	/**
	 * 用户找回密码时验证手机是否存在且唯一并且发送验证码
	 */
	public final static String USER_CHECK_MOBILE_AND_SEND_VALIDATE_CODE = USER + "checkMobileAndSendValidateCode";

	/**
	 * @Fields USER_CHECK_NICK_NAME_EXISTS : 校验用户昵称是否唯一
	 */
	public final static String USER_CHECK_NICK_NAME_EXISTS = USER + "checkNickNameExists";

	/**
	 * @Fields USER_REGISTER : 校验用户昵称是否唯一
	 */
	public final static String USER_UPDATE_NICK_NAME = USER + "updateNickname";

	/**
	 * @Fields USER_REGISTER : 修改用户头像
	 */
	public final static String USER_UPDATE_PROFILE_KEY = USER + "updateProfileKey";

	/**
	 * @Fields DEVICE_GET_VERSION ：获得最新版本信息
	 */
	public final static String DEVICE_GET_VERSION = DEVICE + "getVersion";

	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : TODO 根据用户id获得用户信息
	 */
	public final static String USERINFO_GET_USERINFO = USER + "getUserInfo";

	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : TODO 根据用户id获得用户信息
	 */
	public final static String USERINFO_GET_USER_SELF_INFO = USER + "getUserSelfInfo";
	
	/**
	 * 分销用户信息
	 */
	public final static String USERINFO_GET_DISTRIBUTION = USER + "distributionUserInfo";
	/**
	 * @Fields USER_NEED_COMMENT : TODO 用户已经反馈
	 */
	public final static String USER_FEEDBACK = DEVICE + "feedback";

	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : TODO 发送验证码
	 */
	public final static String SEND_VALIDATION_CODE = COMMON + "sendValidationCode";

	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : TODO 快捷登录，发送验证码
	 */
	public final static String FAST_SEND_VALIDATION_CODE = COMMON + "fastSendValidationCode";

	/**
	 * 客户端激活接口
	 */
	public final static String DEVICE_REGISTER = DEVICE + "register";

	/**
	 * 图片质量自适应
	 */
	public final static String DEVICE_PIC_ADAPTER = DEVICE + "picAdapter";

	/**
	 * 用户注销成功之后修改UserToken
	 */
	public final static String USER_OPERATE_USER_TOKEN = USER + "logout";

	/**
	 * 用户获得上传头像的凭证Token
	 */
	public final static String USER_GET_UPLOAD_TOKEN = COMMON + "getUploadToken";

	/**
	 * 用户数据统计
	 */
	public final static String USER_DATA_STATISTICS = USER + "dataStatistics";

	/**
	 * 交易
	 */
	public final static String TRADE = "trade";

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
	 * 获取购物车数量
	 */
	public final static String TRADE_CART_COUNT = TRADE + "/cart/count";

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
	public final static String GET_TRADE_ORDER_DETAIL = TRADE + "/detail";

	/**
	 * 订单支付信息
	 */
	public final static String GET_TRADE_PREPAY_INFO = TRADE + "/prepayInfo";
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
	public final static String TRADE_CONFIRM = TRADE + "/confirm";

	/**
	 * 确认购买
	 */
	public final static String TRADE_CONFIRM_BUY = TRADE + "/configTrade";
	/**
	 * 取消订单
	 */
	public final static String TRADE_CANCEL = TRADE + "/cancel";
	/**
	 * 支付宝回调payNotify
	 */
	public final static String PAY_NOTIFY = TRADE + "/payNotify";
	/**
	 * 微信回调payNotify
	 */
	public final static String WECHAT_PAY_NOTIFY = TRADE + "/wechat/payNotify";
	
	/**
	 *  WAP	微信回调payNotify
	 */
	public final static String WECHAT_PAY_NOTIFY_WAP = TRADE + "/wechat/payNotifyForWap";
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

	public final static String GET_ORDER_LOGISTICS_INFO = TRADE + "/getOrderLogisticsInfo";

	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : TODO 根据用户id获得用户信息
	 */
	public final static String FOCUSKOLLIST = USER + "kolList";
	/**
	 * @Fields FANSLIST : TODO 根据用户粉丝列表
	 */
	public final static String FANSLIST = USER + "fansList";

	/**
	 * 运营模块
	 */
	public final static String OPS = "ops/";
	/**
	 * 首页运营位
	 */
	public final static String HOME_OPS = OPS + "home";

	/**
	 * 世间BANNER运营位
	 */
	public final static String SHIJIAN_BANNER_OPS = OPS + "shijian/banner";

	/**
	 * 行家BANNER运营位
	 */
	public final static String KOL_BANNER_OPS = OPS + "kol/banner";

	/**
	 * 世间SCROLL运营位
	 */
	public final static String SHIJIAN_SCROLL_OPS = OPS + "shijian/scroll";

	/**
	 * 启动页运营位
	 */
	public final static String START_BANNER_OPS = OPS + "start/banner";

	/**
	 * 新欢banner运营位
	 */
	public final static String XINHUAN_BANNER_OPS = OPS + "xinhuan/banner";

	/**
	 * 新欢scroll运营位
	 */
	public final static String XINHUAN_SCROLL_OPS = OPS + "xinhuan/scroll";

	/**
	 * 新欢专区
	 */
	public final static String START_PREFECTURE_OPS = OPS + "xinhuan/prefecture";

	/**
	 * 世间专题分类列表
	 */
	public final static String SHIJIAN_TOPIC_RECOMMEND = OPS + "shijian/topic/recommend";

	/**
	 * 点击more
	 */
	public final static String SHIJIAN_MORE_TOPIC = OPS + "shijian/moreTopicList";

	/**
	 * 获取专题的URL
	 */
	public final static String TOPIC_URL = "topic/getTopicUrl";

	public final static String HOT_GOODS = OPS + "goods/hot";

	public final static String TOPIC_DETAIL = OPS + "topic/detail";
	/**
	 * @Fields OPS_XINHUAN_SHOW:获取新欢页面的专场列表
	 */
	public final static String OPS_XINHUAN_SHOW = OPS + "xinhuan/show";

	public final static String GOODS = "goods/";

	public final static String GOODS_DETAIL = GOODS + "detail";

	public final static String GOODS_GETGOODS_LIST_BYSCENEID = GOODS + "scene/list";

	public final static String GOODS_PARAMS = GOODS + "params";

	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : 获取用户拥有的商品信息
	 */
	public final static String USERTRADEGOODSLIST = USER + TRADE + "/goodsList";

	public final static String SELECTED_GOODS = OPS + "goods/recommend";

	/**
	 * 分销模块
	 */
	public final static String DISTRIBUTION = "distribution";

	/*
	 * need
	 * 
	 * public final static String NEED="need/";
	 */
	/**
	 * 获取新欢头部3个运营位
	 */
	public final static String XINHUAN_OPS_POSITION = "ops/xinhuan/recommend";

	/**
	 * 获取新欢运营位商品列表
	 */
	public final static String XINHUAN_GOODS_LIST = "ops/xinhuan/goodsList";
	/**
	 * 用户need商品
	 */

	public final static String USER_NEED_GOODS = NEED + "goods/userNeed";

	public final static String KOL_NEED_GOODS = NEED + "goods/kolNeed";

	/**
	 * 用户取消need
	 */
	public final static String USER_CANCEL_NEED = NEED + "goods/cancelNeed";

	/**
	 * 用户关注行家
	 */
	public final static String USER_FOCUS_KOL = NEED + "user/focusKol";

	/**
	 * 用户批量关注行家
	 */
	public final static String USER_FOCUS_KOL_LIST = NEED + "user/focusKolList";
	/**
	 * 取消关注
	 */
	public final static String USER_CANCEL_FOCUS = NEED + "user/cancelFocusKol";

	/**
	 * 是否关注过
	 */
	public final static String IS_FOCUS_KOL = NEED + "user/isFocus";

	/**
	 * 获取feed流
	 */
	public final static String HOME_FEED_LIST = NEED + "home/list";
	/**
	 * 给feed流添加评论
	 */
	public final static String ADD_FEED_COMMENT = NEED + "feed/addComment";

	/**
	 * 给feed评论list
	 */
	public final static String FEED_COMMENT_LIST = NEED + "feed/commentList";

	/**
	 * 其他运营位置
	 */
	public final static String OPS_SHOP_BANNER = OPS + "shop/banner";

	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : 获取用户Need的商品的列表
	 */
	public final static String USERGOODSLIST = USER + NEED + "goodsList";

	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : 获取行家类别列表
	 */
	public final static String GETKOLCATEGORYLIST = KOL + "category/list";

	/**
	 * @Fields INFOLOCATION_GET_CITY_LIST_BY_PROVINCEID : 获取行家类别列表
	 */
	public final static String GETGOODSSCENELIST = GOODS + "sceneList";

	/**
	 * 获取商品基本信息
	 */
	public final static String GOODS_PROFILE = GOODS + "profile";

	/**
	 * 商品推荐评价列表
	 */
	public final static String GOODS_OPS_JUDGEMENTLIST = GOODS + "judgement/opsList";

	/**
	 * 商品评价所有列表
	 */
	public final static String GOODS_JUDGEMENT_LSIT = GOODS + "judgement/list";

	/**
	 * 删除收货地址
	 */
	public final static String DELETE_ADDRESS = TRADE + "/address/delete";
	/**
	 * 选择默认地址
	 */
	public final static String DEFAULT_ADDRESS = TRADE + "/address/default";

	/**
	 * 全国数据获取
	 */
	public final static String PUBLIC_GET_LOGISTICS = PUB + "getLogistics";

	/**
	 * 举报信箱
	 */
	public final static String PUBLIC_RECEIVE_REPORT = COMMON + "receiveReport";

	/**
	 * @Fields 返回配置参数
	 */
	public final static String PUBLIC_SERVICE_STATUS = COMMON + "getServiceStatus";

	/**
	 * 根据行家标签获取行家列表
	 */
	public final static String GET_KOL_LIST = KOL + "getKolListByCategory";

	/**
	 * 用户数据统计接口
	 */
	public final static String GET_EXTERN_INFO = USER + "getExternInfo";

	/**
	 * 优惠券相关接口
	 */
	public final static String COUPON = "coupon";
    
    /**
     * 关注目录
     */
    private final static String USER_FOLLOW = "user/follow";
    
    /**
     * 添加关注
     */
    public final static String USER_FOLLOW_ADD = USER_FOLLOW + "/add";
    
    /**
     * 取消关注
     */
    public final static String USER_FOLLOW_DEL = USER_FOLLOW + "/del";
    
    /**
     * 关注列表
     */
    public final static String USER_FOLLOW_LIST = USER_FOLLOW + "/list";
    
    /**
     * feed目录
     */
    private final static String FEED = "user/feed";
    
    /**
     * 添加feed
     */
    public final static String FEED_ADD = FEED + "/publish";
    
    /**
     * 删除feed
     */
    public final static String FEED_DEL = FEED + "/del";
    
    /**
     * feed列表
     */
    public final static String FEED_LIST = FEED + "/list";
    
    /**
     * 用户feed列表
     */
    public final static String FEED_USER_LIST = FEED + "/userList";
    
    /**
     * feed详情
     */
    public final static String FEED_DETAIL = FEED + "/detail";
    
    /**
     * feed举报
     */
    public final static String FEED_REPORT = FEED + "/report";
    
    /**
     * feed点赞
     */
    public final static String FEED_LIKE = FEED + "/like";
    
    /**
     * feed取消点赞
     */
    public final static String FEED_DISLIKE = FEED + "/dislike";
    
    /**
     * feed评论目录
     */
    private final static String FEED_COMMENT = "user/feed/comment";
    
    /**
     * feed评论目录
     */
    public final static String FEED_COMMENT_ADD = FEED_COMMENT + "/add";
    
    /**
     * feed评论目录
     */
    public final static String FEED_COMMENT_DEL = FEED_COMMENT + "/del";
    
    /**
     * feed评论目录
     */
    public final static String FEED_COMMENT_LISTS = FEED_COMMENT + "/list";
	

	/**
	 * 用户优惠券列表接口
	 */
	public final static String COUPON_LIST = COUPON + "/list";

	/**
	 * 用户可用优惠券列表接口
	 */
	public final static String COUPON_AVAILABLE = COUPON + "/available";

	/**
	 * 用户可用优惠券列表接口
	 */
	public final static String COUPON_AVAILABLE_TRADENO = COUPON_AVAILABLE + "/tradeNo";

	/**
	 * 用户可用优惠券列表接口
	 */
	public final static String COUPON_AVAILABLE_COST = COUPON_AVAILABLE + "/cost";

	/**
	 * 用户分享优惠券信息接口
	 */
	public final static String COUPON_SHARE = COUPON + "/share";

	/**
	 * 用户个人中心分享优惠券信息接口
	 */
	public final static String COUPON_SHARE_REGISTER = COUPON_SHARE + "/register";

	/**
	 * 用户支付成功分享优惠券信息接口
	 */
	public final static String COUPON_SHARE_TRADE = COUPON_SHARE + "/trade";

	/**
	 * 用户领取优惠券接口
	 */
	public final static String COUPON_RECEIVE = COUPON + "/receive";

	/**
	 * 用户领取优惠券列表接口
	 */
	public final static String COUPON_RECEIVE_LIST = COUPON + "/receive/list";

	/**
	 * 用户兑换优惠券接口
	 */
	public final static String COUPON_EXCHANGE = COUPON + "/exchange";
	/**
	 * cheap列表
	 */
	public final static String CHEAP_LIST = CHEAP + "/list";

	/**
	 * cheap列表
	 */
	public final static String CHEAP_CLOSED_LIST = CHEAP + "/closed";
	/**
	 * 进行中cheap列表
	 */
	public final static String CHEAP_AVAILABLE_LIST = CHEAP + "/available";
	/**
	 * 分销商品
	 */
	public final static String DISTRIBUTION_SHARE = DISTRIBUTION + "/share";
	/**
	 * 工资条获取分销列表
	 */
	public final static String GET_DISTRIBUTION_GOODSLIST = DISTRIBUTION +"/simpleGoodsList";
	/**
	 * 获取用户收益
	 */
	public final static String GET_INCOME_LIST = DISTRIBUTION +"/incomeList";
	
	/**
	 * 获取用户的佣金消费记录
	 */
	public final static String GET_CONSUME_LIST = DISTRIBUTION +"/consumeList";
	
	/**
	 * 获取用户的佣金消费记录
	 */
	public final static String GET_SUM_INCOME = DISTRIBUTION +"/myIncome";
	
	/**
	 * 支付时获取可用余额
	 */
	public final static String GET_AVAILABLE_INCOME = TRADE+"/"+DISTRIBUTION +"/availableIncome";
	
	/**
	 * 商品检索分类
	 */
	public final static String GOODS_CATEGORY_LIST = GOODS + "category/list";
	
	/**
	 * 某一分类下的商品list
	 */
	public final static String GOODS_LIST_BY_CATEGORY = GOODS + "category/goodsList";

}
