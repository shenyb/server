package com.need.api.constant;

/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年7月22日 下午7:13:12
 * @ClassName Constants
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月22日
 * @modify by reason:{方法名}:{原因}
 */
public class Constants {

	/**
	 * @Fields APP_USER_INFO : TODO app用户request中的userInfo信息
	 */
	public static final String APP_REQUEST_USER_INFO = "userInfoResult";

	/**
	 * @Fields APP_USER_INFO : TODO app端每页的数量默认是10个
	 */
	public static final Integer APP_EVERY_PAGE_SIZE = 10;

	/**
	 * @Fields APP_USER_INFO : TODO app端默认为第一页
	 */
	public static final Integer APP_EVERY_PAGE_NUM = 1;

	/**
	 * @Fields APP_USER_INFO : TODO app端验证码过期时间
	 */
	public static final Integer APP_TIME_OUT_MIN = 30 * 60;

	/**
	 * @Fields APP_USER_INFO : 用户token的实效时间
	 */
	public static final int APP_USER_TOKEN_DAYS = 15 * 24 * 60 * 60;

	/**
	 * feed内容字数限制
	 */
	public static final int FEED_MEMO_LENGHT = 280;

	/**
	 * feed评论字数限制
	 */
	public static final int FEED_COMMENT_LENGHT = 280;

	/**
	 * 用户状态码
	 */
	public static final Integer USER_SUCCESS = 200;// 操作成功
	public static final Integer USER_MOBILE_NOT_REGISTER = 2100;// 手机未注册
	public static final Integer USER_LOGIN_PWD_WRONG = 2101;// 登录密码错误

	/**
	 * notify success
	 */
	public static final String NOTIFY_SUCCESS = "success";
	/**
	 * notify fail
	 */
	public static final String NOTIFY_FAIL = "fail";

	/**
	 * @Fields VALIDATE_CODE_LENGTH : 验证码位数
	 */
	public static final int VALIDATE_CODE_LENGTH = 6;
	/**
	 * @Fields MOBILE_LENGTH : 手机号位数
	 */
	public static final int MOBILE_LENGTH = 11;
	/**
	 * 保税仓单件商品一次最多买的个数
	 */
	public static final int TAX_WAREHOUSE_BUY_ONCE_MAX = 10;
	/**
	 * 香港仓满280包邮
	 */
	public static final int TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE_LIMIT = 199*100;
	/**
	 * 香港商品运费20
	 */
	public static final int TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE = 20*100;
	/**
	 * 自营满80包邮
	 */
	public static final int TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE_LIMIT = 79*100;
	/**
	 * 自营运费10
	 */
	public static final int TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE = 10*100;
	/**
	 * 保税满9.9包邮
	 */
	public static final int TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE_LIMIT = 79*100;
	/**
	 * 保税运费10
	 */
	public static final int TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE = 10*100;
	
	
	public static final String FALSE = Boolean.FALSE.toString().toUpperCase();
	
	public static final String TRUE = Boolean.TRUE.toString().toUpperCase();
	
	public static final int ONE = 1;


	/**
	 * @Fields CHEAP_TRADE_EXPIRE : 团便宜下单过期时间
	 */
	public static final int CHEAP_TRADE_EXPIRE = 4 * 3600 * 1000;

	
	/**
	 * @Fields APP_REQUEST_DEVICE_CHANNEL : TODO app设备request中的userInfo信息
	 */
	public static final String APP_REQUEST_DEVICE_CHANNEL= "deviceChannel";
	/**
	 * 分销时按照商品总价的30%计算
	 */
	public static final double disCount=0.3d;
    
    //user缓存过期时间，单位秒
    public static final int USER_EXPIRES = 7 * 24 * 60 * 60;
    
    //feed缓存过期时间，单位秒
    public static final int FEED_EXPIRES = 3 * 30 * 24 * 60 * 60;
    
    //已过期的feed的临时缓存过期时间，单位秒
    public static final int EXPIRED_FEED_EXPIRES = 24 * 60 * 60;
    
    //feed流删数据的临界长度
    public static final int MAX_FEED_LIST_LENGTH = 1100;
    
    //feed流删除数据之后保留的长度
    public static final int BUFFERED_FEED_LIST_LENGTH = 1000;
    
    //feed详情页中默认评论的数量
    public static final int FEED_DETAIL_COMMENT_COUNT = 3;
    
    /**
	 * @Fields 新品过时时间72小时
	 */
	public static final long GOODS_TIME_OUT = 3 * 24 * 60 * 60*1000;
	
}
