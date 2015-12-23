
package com.need.biz.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;


/**
 * 
 * <p>
 * </p>
 * 
 * @author david.tan 2015年8月7日 上午11:26:20
 * @ClassName BizCodeUtil
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: david.tan 2015年8月7日
 * @modify by reason:{方法名}:{原因}
 */
public class BizCodeUtil {

	/** 类变量---------------------------------------------------------------- */
	/** 时间串，精确到分 */
	static SimpleDateFormat DF_DATETIME = new SimpleDateFormat("yyyyMMddHHmm");
	/** 时间串，精确到天 */
	static SimpleDateFormat DF_DATE = new SimpleDateFormat("yyMMdd");
	
	/** 时间串，精确到秒 */
	static SimpleDateFormat DF_LONG_DATETIME = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	static int MD5_SUB_INDEX = 13;
	static int MD5_SHORT_SUB_INDEX =10;
	static String SPLIT_SIGN = "|";
	
	static int USERID_LENGTH=25;
	
    static int USER_ORDER_RANDOM_LENGTH=8; 
    static int USER_ORDER_RANDOM=99999999;
    static String USER_PLACE_HOLDER="0";
    static Random randomBase=new Random(USER_ORDER_RANDOM);
	
    
    static int USER_NICKNAME_RANDOM=9999;
    static int USER_NICKNAME_RANDOM_LENGTH=3;
    
    
    static int 	GOODS_GROUP_RANDOM=99999999;
    static int GOODS_GROUP_RANDOM_LENGTH=8;
    
    static final  AtomicInteger RANDOM_WATCH=new AtomicInteger(); 
    
    static final int  MAX_NUM=2147483640;
    
    static volatile  int count =0;
    
	/**
	 * 产生设备标示ID 
	 * @author david.tan 2015年8月7日 下午4:56:46
	 * @Method: generateDeviceId 
	 * @Description: TODO
	 * @param mac
	 * @return 
	 * @throws
	 */
	public static String generateDeviceId(String mac){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(mac+SPLIT_SIGN+timefator).substring(0, MD5_SUB_INDEX);
		
		
		
		return dateFormat+md5Random;
		
	}
	
	
	
	/**
	 * 产生用户ID
	 * @author david.tan 2015年8月7日 上午11:37:23
	 * @Method: generateUserId 
	 * @Description: TODO
	 * @param loginName 登录名，比如手机号，昵称等
	 * @return  
	 * @throws
	 */ 
	public static String generateUserId(String loginName){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(loginName+SPLIT_SIGN+timefator).substring(0, MD5_SUB_INDEX);
		
		
		
		return dateFormat+md5Random;
	}
	
    /**
     * 产生商品ID
     * TODO 商品数字编号待定
     * @author david.tan 2015年8月7日 下午5:06:01
     * @Method: generateGoodsId 
     * @Description: TODO
     * @param goodsName
     * @return 
     * @throws
     */
	public  static String generateGoodsId(String goodsName){
		String md5Random=MD5Util.MD5Encode(goodsName).substring(0, MD5_SHORT_SUB_INDEX);
		
		return md5Random;
	}
	
	
	/**
	 * 产生交易号
	 * @author david.tan 2015年8月7日 下午4:43:20
	 * @Method: generateTradeNo 
	 * @Description: TODO
	 * @param goodsName
	 * @param userId
	 * @return 
	 * @throws
	 */
	public static String generateTradeNo(String goodsName,String userId){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
	     /**random watch */
		int randomWatch=RANDOM_WATCH.incrementAndGet();
         String md5Random=MD5Util.MD5Encode(goodsName+SPLIT_SIGN+timefator+SPLIT_SIGN+ userId+SPLIT_SIGN+randomWatch).substring(0, MD5_SUB_INDEX);

		return dateFormat+md5Random;	
	}
	
//	public static String generateTradeNo2(String goodsName,String userId){
//		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
//		long timefator=Calendar.getInstance().getTimeInMillis();
//	     /**random watch */
//		count++;
//        String md5Random=MD5Util.MD5Encode(goodsName+SPLIT_SIGN+timefator+SPLIT_SIGN+ userId+SPLIT_SIGN+count).substring(0, MD5_SUB_INDEX);
//
//		return dateFormat+md5Random;	
//	}
	
	/**
	 * 产生交易的评价ID
	 * @author david.tan 2015年8月7日 下午5:07:43
	 * @Method: generateJudgementId 
	 * @Description: TODO
	 * @param tradeNo
	 * @param userId
	 * @return 
	 * @throws
	 */
	public static String generateJudgementId(String tradeNo,String userId){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(tradeNo+SPLIT_SIGN+timefator+SPLIT_SIGN+ userId).substring(0, MD5_SUB_INDEX);
		
		
		return dateFormat+md5Random;	
	}
	
