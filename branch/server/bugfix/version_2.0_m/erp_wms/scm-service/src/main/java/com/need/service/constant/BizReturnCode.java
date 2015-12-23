package com.need.service.constant;

public final class BizReturnCode {

	/**
	 * 系统常量------------------------------------------------------------------
	 */
	public final static int SUCCESS = 200;
	/**
	 * 系统异常
	 */
	public final static int SYSTEM_ERR = 1;
	/**
	 * 操作失败
	 */
	public final static int SYSTEM_OPERATE_ERR = 2;
	/**
	 * 权限不够
	 */
	public final static int SYSTEM_AUTH_ERR = 3;
	/**
	 * userToken不能为空
	 */
	public final static int SYSTEM_USER_TOKEN_NULL = 4;
	/**
	 * 用户鉴权失败
	 */
	public final static int SYSTEM_USER_AUTH_ERR = 5;
	/**
	 * 发送验证码失败
	 */
	public final static int SYSTEM_SEND_VALIDATE_CODE_ERR = 7;

	/**
	 * 字段校验不通过
	 */
	public final static int FIELD_VALIDATE_ERR = 9;
    
	/**
	 * 用户ID不能为空
	 */
	public final static int USERID_NOT_EXIST = 2015;

	/**
	 * begin
	 * -------------------------------------------------------------------------
	 * ---------------- 商品相关异常
	 */
	/**
	 * 商品编号不能为空
	 */
	public final static int GOODS_NOT_EXIST = 3001;

	/**
	 * 场景ID不能为空
	 */
	public final static int SCENE_NOT_EXIST = 3002;

	/**
	 * 无此商品
	 */
	public final static int NOT_FIND_GOODS = 3003;

	/**
	 * 无此商品场景
	 */
	public final static int NOT_SCENE_ID = 3004;

	/**
	 * 库存不足
	 */
	public final static int GOODS_STORE_NOT_ENOUGH = 3005;
	
	/**
	 * 商品分类不能为空
	 */
	public final static int GOODS_INDEX_CATEGORY_NOT_NULL = 3021;
	
	/**
	 * 字段校验不通过
	 */
	public final static int TOPIC_CATEGORY_CHECK = 5018;
    
    
	/**
	 * begin
	 * -------------------------------------------------------------------------
	 * ---------------- 优惠券相关异常
	 */
	/**
	 * 优惠券不存在
	 */
	public final static int COUPONNO_NOT_EXIST = 6001;
    
	/**
	 * 未达到优惠券最低消费额度
	 */
	public final static int COUPON_COST_NOTENOUGH = 6002;
    
	/**
	 * 优惠券已冻结
	 */
	public final static int COUPON_FROZE = 6003;
    
	/**
	 * 优惠券已使用
	 */
	public final static int COUPON_USED = 6004;
    
	/**
	 * 不是您的优惠券
	 */
	public final static int COUPON_NOT_OWN = 6005;
    
	/**
	 * 优惠券已过期
	 */
	public final static int COUPON_EXPIRED = 6006;
    
	/**
	 * 优惠券未开始
	 */
	public final static int COUPON_NOT_BEGIN = 6007;
    
	/**
	 * 优惠券已失效
	 */
	public final static int COUPON_INVALID = 6008;
    
	/**
	 * 优惠券今日领取已达到限额
	 */
	public final static int COUPON_DAILY_OUT = 6009;
    
	/**
	 * 优惠券已领完
	 */
	public final static int COUPON_OUT = 6010;
    
	/**
	 * 优惠券模板id不能为空
	 */
	public final static int COUPON_TEMPLATE_ID_NOT_EXIST = 6011;
    
	/**
	 * 优惠券消费金额不能为空
	 */
	public final static int COUPON_COST_NOT_EXIST = 6012;
    
	/**
	 * 优惠券领取已总数达到限额
	 */
	public final static int COUPON_REVEICE_OUT = 6013;
    
	/**
	 * 无法领取自己分享的优惠券
	 */
	public final static int COUPON_REVEICE_SELF = 6014;
    
	/**
	 * 只有新人才能领取该优惠券
	 */
	public final static int COUPON_FOR_NEW = 6015;
    
