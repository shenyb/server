package com.need.erp.wms.integration.constant;

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
	/**
	 * 新的商品管理
	 */
	public final static String BOPS_GOODS_NEW = "goodsNew";
	
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
	 * feed管理
	 */
	public final static String FEED_MANAGE="feed";
	

	/**
	 * feed评论管理
	 */
	public final static String FEED_MANAGE_COMMENT="feedComment";
    
    
    
    /**
     * 优惠券相关接口
     */
    public final static String COUPON = "coupon";

	
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
	 * 商品分类 
	 */
	public final static String BOPS_CATEGORIES = "categories";
	
	/**
	 * 商品品牌
	 */
	public final static String BOPS_BRAND = "brand";
	
	
	/**
	 * @Fields 菜单映射 :  
	 */
   
	public static Map<String, Integer> menu = new HashMap<String,Integer>();
	
	static{
		//商品管理
		menu.put("/goods/page_GET", 11);
		menu.put("/store/page_GET", 12);
		menu.put("/categories/page_GET", 14);
		menu.put("/brand/page_GET", 15);
		menu.put("/goodsNew/page_GET", 16);
		menu.put("/goodsNew/goodsPriceDetailsList_GET", 17);
		menu.put("", 13);
		
		//交易管理
		menu.put("/trade/page_GET", 21);
		menu.put("/tradeBatch/page_GET", 22);
		
		//系统管理
		menu.put("/user/page_GET", 41);
		menu.put("/role/page_GET", 42);
		menu.put("/auth/page_GET", 43);
		
	}

}