	/**
	 * 产生新鲜事ID
	 * @author david.tan 2015年8月7日 下午4:58:37
	 * @Method: generateFeedId 
	 * @Description: TODO
	 * @param goodsName
	 * @param loginName
	 * @return 
	 * @throws
	 */
	public static String generateFeedId(String goodsName,String loginName){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(goodsName+SPLIT_SIGN+timefator+SPLIT_SIGN+ loginName).substring(0, MD5_SUB_INDEX);
		
		
		
		return dateFormat+md5Random;	
	}
	
	
	/**
	 *产生新鲜事的评论ID
	 * 
	 * @author david.tan 2015年8月7日 下午4:45:17
	 * @Method: generateCommentId 
	 * @Description: TODO
	 * @param feeedId
	 * @param userName
	 * @return 
	 * @throws
	 */
	public static String generateCommentId(String feeedId,String loginName){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(feeedId+SPLIT_SIGN+timefator+SPLIT_SIGN+ loginName).substring(0, MD5_SUB_INDEX);
		
		
		
		return dateFormat+md5Random;		
	}
	
	/**
	 * 产生userToken，用于会话保持
	 * 
	 * @param userId
	 * @return
	 */
	public static String generateUserToken(String userId){
	  String dateFormat=DF_LONG_DATETIME.format(Calendar.getInstance().getTime());	
	  String  origin=userId+SPLIT_SIGN+dateFormat;
	  String  userToken=null;
	  try{
	    userToken=Base64.encodeBase64String(origin.getBytes("UTF-8"));	
		
	  }catch(Exception e){
		  
	  }
	   return userToken;
	}
	
	
	/**
	 * 从useToken获取userId,base64混编
	 * 
	 * @param userToken
	 * @return
	 */
	public static String getUserIdByToken(String userToken){
	 byte[]  origin=Base64.decodeBase64(userToken);
	 String token=null;
	 try{
		  token=new String(origin, "UTF-8");
	 }catch(Exception e){
	 }
	 
	 return token.substring(0, USERID_LENGTH);	
	}
	
	
	/**
	 * 产生收货地址的addressId
	 * @author david.tan 2015年8月11日 上午9:25:46
	 * @Method: generateAddressId 
	 * @Description: TODO
	 * @param userId
	 * @param address
	 * @return 
	 * @throws
	 */
	public static String generateAddressId(String userId,String address){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(address+SPLIT_SIGN+timefator+SPLIT_SIGN+ userId).substring(0, MD5_SUB_INDEX);
		
		
		
		return dateFormat+md5Random;		
	}
	
	
	/**
	 * 用户上传图的imgKey
	 * @author david.tan 2015年8月11日 上午9:27:01
	 * @Method: generateImgKey 
	 * @Description: TODO
	 * @param userId
	 * @param imgName
	 * @return 
	 * @throws
	 */
	public static String generateImgKey(String userId,String imgName){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(imgName+SPLIT_SIGN+timefator+SPLIT_SIGN+ userId).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	
	}
	
	
	
	/**
	 * 产生支付流水号
	 * 
	 * @param tradeNo
	 * @param outPayNo
	 * @return
	 */
	public static String generatePayId(String tradeNo,String outPayNo){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(outPayNo+SPLIT_SIGN+timefator+SPLIT_SIGN+ tradeNo).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	
	}
	
	/**
	 * 产生购物车cartId
	 * @param goodsId
	 * @param userId
	 * @return
	 */
	public static String generateCartId(String goodsId,String userId){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(goodsId+SPLIT_SIGN+timefator+SPLIT_SIGN+ userId).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	
	}
	
	
	/**
	 * 产生精选selectionId
	 * @param goodsId
	 * @return
	 */
	public static String generateSelectionId(String goodsId){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(goodsId+SPLIT_SIGN+timefator+SPLIT_SIGN+BizCode.SELECTION_ID).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	
	}
	
	
	
	/**
	 * 产生人气popularityId
	 * @param goodsId
	 * @return
	 */
	public static String generatePopularityId(String goodsId){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(goodsId+SPLIT_SIGN+timefator+SPLIT_SIGN+BizCode.POPULARITY_ID).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	
	}
	
	
	
	/**
	 * 产生优惠券
     *
     * @param mobile
     * @param couponTemplateNo
     * @return
     */
    public static String generateCouponNo(String mobile,String couponTemplateNo ) {
    	
        String dateFormat = DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
		
		 /**random watch */
		int randomWatch=RANDOM_WATCH.incrementAndGet();
        String origin = mobile + SPLIT_SIGN + timefator+SPLIT_SIGN+couponTemplateNo+SPLIT_SIGN+randomWatch;
        String couponId = null;
        try {
            couponId = MD5Util.MD5Encode(origin);

        } catch (Exception e) {

        }
              

        return dateFormat + couponId;
    }
	
	
	
