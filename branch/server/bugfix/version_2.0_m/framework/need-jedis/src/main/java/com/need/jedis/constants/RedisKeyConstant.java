/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.need.jedis.constants;

/**
 *
 * @author 庆凯
 */
public class RedisKeyConstant {
    public static final String USER_OF_ID = "USER_OF_ID:";
    public static final String FEED_OF_ID = "FEED_OF_ID:";
    public static final String FEED_LIKE_OF_ID = "FEED_LIKE_OF_ID:";
    public static final String FEED_COMMENT_OF_ID = "FEED_COMMENT_OF_ID:";
    public static final String FEED_LABEL_OF_ID = "FEED_LABEL_OF_ID:";
    
    public static final String FEED_LIST_IDS_OF = "FEED_LIST_IDS_OF";
    public static final String FEED_LIST_IDS_OF_ALL = "FEED_LIST_IDS_OF_ALL:";
    public static final String FEED_LIST_IDS_OF_KOL = "FEED_LIST_IDS_OF_KOL:";
    public static final String FEED_LIST_IDS_OF_USER_ID = "FEED_LIST_IDS_OF_USER_ID:";
    public static final String USER_FEED_LIST_IDS_OF_USER_ID = "USER_FEED_LIST_IDS_OF_USER_ID:";
    public static final String FEED_LABEL_IDS_OF_FEED_ID = "FEED_LABEL_IDS_OF_FEED_ID:";
    public static final String FEED_LIKE_USER_IDS_OF_FEED_ID = "FEED_LIKE_USER_IDS_OF_FEED_ID:";
    public static final String FEED_COMMENTIDS_OF_FEED_ID = "FEED_COMMENTIDS_OF_FEED_ID:";
    
    public static final String FOLLOW_OF_USER = "FOLLOW_OF_USER:";
    
    public static final String MOBILE_TOKEN_OF_USER = "MOBILE_TOKEN_OF_USER:";
    public static final String WAP_TOKEN_OF_USER = "WAP_TOKEN_OF_USER:";
    
    public static final String MOBILE_OF_REGISTED = "MOBILE_OF_REGISTED:";//优化注册接口
    
    public static final String SYSTEM_SETTINGS = "SYSTEM_SETTINGS";
    
    public static final String WEIXIN_MP_ACCESS_TOKEN = "WEIXIN_ACCESS_TOKEN";//微信公众号accesstoken
    public static final int WEIXIN_MP_ACCESS_TOKEN_EXPIRE = 4000;//微信公众号accesstoken过期时间
    public static final String JSAPI_TICKET="WEIXIN_JSAPI_TICKET";//微信JSAPI_TICKET
    public static final String WEIXIN_MP_ACCESS_TOKEN_TIME="TOKEN_TIME";//
    public static final String WEIXIN_MP_JSAPI_TICKET_TIME="JSAPI_TIME";//微信JSAPI_TICKET 时间
    
    public static final String[] STRING_TYPE_KEYS = {MOBILE_TOKEN_OF_USER, WAP_TOKEN_OF_USER
    };
    public static final String[] HASH_TYPE_KEYS = {USER_OF_ID, FEED_OF_ID, FEED_LIKE_OF_ID, FEED_COMMENT_OF_ID, MOBILE_OF_REGISTED, 
        FOLLOW_OF_USER, SYSTEM_SETTINGS
    };
    public static final String[] LIST_TYPE_KEYS = {
    };
    public static final String[] SET_TYPE_KEYS = {FEED_LABEL_IDS_OF_FEED_ID
    };
    public static final String[] SORTEDSET_TYPE_KEYS = {FEED_LIST_IDS_OF_ALL, FEED_LIST_IDS_OF_USER_ID, USER_FEED_LIST_IDS_OF_USER_ID, 
        FEED_LIKE_USER_IDS_OF_FEED_ID, FEED_COMMENTIDS_OF_FEED_ID
    };
    /**
     * 替换用短ID替换userID
     */
    public static final String REPLACE_USER_ID = "REPLACE_USER_ID:";
}
