package com.need.wap.constant;

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
	 * @Fields APP_USER_INFO : TODO app端验证码过期时间
	 */
	public static final Integer APP_TIME_OUT_MIN = 30*60;

	/**
	 * @Fields APP_USER_INFO : 用户token的实效时间
	 */
	public static final int WAP_USER_TOKEN_DAYS = 15* 24 * 60 * 60;
	
	/**
	 * 用户状态码
	 */
	public static final Integer USER_SUCCESS = 200;//操作成功
	public static final Integer USER_MOBILE_NOT_REGISTER = 2100;//手机未注册
	public static final Integer USER_LOGIN_PWD_WRONG = 2101;//登录密码错误
	
	
	
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
	public static final int TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE_LIMIT =79*10;
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

}