	/**
	 * 产生优惠券模板
     *
     * @param couponTemplateNo
     * @return
     */
    public static String generateCouponTemplateId(String couponTemplateNo) {
        String dateFormat = DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(couponTemplateNo+SPLIT_SIGN+timefator+SPLIT_SIGN+BizCode.COUPON_TEMPLATE_NO).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	
    }

    /**
     * 老的交易编号生成用户交易编号（迁移用）
     * 
     * @author Rylan 2015年9月19日 下午10:18:58
     * @Method: generateUserTradeNo 
     * @Description: 生成用户交易编号，用于用户查询使用
     * @param userId  14位
     * @return 
     * @throws
     */
    @Deprecated
    public static String generateOldUserOrderNo(String userId,String goodsId,Date createTime){
    	if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(goodsId)){
    		return null;
    	}
    	if(createTime==null){
    		createTime =Calendar.getInstance().getTime();
    	}   
    	String dateFormat=DF_DATE.format(createTime.getTime());
    	long timefator=Calendar.getInstance().getTimeInMillis();//当前时间戳
    	    	
    	/**随机因子数   */
    	Long random=(timefator+randomBase.nextInt()+userId.hashCode()+goodsId.hashCode())%USER_ORDER_RANDOM;
    	int randomLength=String.valueOf(random).length(); 	
    	long placeHolderLength=USER_ORDER_RANDOM_LENGTH-randomLength;
    	/**前6位是时间因子  */
    	StringBuffer  userOrderNo=new StringBuffer(dateFormat);
    	
