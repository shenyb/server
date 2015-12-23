package com.need.operation.constant;

import java.util.HashMap;
import java.util.Map;

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
	 * @Fields USER : TODO 商品分销
	 */
	public final static String DISTRIBUTION = "distribution";
	
	/**
	 * @Fields USER : TODO 设备
	 */
	public final static String DEVICE = "device/";


	/**
	 * @Fields USER_LOGIN : 用户登录得接口
	 */
	public final static String USER_LOGIN = "session";
	/**
	 * @Fields Auth : TODO 权限
	 */
	public final static String AUTH = "auth";
	/**
	 * 行家分类
	 */
	public final static String EXPERTCLASSIFY = "expertclassify";
	
	/**
	 * 场景分类
	 */
	public final static String SCENE = "scene";
	

	/**
	 * 运营位管理
	 */
	public final static String OPS_MANAGER="opmanage";
	
	
	/**
	 * 运营位分配
	 */
	public final static String OPS_ASSIGNMENT="opassign";
	
	/**
	 * need1.1运营位分配专题
	 */
	public final static String OPS_ASSIGNMENT_TOPIC="assignTopic";
	
	/**
	 * 人气商品管理
	 */
	public final static String OPS_HOT_GOODS="hotgoods";
	
	/**
	 * 精选商品管理
	 */
	public final static String OPS_SELECTED_GOODS="selectedgoods";
	
	/**
	 * 商品场景管理
	 */
	public final static String GOODS_SCENE = "goodsScene";
	
	/**
	 * 行家管理
	 */
	public final static String KOL_MANAGER = "kol";
	/**
	 * 行家管理
	 */
	public final static String TOPIC = "topic";
	
	/**
	 * 商品管理
	 */
	public final static String BOPS_GOODS_MANAGER = "goods";
	
	public final static String OPS_XINHUAN_GOODS = "opsXinhuanGoods";
	
	/**
	 * 仓库管理
	 */
	public final static String GOODS_STORE_MANGER = "store";
	
	/**
	 * 角色管理
	 */
	public final static String ROLE_MANAGER = "role";
	
	/**
	 * 用户管理
	 */
	public final static String BOPS_USER_MANAGER = "user";
	
	/**
	 * 用户修改密码
	 */
	public final static String BOPS_USER_CHANGE_PWD = "/changePwd";
	
	/**
	 * 获得前台用户信息
	 */
	public final static String API_USER_BASE = "userbase";
	
	/**
	 * 公共图片上传
	 */
	public final static String PUBLIC_IMAGE_UPLOAD = "publicImageUpload";
	
	
	/**
	 * 获取图片域名
	 */
	public final static String PIC_DOMAIN = "picDomian";
	
	/**
	 * 文本图片上传
	 */
	public final static String TXT_IMAGE_UPLOAD = "richTextUpload";
	/**
	 * 全国数据获取
	 */
	public final static String PUBLIC_GET_LOGISTICS = "getLogistics";
	/**
	 * 交易管理
	 */
	public final static String TRADE = "trade";
	
	/**
	 * 交易批次管理
	 */
	public final static String TRADEBATCH = "tradeBatch";
	/**
	 * 支付宝退款
	 */
	public final static String REFUND = TRADE + "/refund";

	/**
	 * 支付宝退款回调接口
	 */
	public final static String REFUND_NOTIFY = TRADE + "/refundNotify";

	/**
	 * 商品图片上传
	 */
	public final static String GOODS_IMAGE_UPLOAD = "/goodsImageUpload";
	/**
	 * 场景图片上传
	 */
	public final static String SCENE_IMAGE_UPLOAD = "/sceneImageUpload";
	
	/**
	 * 运营位管理
	 */
	public final static String OPS_POSITION_MANAGER = "/opsPosition";
	
	/**
	 * 专题图片上传
	 */
	public final static String TOPIC_IMAGE_UPLOAD = "/topicImageUpload";
	/**
	 * 行家分类图片上传
	 */
	public final static String KOL_CATEGORY_IMAGE_UPLOAD = "/kolCategoryImageUpload";
	/**
	 * 意见反馈
	 */
	public final static String FEED_BACK = DEVICE+"feedback/page";

	/**
	 * 旧版feed管理
	 */
	public final static String FEED_MANAGE="feed";
	
	/**
	 * 新版Feed管理
	 */
	public final static String USER_FEED_MANAGE = "user/feed";
	

	/**
	 * feed评论管理
	 */
	public final static String FEED_MANAGE_COMMENT="feedComment";
    
    
    
    /**
     * 优惠券相关接口
     */
    public final static String COUPON = "coupon";

    /**
     * 团便宜相关接口
     */
    public final static String CHEAP = "cheap";
    
    
    
    /**
     * 优惠券兑换相关接口
     */
    public final static String COUPON_EXCHANGE = COUPON + "/exchange";

    /**
     * 优惠券列表
     */
    public final static String COUPON_DISCOUNT = COUPON + "/discount";

	
	/**
	 * 专题分类管理
	 */
    public final static String TOPIC_CATEGORY= "topic/category";

	
	/**
	 * @Fields WEB_TEMPLATE_OPERATE :  
	 */
	public final static String WEB_TEMPLATE_OPERATE = "template";

	
	
	/**
	 * 专题分类管理
	 */
	public final static String TOPIC_CATEGORY_RELATION = "topicCategory";

	
	/**
	 * @Fields WEB_TEMPLATE_OPERATE :  
	 */
	public final static String WEB_SPECIAL = "special";
	

	/**
	 * @Fields WEB_TEMPLATE_OPERATE :  
	 */
	public final static String WEB_INDEX = "/index";
	
	
	/**
	 * @Fields 菜单映射 :  
	 */
   
	public static Map<String, Integer> menu = new HashMap<String,Integer>();
	
	
	/***
	 * 商品组合管理
	 */
	public final static String BOPS_GOODS_GROUP = "goodsGroup";
	
	/***
	 * 商品组合管理
	 */
	public final static String OPS_INDEX_CATEGORY = "indexCategory";
	
	/***
	 * 系统设置
	 */
	public final static String SYSTEM_SETTING = "systemSetting";
	
	static{
		//运营管理
		menu.put("/opassign_GET", 11);
		menu.put("/special/page_GET", 12);
		menu.put("/topic/category/page_GET", 13);
		menu.put("/opsPosition/page_GET", 14);
		
		//行家管理
		menu.put("/expertclassify/page_GET", 21);
		menu.put("/kol/page_GET", 22);
		
		//优惠券管理
		menu.put("/coupon/page_GET", 31);
		menu.put("/coupon/statistics_GET", 32);
		menu.put("/coupon/exchange/page_GET", 33);
		menu.put("/coupon/discount/page_GET", 34);
		
		//团便宜管理
		menu.put("/cheap/page_GET", 61);
		menu.put("/cheap/cheapStat_GET", 62);
		
		//商品组合管理
		menu.put("/goodsGroup/page_GET", 71);
		//menu.put("/cheap/cheapStat_GET", 64);
		
		//商品分销管理
		menu.put("/distribution/page_GET", 91);
		menu.put("/distribution/goods/page_GET", 92);
		menu.put("/distribution/user/page_GET", 93);
				
		//系统管理
		menu.put("/user/page_GET", 41);
		menu.put("/role/page_GET", 42);
		menu.put("/auth/page_GET", 43);
		menu.put("/systemSetting/page_GET", 44);
		
		//安全中心
		menu.put("/feed/page_GET", 51);
		menu.put("/feedComment/page_GET", 52);
		menu.put("/device/feedback/page_GET", 53);
		
		//feed管理
		menu.put("/user/feed/page_GET", 101);
		menu.put("/user/feed/report/page_GET", 102);
		menu.put("/user/feed/handledReport/page_GET", 103);
		//商品管理
		menu.put("/indexCategory/page_GET", 112);
		menu.put("/goods/page_GET", 111);
	}
}
