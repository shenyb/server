package com.need.share.constant;

import java.io.File;

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
	 * @Fields APP_USER_INFO : TODO 每页的数量默认是10个
	 */
	public static final Integer EVERY_PAGE_SIZE = 10;

    
	/**
	 * @Fields APP_USER_INFO : TODO 每页的数量默认是10个
	 */
	public static final String PIC_DOMAIN_KEY = "/properties/constants.properties";
	
    public static final int PAGE_STATEMENT_LIMIT = 20;
    /**pageIndex 分页的开始索引*/
    public static final int PAGE_START_INDEX = 0;
    /**新鲜事评论显示限制数量*/
    public static final int FEED_COMMENT_LIMIT= 8;
    /**小说评论显示限制数量*/
    public static final int NOVEL_COMMENT_LIMIT= 8;
    /**视频评论显示限制数量*/
    public static final int SHOW_COMMENT_LIMIT= 8;
    /**标签下的手绘显示数量*/
    public static final int TOPIC_FEEDS_LIMIT = 9; 

    /**商品分享页面路径*/
    public static final String GOODS_PAGES_PATH = "pages"+File.separator+"goods"+File.separator;
   
    /**活动专题分享页面路径*/
    public static final String ACTIVITY_PAGES_PATH = "pages"+File.separator+"ops"+File.separator;
    /**html后缀*/
    public static final String HTML_SUFFIX = ".html";
    

	/**
	 * @Fields TEMPLATE_PATH : 模板存放路径
	 */
    public static final String TEMPLATE_PATH = "html";
    

}