        for(int i=0;i<placeHolderLength;i++) {
        	userOrderNo.append(USER_PLACE_HOLDER);
        }
    	userOrderNo.append(random);
    	return userOrderNo.toString();
	}

    /**
     * 产生用户交易编号
     *      
     * @param userId
     * @return
     */
    public static String generateUserOrderNo(String userId,String goodsId){
    	if(StringUtils.isEmpty(goodsId)||StringUtils.isEmpty(userId)){
    		return null;
    	}
    	String dateFormat=DF_DATE.format(Calendar.getInstance().getTime());
    	long timefator=Calendar.getInstance().getTimeInMillis();//当前时间戳
    	    	
    	/**随机因子数   */
    	Long random=(timefator+randomBase.nextInt()+userId.hashCode()+goodsId.hashCode())%USER_ORDER_RANDOM;
    	int randomLength=String.valueOf(random).length();    	
    	long placeHolderLength=USER_ORDER_RANDOM_LENGTH-randomLength;
    	/**前6位是时间因子  */
    	StringBuffer  userOrderNo=new StringBuffer(dateFormat); 	
        //如果长度不够填充其他数字
    	for(int i=0;i<placeHolderLength;i++) {
        	userOrderNo.append(USER_PLACE_HOLDER);
        }
    	userOrderNo.append(random);
    	return userOrderNo.toString();
    }
    
    /**
     * @author Rylan 2015年10月23日 下午1:37:17
     * @Method: generateUserNickName 
     * @Description: 生产随机的昵称
     * @param mobile
     * @return 
     * @throws
     */
    public static String generateUserNickName(String mobile){
    	if(StringUtils.isEmpty(mobile)){
    		return null;
    	}
    	String dateFormat=DF_DATE.format(Calendar.getInstance().getTime());
    	long timefator=Calendar.getInstance().getTimeInMillis();//当前时间戳
    	    	
    	/**随机因子数   */
    	Long random=(timefator+randomBase.nextInt()+mobile.hashCode())%USER_NICKNAME_RANDOM;
    	int randomLength=String.valueOf(random).length();    	
    	long placeHolderLength=USER_NICKNAME_RANDOM_LENGTH-randomLength;
    	/**前6位是时间因子  */
    	StringBuffer  nickname=new StringBuffer(dateFormat); 	
        //如果长度不够填充其他数字
    	for(int i=0;i<placeHolderLength;i++) {
    		nickname.append(USER_PLACE_HOLDER);
        }
    	nickname.append(random);
    	return nickname.toString();
    }
    
    /**
     *
     * @author Rylan 2015年10月29日 上午10:06:13
     * @Method: generateCheapNo 
     * @Description: 生产团便宜编号
     * @param goodsId
     * @return cheapNo
     */
    public static String generateCheapNo(String goodsId) {
        String dateFormat = DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
    	    	
    	/**随机因子数   */
    	Long random=(timefator+randomBase.nextInt()+goodsId.hashCode())%USER_ORDER_RANDOM;
    	int randomLength=String.valueOf(random).length();    	
    	long placeHolderLength=USER_ORDER_RANDOM_LENGTH-randomLength;
    	/**前6位是时间因子  */
    	StringBuffer  cheapNo=new StringBuffer(dateFormat); 	
        //如果长度不够填充其他数字
    	for(int i=0;i<placeHolderLength;i++) {
        	cheapNo.append(USER_PLACE_HOLDER);
        }
    	cheapNo.append(random);
    	return cheapNo.toString();
    }
    
    
    public static String generateGoodsGroupId(String ... goodsIds) {
    	if(goodsIds.length==0){
    		return null;
    	}
    	String dateFormat=DF_DATE.format(Calendar.getInstance().getTime());
    	long timefator=Calendar.getInstance().getTimeInMillis();//当前时间戳
    	    	
    	/**随机因子数   */
    	long random=0;		
        for (int i = 0; i < goodsIds.length; i++) {
        	 random +=(timefator+randomBase.nextInt()+goodsIds[i].hashCode())%USER_ORDER_RANDOM;
		}	   	
    	int randomLength=String.valueOf(random).length();    	
    	long placeHolderLength=USER_ORDER_RANDOM_LENGTH-randomLength;
    	/**前6位是时间因子  */
    	StringBuffer  groupId=new StringBuffer(dateFormat); 	
        //如果长度不够填充其他数字
    	for(int i=0;i<placeHolderLength;i++) {
    		groupId.append(USER_PLACE_HOLDER);
        }
    	groupId.append(random);
    	
    	return groupId.toString();
    }
	
	/**
	 * 产生新鲜事ID
	 * @author david.tan 2015年8月7日 下午4:58:37
	 * @Method: generateFeedId 
	 * @Description: TODO
	 * @param userId
	 * @return 
	 * @throws
	 */
	public static String generateFeedId(String userId){
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(userId+SPLIT_SIGN+timefator+SPLIT_SIGN).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	
	}

	/**
	 * 产生新鲜事举报ID
	 * @author david.tan 2015年8月7日 下午4:58:37
	 * @Method: generateFeedReportId 
	 * @Description: TODO
	 * @param userId
	 * @param feedId
	 * @return 
	 */
    public static String generateFeedReportId(String feedId, String userId) {
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(feedId+SPLIT_SIGN+timefator+SPLIT_SIGN+ userId).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	
    }

	/**
	 * 产生新鲜事标签ID
	 * @author david.tan 2015年8月7日 下午4:58:37
	 * @Method: generateFeedLabelId 
	 * @Description: TODO
	 * @param userId
	 * @param feedId
	 * @return 
	 */
    public static String generateFeedLabelId(String feedId, String userId) {
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(feedId+SPLIT_SIGN+timefator+SPLIT_SIGN+ userId).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	
    }

	/**
	 * 产生新鲜事评论ID
	 * @author david.tan 2015年8月7日 下午4:58:37
	 * @Method: generateFeedCommentId 
	 * @Description: TODO
	 * @param userId
	 * @param feedId
	 * @return 
	 */
    public static String generateFeedCommentId(String feedId, String userId) {
		String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
        String md5Random=MD5Util.MD5Encode(feedId+SPLIT_SIGN+timefator+SPLIT_SIGN+ userId).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	
    }
    
    
    /**
     * 产生用户对应的的账户ID
     * 
     * @param userId
     * @return accountId
     */
   public static String generateAccountId(String userId){
	   String dateFormat=DF_DATETIME.format(Calendar.getInstance().getTime());
		long timefator=Calendar.getInstance().getTimeInMillis();
       String md5Random=MD5Util.MD5Encode(userId+SPLIT_SIGN+timefator).substring(0, MD5_SUB_INDEX);
		
		return dateFormat+md5Random;	  
   }
   
    
    public static void main(String[] args) {
		//System.out.println(BizCodeUtil.generateUserNickName("15822060068"));
		long startTime=System.currentTimeMillis();
    	String tradeNo=null;
		for (int i = 0; i < 800; i++) {
			String tmpTradeNo=BizCodeUtil.generateTradeNo("1111", "w32312121");
			if(tmpTradeNo.equals(tradeNo)){
				System.out.println(tradeNo +" 相等的 " +tmpTradeNo);
			}
			tradeNo=tmpTradeNo;
			
		}
		//System.out.println(BizCodeUtil.generateUserToken("2015110910454bee5730c2873"));
		//System.out.println(BizCodeUtil.getUserIdByToken("MjAxNTExMDkxMDQ1NGJlZTU3MzBjMjg3M3wyMDE1MTIwODE1NTM="));
		System.out.println(System.currentTimeMillis()-startTime+"ms");
		
	}
    
	
}