	/**
	 * 分享用户不存在，无法领取优惠券
	 */
	public final static int COUPON_SHARE_USER_NOT_EXIST = 6016;

	/**
	 * 优惠券模板已生效不允许修改
	 */
	public final static int COUPON_VALID_UPDATE = 6017;

	/**
	 * 优惠券模板状态不正确
	 */
	public final static int COUPON_STATUS_ERROR = 6018;

	/**
	 * 优惠券模板金额不正确
	 */
	public final static int COUPON_VALUE_ERROR = 6019;

	/**
	 * 优惠券模板时间不正确
	 */
	public final static int COUPON_TIME_ERROR = 6020;

	/**
	 * 优惠券模板编号不能重复
	 */
	public final static int COUPON_NO_DUPLICATE = 6021;

	/**
	 * 优惠券不存在
	 */
	public final static int COUPON_NOT_EXIST = 6022;

	/**
	 * 优惠券兑换编号不能重复
	 */
	public final static int COUPON_EXCHANGE_CODE_DUPLICATE = 6023;
    
	/**
	 * end
	 * -------------------------------------------------------------------------
	 * ---------------- 优惠券相关异常
	 */
	/**
	 * 该商品已经处在分销当中，不能重复
	 */
	public final static int DISTRIBUTION_EXIST = 6101;
	
	
	/**
	 * 字段校验不通过
	 */
	public final static int TOPIC_CODE_EXIST = 7015;
	/**
	 * 退款金额不能为空
	 */
	public final static int REFUND_AMOUNT_NOT_NULL = 7016;
	/**
	 * 物料号不能为空
	 */
	public final static int MATERIALNO_NOT_NULL = 7017;
	/**
	 * 服务商不能为空
	 */
	public final static int LOGISTICSSERVICEID_NOT_NULL = 7018;
	/**
	 * 物流编号不能为空
	 */
	public final static int LOGISTICSNUMS_NOT_NULL = 7019;

	/**
	 * begin
	 * -------------------------------------------------------------------------
	 * ---------------- 团便宜相关异常
	 */
	/**
	 * 团便宜未开团
	 */
	public final static int CHEAP_NOT_OPEN = 8001;

	/**
	 * 团便宜已关闭
	 */
	public final static int CHEAP_CLOSED = 8002;

	/**
	 * 团便宜未成团
	 */
	public final static int CHEAP_NOT_COMPLETE = 8003;

	/**
	 * 团便宜已过期
	 */
	public final static int CHEAP_TRADE_EXPIRED = 8004;

	/**
	 * 团便宜尚未参团
	 */
	public final static int CHEAP_NOT_JOIN = 8005;

	/**
	 * 团便宜商品已经购买
	 */
	public final static int CHEAP_TRADED = 8006;
    
	/**
	 * 团便宜添加失败
	 */
	public final static int CHEAP_ADD_FAIL = 8007;
	/**
	 * 该时间段已经有改商品的团便宜
	 */
	public final static int CHEAP_EXISTE = 8008;
	/**
	 * 超过规定的持续时间（240小时）
	 */
	public final static int CHEAP_DURING_TOO_LONG = 8009;
	/**
	 * 参团人数不能少于2
	 */
	public final static int CHEAP_COUNT_TOO_SMALL = 8010;
	
	/**
	 *该品牌已有，请勿重新添加
	 */
	public final static int BRAD_EXIST = 3101;
	
	/**
	 *改组合不存在
	 */
	public final static int GOODS_GROUP_NO_EXIST = 9001;
	
	/**
	 *改组合不存在商品
	 */
	public final static int GOODS_GROUP_NO_GOODS = 9002;
	
	/**
	 *商品组合不能打包保税仓商品
	 */
	public final static int GOODS_GROUP_INCLUDE_TAX_WAREHOUSE= 9003;
	
	/**
	 *仓库不一致
	 */
	public final static int GOODS_GROUP_WAREHOSE_NO_SAME = 9004;
	
	/**
	 *仓库不一致
	 */
	public final static int NO_GOODS = 9005;
	
	/**
	 *打包商品不能参加促销活动
	 */
	public final static int CONTAIN_PACK_GOODS = 9006;
	

}
